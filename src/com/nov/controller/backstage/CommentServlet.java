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
import com.nov.service.CommentService;
import com.nov.service.impl.CommentServiceImpl;

/**
 * 评论管理模块控制层
 * @author nov
 *
 */
public class CommentServlet extends HttpServlet {

	// 注入评论模块业务层
	private CommentService commentService = new CommentServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if("list".equals(method)) {
			list(request, response);
			return;
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Object author = request.getSession().getAttribute("author");
		
		if(author == null) {
			response.sendRedirect("/backstage/login.jsp");
			return;
		} else {
			// 调用业务层获得博主所有文章的评论
			List<Comment> commentList = commentService.findAllCommentByAuthorIdList(((Author)author).getId());
			// 转发到页面
			request.setAttribute("commentList", commentList);
			request.getRequestDispatcher("/WEB-INF/backstageContent/CommentManage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
