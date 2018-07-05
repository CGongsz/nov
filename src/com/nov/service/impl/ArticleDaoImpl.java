package com.nov.service.impl;

import java.util.List;

import com.nov.bean.Article;
import com.nov.dao.ArticleDao;
import com.nov.dao.impl.BaseDaoImpl;

public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao {

	public List<Article> findAllArticleByAuthorIdList(Integer id) {
		String sql = "select * from article where author_id = ? ";
		return this.query(sql, id);
	}

}
