package com.nov.controller.backstage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nov.bean.ArticleType;
import com.nov.bean.Author;
import com.nov.controller.base.BaseServlet;
import com.nov.service.ArticleTypeService;
import com.nov.service.impl.ArticleTypeServiceImpl;
import com.nov.vo.PageBean;
/**
 * 文章类型控制层
 * @author nov
 *
 */
public class ArticleTypeServlet extends BaseServlet<ArticleType> {
	
	// 注入文章类型模块的业务层
	private ArticleTypeService articleTypeService = new ArticleTypeServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if("list".equals(method)) {
			list(request, response);
			return;
		}
		
		if("delete".equals(method)) {
			delete(request, response);
			list(request, response);
			return;
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获得博主对象
		Object author = request.getSession().getAttribute("author");
		// 非空判断
		if(author == null) {
			// session失效重定向登录页面
			response.sendRedirect("/backstage/login.jsp");
			return;
		} else {
			// 获得要删除文章类型的ID
			Integer id = Integer.parseInt(request.getParameter("id"));
			// 级联删除文章类型
			articleTypeService.cascadeDeleteArticleType(id);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 获得博主对象
		Object author = request.getSession().getAttribute("author");
		
		// 非空判断
		if(author == null) {
			// session失效重定向登录页面
			response.sendRedirect("/backstage/login.jsp");
			return;
		} else {
			Integer id = ((Author)author).getId();
			//获得分页对象
			PageBean<ArticleType> pageBean = this.getPageBean(request, response);
			articleTypeService.improvePageBean(id, pageBean);
			List<ArticleType> articleTypeList = articleTypeService.findArticleTypeByAuthorIdList(id);
			//转发到页面
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("/WEB-INF/backstageContent/articletype.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
