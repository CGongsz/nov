package com.nov.controller.backstage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nov.bean.Author;
import com.nov.service.LoginService;
import com.nov.service.impl.LoginServiceImpl;

/**
 * 后台登录模块控制层
 * @author nov
 *
 */
public class LoginServlet extends HttpServlet {

	private LoginService loginService = new LoginServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获得提交的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 获得记住密码框
		/*request.getParameter("");*/
		
		boolean existUsername = username != null && !"".equals(username);
		boolean existPassword = password != null && !"".equals(password);
		
		// 防止直接输入url提交空指针报错
		if(existUsername) {
			username = username.trim();
		}
		
		Author author = null;
		
		// 非空校验
		if(existUsername && existPassword) {
			// 如果用户名和密码都不为null且不为"",调用业务层对比数据库数据
			author = loginService.findAuthorByUsernameAndPassword(username, password);
			if (author == null) {
				request.setAttribute("error", "用户名或不存在");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			
			// 跳转页面到后台页面
			request.getSession().setAttribute("author", author);
			request.getRequestDispatcher("/WEB-INF/backstageContent/backIndex.jsp").forward(request, response);
		} else {
			// 将错误信息转发到登录页面
			request.setAttribute("error", "用户名或密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
