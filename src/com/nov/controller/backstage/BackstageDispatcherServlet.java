package com.nov.controller.backstage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台分发器
 * @author nov
 *
 */
public class BackstageDispatcherServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 需要分发的路径
		String method = request.getParameter("method");
		
		if("nav".equals(method)) {
			request.getRequestDispatcher("/WEB-INF/backstageContent/nav.jsp").forward(request, response);
		} else if("left".equals(method)){
			request.getRequestDispatcher("/WEB-INF/backstageContent/left.jsp").forward(request, response);
		} else if("index".equals(method)) {
			request.getRequestDispatcher("/WEB-INF/backstageContent/index.jsp").forward(request, response);
		} else if ("send_article".equals(method)) {
			request.getRequestDispatcher("/WEB-INF/backstageContent/send_article.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
