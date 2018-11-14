package com.mgshopping.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckCode
 */
@WebServlet("/CheckCode")
public class CheckCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Content-type", "image/jpeg");
		
		BufferedImage image = new BufferedImage(75, 30, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics2d = (Graphics2D) image.getGraphics();
		
		graphics2d.setBackground(Color.lightGray);
		graphics2d.fillRect(0, 0, 75, 30);
		
		graphics2d.setColor(new Color(154, 157, 158));
		graphics2d.setFont(new Font("Open Sans", Font.BOLD, 16));
		String code = randomNum();
		graphics2d.drawString(code, 7, 23);
		ImageIO.write(image, "jpeg", response.getOutputStream());
		request.getSession().setAttribute("code", code);
		
	}

	private String randomNum() {
		// TODO Auto-generated method stub
		Random random = new Random();
		String num=random.nextInt(999999)+"";
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 6-num.length(); i++) {
			stringBuffer.append(0);
		}
		
		num=stringBuffer.toString()+num;
		return num;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
