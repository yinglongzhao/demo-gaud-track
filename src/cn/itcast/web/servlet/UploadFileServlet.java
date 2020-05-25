package cn.itcast.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import cn.itcast.dao.UserDao;
import cn.itcast.factory.DaoFactory;
import cn.itcast.service.UploadFileService;;

public class UploadFileServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ҫִ���ļ��ϴ�����
		//�жϱ��Ƿ�֧���ļ��ϴ���enctype="multipart/form-data"
		boolean ism =ServletFileUpload.isMultipartContent(request);
		if(!ism){
			throw new RuntimeException("your form is not multipart/form-data");
		}
		//����һ��DiskFileItemfactory������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//����һ��ServletFileUpload���Ķ��󣨱���������
		ServletFileUpload sfu = new ServletFileUpload(factory);
		//����request���󣬵õ�һ�������
		try {
			List<FileItem> fileItems = sfu.parseRequest(request);
			//������������
			for(FileItem fileitem:fileItems){
				if(fileitem.isFormField()){
					//��ͨ����
					processFormField(fileitem);
				}else{
					//�ϴ�����
					processUploadField(fileitem);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//����excel�ļ�
		try {
			UploadFileService ufs = new UploadFileService();
			ArrayList<ArrayList<String>> results = ufs.f2m();
			UserDao ud=DaoFactory.getInstance().getUserDao();
			ud.insertUserInfo(results);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//�ϴ�����
	private void processUploadField(FileItem fileitem) {
		//�õ��ϴ�������
		String filename= fileitem.getName();
		//�õ��ļ���
		try {
			//�õ��ļ�������
			InputStream is = fileitem.getInputStream();
			//��������·��
			String directoryRealPath=this.getServletContext().getRealPath("/upload");
			System.out.println(directoryRealPath);
			File storeDirectory= new File(directoryRealPath);
			//����ָ��Ŀ¼
			if(!storeDirectory.exists()){
				storeDirectory.mkdirs();
			}
			//��storeDirectory�£���������Ŀ¼�µ��ļ�
			File file=new File(storeDirectory,filename);
			//ͨ���ļ���������ϴ����ļ����浽����
			FileOutputStream fos=new FileOutputStream(file);
			
			int len=0;
			byte[] b=new byte[1024];
			while((len=is.read(b))!=-1){
				fos.write(b, 0, len);
			}
			fos.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//��ͨ����
	private void processFormField(FileItem fileitem) {
		String fieldname=fileitem.getFieldName();//�ֶ���
		String fieldvalue=fileitem.getString();//�ֶ�ֵ
		System.out.println(fieldname+"="+fieldvalue);
	}
}
