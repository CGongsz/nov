package com.nov.service.impl;

import java.util.List;

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
		List<Article> rows = articleDao.findRowsByIndexSizeAndAuthorId(id, index, size);
		pageBean.setRows(rows);
	}

	/**
	 * 在文章实体中设置文章类型实体
	 */
	public void setArticleTypeToArticle(PageBean<Article> pageBean) {
		List<Article> rows = pageBean.getRows();
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
	public void deleteArticleById(String id) {
		articleDao.deleteArticleById(id);
	}

	/**
	 * 级联删除文章
	 */
	public void cascadeDeleteArticle(String id) {
		// 先根据文章ID删除该文章所有的评论
		deleteAllCommentOfArticle(id);
		// 删除文章
		deleteArticleById(id);
	}

	/**
	 * 保存文章
	 */
	public void save(Article art) {
		articleDao.save(art);
	}

}
