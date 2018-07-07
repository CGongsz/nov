package com.nov.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.nov.bean.Article;
import com.nov.dao.ArticleDao;
import com.nov.utils.ComPoolUtil;
import com.nov.vo.PageBean;

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


	/**
	 * 查询博主文章总记录数
	 */
	public Long findArticleTotal(Integer id) {
		String sql = "select count(*) from article where author_id = ? ";
		Long total = this.findEntityNumber(sql, id);
		return total;
	}

	/**
	 * 查询部分文章数据
	 */
	public List<Article> findRowsByIndexSizeAndAuthorId(Integer id, Integer index, Integer size) {
		String sql = "select * from article where author_id = ? order by createTime desc limit ?,?";
		return this.query(sql, id, index, size);
	}

	/**
	 * 根据ID查询指定文章
	 */
	public Article findArticleById(String article_id) {
		String sql = "select * from article where id = ? ";
		List<Article> articleList = this.query(sql, article_id);
		if(articleList != null && articleList.size() > 0) {
			return articleList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 删除文章
	 */
	public void deleteArticleById(String id) {
		String sql = "delete from article where id = ? ";
		this.update(sql, id);
	}

	/**
	 * 获得一个文章类型的所有文章ID的集合
	 */
	public List<Object> findArticleIdByArticleTypeIdList(Integer id) {
		String sql = "select * from article where article_type_id = ? ";
		List<Object> columnList = this.findEntityOfOneColumn(sql, "id", id);
		
		return columnList;
	}

	/**
	 * 保存文章实体
	 */
	public void save(Article art) {
		String sql = "insert into article (id, author_id, title, createTime, content, article_type_id, keyword, clickRate) values (?,?,?,?,?,?,?,?)";
		Object[] params = {art.getId(), art.getAuthor_id(), art.getTitle(), art.getCreateTime(), art.getContent(), art.getArticle_type_id(), art.getKeyword(), art.getClickRate()};
		this.update(sql, params);
	}

	
	

}
