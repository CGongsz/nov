package com.nov.controller.backstage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONObject;
import com.nov.bean.Article;
import com.nov.bean.ArticleType;
import com.nov.bean.Author;
import com.nov.controller.base.BaseServlet;
import com.nov.service.ArticleService;
import com.nov.service.ArticleTypeService;
import com.nov.service.impl.ArticleServiceImpl;
import com.nov.service.impl.ArticleTypeServiceImpl;
import com.nov.vo.PageBean;
/**
 * 文章管理模块控制层
 * @author nov
 *
 */
public class ArticleServlet extends BaseServlet<Article> {

	// 注入文章模块的业务层
	private ArticleService articleService = new ArticleServiceImpl();
	
	private ArticleTypeService artTypeService = new ArticleTypeServiceImpl();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获得将要执行的方法
		String method = request.getParameter("method");
		
		
		// 逻辑判断执行什么方法
		
		
		if("list".equals(method)) {
			list(request, response);
		}else if("send".equals(method)) {
			send(request,response);
		}else if ("save".equals(method)) {
			save(request, response);
		}else if("art_type".equals(method)) {
			artType(request, response);
		}else if("imgurl".equals(method)) {
			imgurl(request, response);
		}else if("delete".equals(method)) {
			delete(request, response);
			list(request, response);
		}else if("avoidRepeat".equals(method)) {
			avoidRepeat(request, response);
		}else if("backWrite".equals(method)) {
			backWrite(request, response);
		}else if("update".equals(method)) {
			update(request, response);
		}else if("updateAvoidRepeat".equals(method)){
			updateAvoidRepeat(request, response);
		}
	}
	
	/**
	 * 更新文章标题去重
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateAvoidRepeat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object author = request.getSession().getAttribute("author");
		if (author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		} else {
			String title = request.getParameter("title");
			String id = request.getParameter("id");
			
			Article article = articleService.findArticleById(id);
			
			if(article ==null){
				request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
			}
			
			PrintWriter writer = response.getWriter();
			if(article.getTitle().equals(title)) {
				writer.write("");
				return;
			}
			
			Integer authorId = ((Author) author).getId();
			
			boolean exsit = articleService.exsitThisTitle(authorId, title);
			
			if(exsit) {
				writer.write("已存在该标题的文章");
			}else{
				writer.write("");
			}
			
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object author = request.getSession().getAttribute("author");
		// 非空判断
		if (author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
			return;
		}
		
		// 初始化文章实体
		Article art = new Article();
		DiskFileItemFactory dis = new DiskFileItemFactory();
		File f = new File("/temp");
		if (!f.exists()) {
			f.mkdirs();
		}
		dis.setRepository(f);
		ServletFileUpload sf = new ServletFileUpload(dis);
		sf.setHeaderEncoding("utf-8");
		
		try {
			Map map = new HashMap();
			List<FileItem> fileitems = sf.parseRequest(request);
			for (FileItem fileitem : fileitems) {
				if (fileitem.isFormField()) {
					String name = fileitem.getFieldName();
					String value = fileitem.getString("UTF-8");
					map.put(name, value);
				}
			}

			// 映射文章实体

			int author_id = ((Author) author).getId();
			map.put("author_id", author_id);

			Date createTime = new Date();
			map.put("createTime", createTime);
			

			try {
				BeanUtils.populate(art, map);
				articleService.update(art);
				request.getRequestDispatcher("/articleServlet?method=list").forward(request, response);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得文章并回写到修改页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void backWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object author = request.getSession().getAttribute("author");
		if (author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		} else {
			String articleId = request.getParameter("id");
			
			Article article = articleService.findArticleById(articleId);
			if(article == null) {
				return;
			}
			request.setAttribute("article", article);
			request.getRequestDispatcher("/WEB-INF/backstageContent/update_article.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * ajax判断文章标题是否重复
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void avoidRepeat(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Object author = request.getSession().getAttribute("author");
		if (author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		} else {
			String title = request.getParameter("title").trim();
			Integer id = ((Author) author).getId();
			
			boolean exsit = articleService.exsitThisTitle(id, title);
			
			PrintWriter writer = response.getWriter();
			if(exsit) {
				writer.write("已存在该标题的文章");
			}else{
				writer.write("");
			}
		}
	}

	/**
	 * 前台编辑器保存图片
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void imgurl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Object author = request.getSession().getAttribute("author");
		if (author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
		}else {

			// 创建文件对象工厂
			DiskFileItemFactory dis = new DiskFileItemFactory();
			// 设置文件缓存路径
			File f = new File("/temp");
			if (!f.exists()) {
				f.mkdirs();
			}
			dis.setRepository(f);
			// 获得文件对象
			ServletFileUpload fileupload = new ServletFileUpload(dis);
			fileupload.setHeaderEncoding("utf-8");
			try {
				List<FileItem> fileItems = fileupload.parseRequest(request);
				for (FileItem fileitem : fileItems) {
					if (fileitem.isFormField()) {
						System.out.println(fileitem.getString("utf-8"));
					} else {
						// 重写图片名
						String filename = UUID.randomUUID() + "_" + fileitem.getName();
						// 将图片写入
						String filepath = this.getServletContext().getRealPath("/uploadImg/" + filename);
						File newFile = new File(filepath);
						newFile.getParentFile().mkdirs();
						newFile.createNewFile();
						InputStream in = fileitem.getInputStream();
						FileOutputStream out = new FileOutputStream(newFile);
						byte[] buffer = new byte[1024];
						int len;
						while ((len = in.read(buffer)) > 0) {
							out.write(buffer, 0, len);
						}
						out.flush();
						out.close();
						in.close();
						fileitem.delete();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
						} finally {

							response.getWriter().write(request.getContextPath() + "/uploadImg/" + filename);
						}
					}
				}
			} catch (FileUploadException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	/**
	 * ajax获取文章类型数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void artType(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		Object obj = request.getSession().getAttribute("author");
		if (obj == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
			return;
		}
		
		//获得博主id来查询文章分类,返回文章编辑页面显示
		Author author =  (Author) obj;
		Integer integer = author.getId();
		List<ArticleType> artTypeList = artTypeService.findArticleTypeByAuthorIdList(integer);
		JSONObject json = new JSONObject();
		json.put("list", artTypeList);
		response.getWriter().write(json.toString());
	}
	
	/**
	 * 转发发布文章页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void send(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {
			
			request.getRequestDispatcher("/WEB-INF/backstageContent/send_article.jsp").forward(request, response);
		}
	/**
	 * 发布文章
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Object author = request.getSession().getAttribute("author");
		// 非空判断
		if (author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
			return;
		}
		// 初始化文章实体
		Article art = new Article();
		DiskFileItemFactory dis = new DiskFileItemFactory();
		File f = new File("/temp");
		if (!f.exists()) {
			f.mkdirs();
		}
		dis.setRepository(f);
		ServletFileUpload sf = new ServletFileUpload(dis);
		sf.setHeaderEncoding("utf-8");
		try {
			Map map = new HashMap();
			List<FileItem> fileitems = sf.parseRequest(request);
			for (FileItem fileitem : fileitems) {
				if (fileitem.isFormField()) {
					if ("click".equals(fileitem.getFieldName())) {
						if (fileitem.getString() == null || fileitem.getString().equals("")) {
							map.put("clickRate", 0);
						} else {
							int clickRate = Integer.parseInt(request.getParameter("clickRate"));
							map.put("clickRate", clickRate);
						}
					} else {
						map.put(fileitem.getFieldName(), fileitem.getString("utf-8"));
					}
				}
			}

			// 映射文章实体

			int author_id = ((Author) author).getId();
			map.put("author_id", author_id);

			String id = UUID.randomUUID().toString();
			map.put("id", id);

			Date createTime = new Date();
			map.put("createTime", createTime);
			
			map.put("clickRate", 0);

			try {
				BeanUtils.populate(art, map);
				articleService.save(art);
				request.getRequestDispatcher("/articleServlet?method=list").forward(request, response);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除文章
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Object author = request.getSession().getAttribute("author");
		if (author == null) {
			// session失效，给出错误页面
			request.getRequestDispatcher("/WEB-INF/error/error.jsp").forward(request, response);
			return;
		} else {
			// 获得文章ID
			String id = request.getParameter("id");
			articleService.cascadeDeleteArticle(id, request);

		}
	}

	/**
	 * 文章列表
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
			PageBean<Article> pageBean = this.getPageBean(request, response);
			
			// 搜索条件以及搜索方式
			String search = request.getParameter("search");
			String condition = request.getParameter("condition");
			
			// 有搜索条件和搜索方式的情况
			if ((search != null && !"".equals(search))&& (condition != null) && !"".equals(condition)) {
				articleService.improveArticlePageBeanByTerm(id, search, condition ,pageBean);
				request.setAttribute("search", search);
				request.setAttribute("condition", condition);
			} else {
				// 完善分页对象
				articleService.improveArticlePageBean(id, pageBean);
			}
			
			
			
			// 将分页对象中的文章实体设置文章类型
			articleService.setArticleTypeToArticle(pageBean);
			
			// 健壮性判断
			if (pageBean.getCurrentPage() == 0) {
				pageBean.setCurrentPage(1);
			}
			if (pageBean.getTotalPage() == 0) {
				pageBean.setTotalPage(1);
			}
			// 转发到页面
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("/WEB-INF/backstageContent/articleList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
