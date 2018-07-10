package com.nov.controller.backstage;

import java.io.IOException;
import java.io.PrintWriter;
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
		
		if ("list".equals(method)) {
			list(request, response);
		} else if ("delete".equals(method)) {
			delete(request, response);
			list(request, response);
		} else if ("save".equals(method)) {
			save(request, response);
			list(request, response);
		} else if ("avoidRepeat".equals(method)) {
			avoidRepeat(request, response);
		}

	}

	private void avoidRepeat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object author = request.getSession().getAttribute("author");
		if (author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		} else {
			String typeName = request.getParameter("typeName").trim();
			Integer id = ((Author) author).getId();
			boolean exsit = articleTypeService.exsitThisTypeName(id, typeName);
			
			PrintWriter writer = response.getWriter();
			if(exsit) {
				writer.write("已存在该类型名");
			}else{
				writer.write("");
			}
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 获得博主对象
		Object author = request.getSession().getAttribute("author");
		// 非空判断
		if (author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
			return;
		} else {
			Integer id = ((Author)author).getId();
			
			ArticleType articleType = new ArticleType();
			articleType.setAuthor_id(id);
			articleType.setTypeName(request.getParameter("typeName").trim());
			articleType.setInfo(request.getParameter("info").trim());
			
			articleTypeService.saveArticleType(articleType);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 获得博主对象
		Object author = request.getSession().getAttribute("author");
		// 非空判断
		if(author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
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
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
			return;
		} else {
			Integer id = ((Author)author).getId();
			//获得分页对象
			PageBean<ArticleType> pageBean = this.getPageBean(request, response);
			articleTypeService.improvePageBean(id, pageBean);
			/*List<ArticleType> articleTypeList = articleTypeService.findArticleTypeByAuthorIdList(id);*/
			// 健壮性判断
			if (pageBean.getCurrentPage() == 0) {
				pageBean.setCurrentPage(1);
			}
			if (pageBean.getTotalPage() == 0) {
				pageBean.setTotalPage(1);
			}
			//转发到页面
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("/WEB-INF/backstageContent/articletype.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
