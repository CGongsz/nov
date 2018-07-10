package com.nov.controller.stage;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.nov.bean.Article;
import com.nov.bean.Author;
import com.nov.controller.base.BaseServlet;
import com.nov.service.StageArticleService;
import com.nov.service.impl.SategArticleServiceImpl;

/**
 * 前台文章模块控制器
 * @author nov
 *
 */
public class StageArticleServlet extends BaseServlet<Article> {
	
	private StageArticleService stageArticleService = new SategArticleServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if("getArticleByArticleType".equals(method)) {
			getArticleByArticleType(request, response);
		} else if("showArticle".equals(method)) {
			showArticle(request, response);
		}
	}
	
	/**
	 * 获得显示文章的json数据,并点击量加一
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void showArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String authorIdStr = request.getParameter("aid");
		String articleIdStr = request.getParameter("id");
		Integer authorId = null;
		
		// 数据合法性校验
		if (authorIdStr != null && !"".equals(authorIdStr)) {
			Pattern p = Pattern.compile("^[0-9]*[1-9][0-9]*$");
			Matcher m = p.matcher(authorIdStr);

			if (m.find()) {
				authorId = Integer.parseInt(authorIdStr);
			}
		}


		if (authorId == null) {
			return;
		}
		
		// 查询博主
		Author author = stageArticleService.findAuthorById(authorId);

		if (author == null) {
			return;
		}
		// 查询文章
		Article article = stageArticleService.findArticleById(articleIdStr);
		if(article == null) {
			return;
		}
		
		
		
		article.setAuthor(author);
		// 文章点击量加一
		stageArticleService.updateArticleClickRateById(articleIdStr, article.getClickRate());
		String articleJson = JSON.toJSONString(article);
		// 写到页面
		response.getWriter().write(articleJson);

	}

	/**
	 * 查出某类型的所有文章以json格式写回给页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getArticleByArticleType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String authorIdStr = request.getParameter("aid");
		String articleTypeId = request.getParameter("tid");
		Integer authorId = null;
		Integer typeId = null;
		
		// 数据合法性校验
		if(authorIdStr != null && !"".equals(authorIdStr)) {
			Pattern p = Pattern.compile("^[0-9]*[1-9][0-9]*$");
			Matcher m = p.matcher(authorIdStr);
			
			if(m.find()) {
				authorId = Integer.parseInt(authorIdStr);
			}
		}
		
		if(articleTypeId != null && !"".equals(articleTypeId)) {
			Pattern p = Pattern.compile("^[0-9]*[1-9][0-9]*$");
			Matcher m = p.matcher(articleTypeId);
			
			if(m.find()) {
				typeId = Integer.parseInt(articleTypeId);
			}
		}
		
		if(authorId == null || typeId == null) {
			return;
		}
		
		// 查询博主
		Author author = stageArticleService.findAuthorById(authorId);
		
		if(author == null) {
			return;
		}
		
		// 查询文章
		List<Article> articleList = stageArticleService.findArticleByArticleTypeIdList(typeId);
		if (articleList == null) {
			return;
		}
		for (Article article : articleList) {
			article.setAuthor(author);
		}
		
		String articleJson = JSON.toJSONString(articleList);
		
		response.getWriter().write(articleJson);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
