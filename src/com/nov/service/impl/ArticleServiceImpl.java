package com.nov.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.nov.bean.Article;
import com.nov.bean.ArticleType;
import com.nov.dao.ArticleDao;
import com.nov.dao.ArticleTypeDao;
import com.nov.dao.CommentDao;
import com.nov.dao.impl.ArticleDaoImpl;
import com.nov.dao.impl.ArticleTypeDaoImpl;
import com.nov.dao.impl.CommentDaoImpl;
import com.nov.service.ArticleService;
import com.nov.vo.PageBean;

/**
 * 文章模块业务层实现类
 * @author nov
 *
 */
public class ArticleServiceImpl implements ArticleService {

	// 注入文章数据持久层对象
	private ArticleDao articleDao = new ArticleDaoImpl();
	// 注入文章类型数据持久层对象
	private ArticleTypeDao articleTypeDao = new ArticleTypeDaoImpl();
	// 注入评论数据持久层对象
	private CommentDao commentDao = new CommentDaoImpl();
	
	public List<Article> findAllArticleByAuthorIdList(Integer id) {
		List<Article> articleList = articleDao.findAllArticleByAuthorIdList(id);
		for (Article article : articleList) {
			// 根据文章实体中外键文章类型ID查询文章类型实体，并设置到文章实体中
			article.setArticleType(articleTypeDao.findArticleTypeById(article.getArticle_type_id()));
		}
		return articleList;
	}

	/**
	 * 将文章模块的分页对象完善的业务层实现方法
	 */
	public void improveArticlePageBean(Integer id, PageBean<Article> pageBean) {
		Long total = articleDao.findArticleTotal(id);
		pageBean.setTotal(total.intValue());
		Integer size = pageBean.getPageSize();
		Integer index = (pageBean.getCurrentPage() - 1) * size;
		if(index < 0) {
			index = 0;
		}
		
		List<Article> rows = articleDao.findRowsByIndexSizeAndAuthorId(id, index, size);
		pageBean.setRows(rows);
	}

	/**
	 * 在文章实体中设置文章类型实体
	 */
	public void setArticleTypeToArticle(PageBean<Article> pageBean) {
		List<Article> rows = pageBean.getRows();
		if(rows == null) {
			return;
		}
		for (Article article : rows) {
			ArticleType articleType = articleTypeDao.findArticleTypeById(article.getArticle_type_id());
			article.setArticleType(articleType);
		}
	}

	/**
	 * 删除文章所有的评论
	 */
	public void deleteAllCommentOfArticle(String id) {
		commentDao.deleteAllCommentOfArticle(id);
	}

	/**
	 * 删除文章
	 */
	public void deleteArticleById(String id, HttpServletRequest request) {
		// 删除tomcat中保存的图片
		Article article = articleDao.findArticleById(id);
		String content = article.getContent();
		if (content != null && content.length() >= 0) {
			List<String> imageSrcList = new ArrayList<String>();
			// 正则获得图片url
			Pattern p = Pattern.compile(
					"<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>",
					Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(content);
			String quote = null;
			String src = null;
			while (m.find()) {
				quote = m.group(1);
				src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
				// 正斜杠转反斜杠
				src = src.replaceAll("/", "\\\\");
				imageSrcList.add(src);
			}
			List<String> imgUrlList = new ArrayList<String>();
			String url = request.getServletContext().getRealPath("");
			int ch = url.lastIndexOf("\\");
			String webapp = url.substring(0, ch);
			for (String imgSrc : imageSrcList) {
				String imgUrl = webapp + "\\" + imgSrc;
				imgUrlList.add(imgUrl);
			}

			for (String string : imgUrlList) {
				File file = new File(string);
				if (file.isFile() && file.exists()) {
					Boolean succeedDelete = file.delete();
					if (succeedDelete) {
						System.out.println("删除单个文件" + string + "成功！");
					} else {
						System.out.println("删除单个文件" + string + "失败！");
					}
				}
			}
		}
		// 删除文章在数据库的记录
		articleDao.deleteArticleById(id);
	}

	/**
	 * 级联删除文章
	 */
	public void cascadeDeleteArticle(String id, HttpServletRequest request) {
		// 先根据文章ID删除该文章所有的评论
		deleteAllCommentOfArticle(id);
		// 删除文章
		deleteArticleById(id, request);
	}

	/**
	 * 保存文章
	 */
	public void save(Article art) {
		articleDao.save(art);
	}

	/**
	 * 判断该博主的文章标题是否重复
	 */
	public boolean exsitThisTitle(Integer id, String title) {
		List<Object> titleList = articleDao.findAllArticleTitleByAuthorIdList(id);
		if (titleList == null) {
			return false;
		}
		for (Object object : titleList) {
			if(title.equals(object.toString())){
				return true;
			}
		}
		return false;
	}

	/**
	 * 按搜索和条件来完善分页对象
	 */
	public void improveArticlePageBeanByTerm(Integer id, String search, String condition, PageBean<Article> pageBean) {
		Long total = new Long(0);
		if("title".equals(condition)) {
			total = articleDao.findArticleByTitleTotal(id, search);
		}
		
		
		pageBean.setTotal(total.intValue());
		Integer size = pageBean.getPageSize();
		Integer index = (pageBean.getCurrentPage() - 1) * size;
		if(index < 0) {
			index = 0;
		}
		List<Article> rows = null;
		if("title".equals(condition)){
			search = '%' + search + '%';
			rows = articleDao.findRowsByIndexSizeTitleAndAuthorId(id, index, size, search);
		}
		pageBean.setRows(rows);
	}

	/**
	 * 根据ID查询文章
	 */
	public Article findArticleById(String articleId) {
		return articleDao.findArticleById(articleId);
	}

	/**
	 * 对文章进行更新
	 */
	public void update(Article art) {
		articleDao.updateArticle(art);
	}

}
