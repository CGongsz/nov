package com.nov.controller.backstage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nov.bean.Article;
import com.nov.bean.Author;
import com.nov.service.ArticleService;
import com.nov.service.impl.ArticleServiceImpl;
/**
 * 文章管理模块控制层
 * @author nov
 *
 */
public class ArticleServlet extends HttpServlet {

	// 注入文章模块的业务层
	private ArticleService articleService = new ArticleServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获得将要执行的方法
		String method = request.getParameter("method");
		
		// 逻辑判断执行什么方法
		if("list".equals(method)) {
			list(request, response);
		}
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Object author = request.getSession().getAttribute("author");
		if(author == null) {
			// session失效，重定向到登录页面
			response.sendRedirect("/backstage/login.jsp");
		} else {
			Integer id = ((Author) author).getId();
			List<Article> articleList = articleService.findAllArticleByAuthorIdList(id);
			// 转发到页面
			request.setAttribute("articleList", articleList);
			request.getRequestDispatcher("/WEB-INF/backstageContent/backstageindex.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
