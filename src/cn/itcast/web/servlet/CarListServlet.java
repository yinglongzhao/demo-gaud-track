package cn.itcast.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dao.DataDao;
import cn.itcast.entity.GuijiInfo;
import cn.itcast.entity.ResultInfo;
import cn.itcast.factory.DaoFactory;
import net.sf.json.JSONObject;

public class CarListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("username");
		System.out.println("hello world " + name);
		DataDao dd = DaoFactory.getInstance().getDataDao(); 
		Map<String,List<GuijiInfo>> allGuiji = dd.getAllGuiji(name);
		Map<String, ResultInfo> chuliData = dd.chuliData(allGuiji);
		System.out.println(chuliData);
		PrintWriter out = response.getWriter();
		JSONObject json = JSONObject.fromObject(chuliData);
		out.write(json.toString());
	}
}
