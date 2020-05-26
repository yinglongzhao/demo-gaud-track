package cn.itcast.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import cn.itcast.dao.UserDao;
import cn.itcast.factory.DaoFactory;

public class RegisterServlet extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String devId = request.getParameter("devId");
		System.out.println("注册 " + name + " " + password);
		UserDao dd = DaoFactory.getInstance().getUserDao();
		PrintWriter out = response.getWriter();
		try {
			dd.registerUser(name, password,devId);
			out.write("注册成功！！！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.write("注册失败，请更换其他用户名！！");
		}
	}

}
