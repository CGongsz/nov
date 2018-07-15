package com.nov.controller.backstage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nov.bean.Author;
import com.nov.service.AuthorService;
import com.nov.service.impl.AuthorServiceImpl;
/**
 * 博主信息模块控制层
 * @author nov
 *
 */
public class AuthorServlet extends HttpServlet {
	
	// 注入博主业务层
	private AuthorService authorService = new AuthorServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if ("writeBack".equals(method)) {
			writeBack(request, response);
		} else if ("update".equals(method)) {
			update(request, response);
			writeBack(request, response);
		}
	}
	
	/**
	 * 更新信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获得页面传来要修改的账号、密码以及介绍
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String introduction = request.getParameter("introduction");
		
		if(username != null) {
			username = username.trim();
		}
		
		if(password != null) {
			password = password.trim();
		}
		
		if(introduction != null) {
			introduction = introduction.trim();
		}
		
		Object author = request.getSession().getAttribute("author");
		if (author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		} else {
			Author oldAuthor = (Author)author;
			authorService.updateAuthor(oldAuthor, username, password, introduction);
			Author newAuthor = authorService.findAuthorById(oldAuthor.getId());
			request.getSession().setAttribute("author", newAuthor);
		}
	}

	/**
	 * 回写博主信息到页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void writeBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object author = request.getSession().getAttribute("author");
		if (author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		} else {
			Integer id = ((Author)author).getId();
			Author writeBackAuthor = authorService.findAuthorById(id);
			request.setAttribute("writeBackAuthor", writeBackAuthor);
			request.getRequestDispatcher("/WEB-INF/backstageContent/updateInfo.jsp").forward(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
