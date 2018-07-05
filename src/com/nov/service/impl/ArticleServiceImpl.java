package com.nov.service.impl;

import java.util.List;

import com.nov.bean.Article;
import com.nov.dao.ArticleDao;
import com.nov.dao.ArticleTypeDao;
import com.nov.dao.impl.ArticleDaoImpl;
import com.nov.dao.impl.ArticleTypeDaoImpl;
import com.nov.service.ArticleService;

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
	
	public List<Article> findAllArticleByAuthorIdList(Integer id) {
		List<Article> articleList = articleDao.findAllArticleByAuthorIdList(id);
		for (Article article : articleList) {
			// 根据文章实体中外键文章类型ID查询文章类型实体，并设置到文章实体中
			article.setArticleType(articleTypeDao.findArticleTypeById(article.getArticle_type_id()));
		}
		return articleList;
	}

}
