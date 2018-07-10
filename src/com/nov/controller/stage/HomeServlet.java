package com.nov.controller.stage;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.nov.bean.Article;
import com.nov.bean.ArticleType;
import com.nov.bean.Author;
import com.nov.service.HomeService;
import com.nov.service.impl.HomeServiceImpl;

/**
 * 前台系统的首页模块控制层
 * @author nov
 *
 */
public class HomeServlet extends HttpServlet {

	// 首页模块的业务层
	private HomeService homeService = new HomeServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if ("getBlogAuthor".equals(method)) {
			getBlogAuthor(request, response);
		} else if ("getArticleType".equals(method)) {
			getArticleType(request, response);
		} else if ("getTop10Article".equals(method)) {
			getTop10Article(request, response);
		} else if ("getAuthor".equals(method)) {
			getAuthor(request, response);
		}
	}

	/**
	 * 获得博主实体
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idStr = request.getParameter("id");
		// 判断ID是否是数字
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(idStr);
        if(isNum.matches()){
        	Integer id = Integer.parseInt(idStr);
        	Author author = homeService.findAuthorById(id);
        	String authorJson = JSON.toJSONString(author);
        	response.getWriter().write(authorJson);
        }
	}


	/**
	 * 获得前十点击量的文章
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getTop10Article(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idStr = request.getParameter("id");
		// 判断ID是否是数字
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(idStr);
        if(isNum.matches()){
        	Integer id = Integer.parseInt(idStr);
        	// 获得前十文章集合
        	List<Article> articleList = homeService.findTenOfArticleByClickRate(id);
        	String articleListJson = JSON.toJSONString(articleList);
        	response.getWriter().write(articleListJson);
        }
	}



	/**
	 * 文章分类数据以json数据返回
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getArticleType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idStr = request.getParameter("id");
		// 判断ID是否是数字
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(idStr);
        if(isNum.matches()){
        	Integer id = Integer.parseInt(idStr);
        	// 获得文章分类数据
        	List<ArticleType> articleTypeList = homeService.findArticleTypeByAuthorIdList(id);
        	String articleTypeJson = JSON.toJSONString(articleTypeList);
        	response.getWriter().write(articleTypeJson);
        }
		
	}
	

	/**
	 * 返回博主的json数据给前台
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void getBlogAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Author> author = homeService.getAllBlogAuthorList();
		if (author != null && author.size() > 0) {
			String json = JSON.toJSONString(author);
			response.getWriter().write(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
