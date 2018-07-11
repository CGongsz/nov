package com.nov.service.impl;

import java.util.List;

import com.nov.bean.Article;
import com.nov.bean.ArticleType;
import com.nov.bean.Author;
import com.nov.dao.ArticleDao;
import com.nov.dao.ArticleTypeDao;
import com.nov.dao.AuthorDao;
import com.nov.dao.impl.ArticleDaoImpl;
import com.nov.dao.impl.ArticleTypeDaoImpl;
import com.nov.dao.impl.AuthorDaoImpl;
import com.nov.service.StageArticleService;
import com.nov.vo.PageBean;

/**
 * 前台业务层实现类
 * @author nov
 *
 */
public class SategArticleServiceImpl implements StageArticleService {
	// 文章数据持久层
	private ArticleDao articleDao = new ArticleDaoImpl();
	// 博主数据持久层
	private AuthorDao authorDao = new AuthorDaoImpl();
	// 文章类型数据持久层
	private ArticleTypeDao articleTypeDao = new ArticleTypeDaoImpl();
	/**
	 * 查询某类型所有文章
	 */
	public List<Article> findArticleByArticleTypeIdList(Integer typeId) {
		List<Article> articleList = articleDao.findArticleByArticleTypeIdList(typeId);
		
		return articleList;
	}

	/**
	 * 查询博主
	 */
	public Author findAuthorById(Integer authorId) {
		return authorDao.findAuthorById(authorId);
	}

	/**
	 * 根据ID查询文章
	 */
	public Article findArticleById(String articleId) {
		return articleDao.findArticleById(articleId);
	}

	/**
	 * 点击量加一
	 */
	public void updateArticleClickRateById(String articleIdStr ,Integer integer) {
		articleDao.updateArticleClickRateById(articleIdStr, integer);
	}

	/**
	 * 查询文章类型
	 */
	public ArticleType findArticleTypeById(Integer typeId) {
		return articleTypeDao.findArticleTypeById(typeId);
	}

	/**
	 * 完善前台分页对象
	 */
	public void improveArticlePageBean(Integer typeId, PageBean<Article> pageBean) {
		Long total = articleDao.findArticleTotalByArticleTypeId(typeId);
		pageBean.setTotal(total.intValue());
		Integer size = pageBean.getPageSize();
		Integer index = (pageBean.getCurrentPage() - 1) * size;
		if(index < 0) {
			index = 0;
		}
		List<Article> rows = articleDao.findfindRowsByIndexSizeAndArticleTypeId(typeId, index, size);
		pageBean.setRows(rows);
	}

}
