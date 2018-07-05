package com.nov.service.impl;

import java.util.List;

import com.nov.bean.Article;
import com.nov.dao.ArticleDao;
import com.nov.service.ArticleService;

/**
 * 文章模块业务层实现类
 * @author nov
 *
 */
public class ArticleServiceImpl implements ArticleService {

	// 注入文章数据持久层对象
	private ArticleDao articleDao = new ArticleDaoImpl();
	
	public List<Article> findAllArticleByAuthorIdList(Integer id) {
		return articleDao.findAllArticleByAuthorIdList(id);
	}

}
