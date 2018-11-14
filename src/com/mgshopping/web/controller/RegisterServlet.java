package com.mgshopping.web.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mgshopping.bean.UserBean;
import com.mgshopping.service.UserService;
import com.mgshopping.service.impl.UserServiceImpl;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8;");
		UserBean user = new UserBean();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String string = (String) enumeration.nextElement();
			switch (string) {
			case "username":
				user.setUsername(request.getParameter(string));
				break;

			case "password":
				user.setPassword(request.getParameter(string));
				break;

			case "email":
				user.setEmail(request.getParameter(string));
				break;

			case "realname":
				user.setRealname(request.getParameter(string));
				break;

			default:
				break;
			}
		}
		user.setStatus(0);
		
		System.out.println(user.getEmail());
		
		UserService userService = new UserServiceImpl();
		if (userService.registerUser(user)) {
			response.sendRedirect("/MINGShopping/Login.jsp");
		}else {
			response.getWriter().write("注册失败");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
