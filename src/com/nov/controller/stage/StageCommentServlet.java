package com.nov.controller.stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.nov.bean.Comment;
import com.nov.bean.Visitor;
import com.nov.service.StageCommentService;
import com.nov.service.impl.StageCommentServiceImpl;

/**
 * 前台评论模块的控制层
 * @author nov
 *
 */
public class StageCommentServlet extends HttpServlet {
	
	private StageCommentService stageCommentService = new StageCommentServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if ("appearComment".equals(method)) {
			appearComment(request, response);
		} else if ("existVisitorSubmitComment".equals(method)) {
			existVisitorSubmitComment(request, response);
		} else if ("showComment".equals(method)) {
			showComment(request, response);
		}
	}
	
	/**
	 * 显示该文章所有评论
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void showComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String articleId = request.getParameter("id");
		
		// 根据文章ID获得所有评论并按创建时间递增排序
		List<Comment> commentList = stageCommentService.findAllCommentByArticleId(articleId);
		
		//转JSON发页面
		String commentJson = JSON.toJSONString(commentList);
		response.getWriter().write(commentJson);
		
	}

	/**
	 * 已注册过的游客发表评论
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void existVisitorSubmitComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取从前台传来的数据
		String authorIdStr = request.getParameter("aid");
		// 数据合法性校验
		Integer authorId = null;
		if(authorIdStr != null && !"".equals(authorIdStr)) {
			Pattern p = Pattern.compile("^[0-9]*[1-9][0-9]*$");
			Matcher m = p.matcher(authorIdStr);
			
			if(m.find()) {
				authorId = Integer.parseInt(authorIdStr);
			}
		}
		
		String articleId = request.getParameter("id");
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		
		Comment comment = new Comment();
		comment.setArticle_id(articleId);
		comment.setAuthor_id(authorId);
		comment.setVisitor_email(email);
		comment.setContent(content);
		
		
		Comment showComment = stageCommentService.saveExistVisitorSubmitComment(comment);
		// 出错转错误页面
		if(showComment == null) {
			response.sendRedirect(request.getContextPath() + "/stage/error.jsp");
			return;
		}
		
		// 转JSON数据发页面
		String commentJson = JSON.toJSONString(comment);
		response.getWriter().write(commentJson);
		
	}

	/**
	 * 有效校验
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void appearComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		
		if(email == null) {
			return;
		}
		
		email = email.trim();
		
		Visitor visitor = stageCommentService.findVisitorByEmail(email);
		
		PrintWriter writer = response.getWriter();
		if (visitor == null) {
			Integer mark = stageCommentService.vaildateEmail(email);

			if (1 == mark) {
				writer.write("邮件地址合法");
			} else if (5 == mark) {
				writer.write("电子邮件地址错误");
			} else if (6 == mark) {
				writer.write("当天用户验证超过数量");
			} else if (0 == mark){
				writer.write("请重新验证");
			} else {
				writer.write("一个未知错误");
			}
		} else {
			writer.write("用户存在");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
