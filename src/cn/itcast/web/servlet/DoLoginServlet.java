package cn.itcast.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import cn.itcast.dao.UserDao;
import cn.itcast.factory.DaoFactory;

public class DoLoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest hrequest=(HttpServletRequest)request;
		HttpSession session = hrequest.getSession();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String name=request.getParameter("username");
        String password=request.getParameter("password");
        
        session.setAttribute("name", name);
        UserDao ud=DaoFactory.getInstance().getUserDao();
        JSONObject jsondata = new JSONObject();
        jsondata=ud.getGrade(name, password);
        
        if(jsondata == null || jsondata.isEmpty() || jsondata.isNullObject() || "null".equals(jsondata)){
        	request.setAttribute("msg", "�����û��������벻��ȷ");
        	request.getRequestDispatcher("/login.html").forward(request,response);
        }else {
            System.out.println("name="+name);
            System.out.println("password="+password);
            
        	System.out.println(jsondata);
        	out.write(jsondata.toString());
        }
        
		/*
		 * if(jsondata.getString("grade") !="0"){
		 * 
		 * }else{ request.setAttribute("msg", "�����û��������벻��ȷ");
		 * request.getRequestDispatcher("/login.html").forward(request,response); }
		 */
	}

}
