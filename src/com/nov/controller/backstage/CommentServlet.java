package com.nov.controller.backstage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nov.bean.Author;
import com.nov.bean.Comment;
import com.nov.controller.base.BaseServlet;
import com.nov.service.CommentService;
import com.nov.service.impl.CommentServiceImpl;
import com.nov.vo.PageBean;

/**
 * 评论管理模块控制层
 * @author nov
 *
 */
public class CommentServlet extends BaseServlet<Comment> {

	// 注入评论模块业务层
	private CommentService commentService = new CommentServiceImpl();
	
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

	/**
	 * 删除评论
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Object author = request.getSession().getAttribute("author");
		
		if(author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
			return;
		} else {
			String id = request.getParameter("id");
			commentService.deleteCommentById(id);
		}
	}

	/**
	 * 列表方法
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Object author = request.getSession().getAttribute("author");
		
		if(author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
			return;
		} else {
			Integer id = ((Author) author).getId();
			// 获得分页对象，并初始化
			PageBean<Comment> pageBean = this.getPageBean(request, response);
			commentService.improveCommentPageBean(id, pageBean);
			
			// 设置评论中的文章实体
			commentService.setArticleOfComment(pageBean);
			
			// 健壮性判断
			if (pageBean.getCurrentPage() == 0) {
				pageBean.setCurrentPage(1);
			}
			if (pageBean.getTotalPage() == 0) {
				pageBean.setTotalPage(1);
			}
			
			// 转发到页面
			request.setAttribute("pageBean", pageBean);
			
			request.getRequestDispatcher("/WEB-INF/backstageContent/commentManage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
