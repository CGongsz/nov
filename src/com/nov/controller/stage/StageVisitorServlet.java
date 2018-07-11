package com.nov.controller.stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.nov.bean.ArticleType;
import com.nov.bean.Comment;
import com.nov.bean.Visitor;
import com.nov.service.StageCommentService;
import com.nov.service.StageVisitorService;
import com.nov.service.impl.StageCommentServiceImpl;
import com.nov.service.impl.StageVisitorServiceImpl;

/**
 * 前台游客模块控制层
 * @author nov
 *
 */
public class StageVisitorServlet extends HttpServlet {
	// 前台游客业务模块
	private StageVisitorService stageVisitorService = new StageVisitorServiceImpl();
	
	// 前台评论业务模块
	private StageCommentService stageCommentService = new StageCommentServiceImpl(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if ("usernameAvoidRepeat".equals(method)) {
			usernameAvoidRepeat(request, response);
		} else if ("newVisitorSubmitComment".equals(method)) {
			newVisitorSubmitComment(request, response);
		}
	}

	/**
	 * 保存游客和评论
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void newVisitorSubmitComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String content = request.getParameter("content");
		String articleId = request.getParameter("id");
		String authorIdStr = request.getParameter("aid");
		
		Integer author_id = null;
		
		// 防止URL提交脏数据
		if(email != null && !"".equals(email)) {
			email = email.trim();
		} else {
			return;
		}
		
		if (username != null && !"".equals(username)) {
			username = username.trim();
		} else {
			return;
		}
		
		if (content != null && !"".equals(content)) {
			//content = content.trim();
		} else {
			return;
		}
		
		if (articleId != null && !"".equals(articleId)) {
			articleId = articleId.trim();
		} else {
			return;
		}
		
		// 判断ID是否是数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(authorIdStr);
		if(isNum.matches()){
			author_id = Integer.parseInt(authorIdStr);
		}
		
		// 保存游客
		Visitor visitor = new Visitor(email, username);
		stageVisitorService.save(visitor);
		
		// 保存评论
		Comment comment = new Comment();
		comment.setId(UUID.randomUUID().toString());
		comment.setAuthor_id(author_id);
		comment.setVisitor_username(visitor.getUsername());
		comment.setVisitor_email(visitor.getEmail());
		comment.setArticle_id(articleId);
		comment.setContent(content);
		comment.setCreateTime(new Date());
		stageCommentService.saveComment(comment);
		
		String commentJson = JSON.toJSONString(comment);
		response.getWriter().write("commentJson");
	}

	/**
	 * 游客使用名的去重
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void usernameAvoidRepeat(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		
		if (username != null && !"".equals(username)){
			username = username.trim();
		} else {
			return;
		}
		
		boolean exist = stageVisitorService.existVisitorUsername(username);
		
		PrintWriter writer = response.getWriter();
		
		if (exist) {
			writer.write("该用户名已存在");
		} else {
			writer.write("该用户名可以使用");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
