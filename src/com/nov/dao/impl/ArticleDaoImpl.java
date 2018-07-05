package com.nov.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.nov.bean.Article;
import com.nov.dao.ArticleDao;
import com.nov.utils.ComPoolUtil;

public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao {

	/**
	 * 根据博主ID查询所有文章实体
	 */
	public List<Article> findAllArticleByAuthorIdList(Integer id) {
		String sql = "select * from article where author_id = ? ";
		return this.query(sql, id);
	}

	/**
	 * 根据博主ID查询博主所有文章的ID
	 */
	public List<String> findAllArticleIdByAuthorIdList(Integer id) {
		String sql = "select id from article where author_id = ? ";
		QueryRunner queryRunner = ComPoolUtil.getQueryRunner();
		List<String> articleIdList = null;
		try {
			articleIdList = queryRunner.query(sql, new ColumnListHandler<String>("id"), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleIdList;
	}

}
