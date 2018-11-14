package com.mgshopping.web.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mgshopping.bean.UserBean;
import com.mgshopping.service.UserService;
import com.mgshopping.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkcode = request.getParameter("checkcode");
		String remeberpwd = request.getParameter("remeberpassword");
		System.out.println(remeberpwd);
		String code = (String) request.getSession().getAttribute("code");
		if (code == null || code.equals("") ||  !code.equals(checkcode)) {
			response.getWriter().write("验证码错误");
		}else {
			UserService userService = new UserServiceImpl();
			UserBean user = userService.UserInfo(username, password);
//			System.out.println(username);
//			System.out.println(userService.canlogin(user));
			if (userService.canlogin(user)) {
				request.getSession().setAttribute("UserInfo",user);
				if (remeberpwd==null || remeberpwd.equals("")) {
				}else if (remeberpwd.equals("on")) {
					Cookie nameCookie = new Cookie("username", username);
					Cookie passwordCookie = new Cookie("password", password);
					nameCookie.setMaxAge(60);
					passwordCookie.setMaxAge(60);
					nameCookie.setPath("/");
					passwordCookie.setPath("/");
					response.addCookie(nameCookie);
					response.addCookie(passwordCookie);
				}
				response.sendRedirect(request.getContextPath()+"/IndexServlet");
			}else {
				response.getWriter().write("登录失败");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
