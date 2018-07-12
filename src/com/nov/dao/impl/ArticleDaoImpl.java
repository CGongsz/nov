package com.nov.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


	/**
	 * 查询博主文章标题的集合
	 */
	public List<Object> findAllArticleTitleByAuthorIdList(Integer id) {
		String sql = "select * from article where author_id = ? ";
		List<Object> titleList = this.findEntityOfOneColumn(sql, "title", id);
		return titleList;
	}

	/**
	 * 查询点击量前十的文章
	 */
	public List<Article> findTenOfArticleByClickRate(Integer id) {
		String sql = "select * from article where author_id = ? order by clickRate desc limit 0,10";
		return this.query(sql, id);
	}

	/**
	 * 获得按条件查询的文章总记录数
	 */
	public Long findArticleByTitleTotal(Integer id, String search) {
		String sql = "select count(*) from article where author_id = ? and title like ?";
		search = '%' + search + '%';
		return this.findEntityNumber(sql, id, search);
	}

	/**
	 * 根据分页对象里的数据、搜索条件和博主ID查询对应的部分数据
	 */
	public List<Article> findRowsByIndexSizeTitleAndAuthorId(Integer id, Integer index, Integer size, String search) {
		String sql = "select * from article where author_id = ? and title like ? order by createTime desc limit ?,?";
		return this.query(sql, id, search, index, size);
	}

	/**
	 * 根据文章类型ID查询文章
	 */
	public List<Article> findArticleByArticleTypeIdList(Integer typeId) {
		String sql = "select * from article where article_type_id = ? order by createTime desc";
		return this.query(sql, typeId);
	}

	/**
	 * 点击量加一
	 */
	public void updateArticleClickRateById(String articleIdStr, Integer integer) {
		integer++;
		String sql = "update article set clickRate = ? where id = ? ";
		this.update(sql, integer, articleIdStr);
	}

	/**
	 * 对文章进行更新
	 */
	public void updateArticle(Article art) {
		String sql = "update article set author_id = ?, title = ?, createTime = ?, content = ?, article_type_id = ?, keyword = ?, clickRate = ? where id = ? ";
		this.update(sql, art.getAuthor_id(), art.getTitle(), art.getCreateTime(), art.getContent(), art.getArticle_type_id(), art.getKeyword(), art.getClickRate(), art.getId());
	}

	/**
	 * 根据文章类型查找该类型文章总记录数
	 */
	public Long findArticleTotalByArticleTypeId(Integer typeId) {
		String sql = "select count(*) from article where article_type_id = ? ";
		Long total = this.findEntityNumber(sql, typeId);
		return total;
	}

	/**
	 * 根据分页对象里的数据和文章类型ID查询对应的部分数据
	 */
	public List<Article> findfindRowsByIndexSizeAndArticleTypeId(Integer typeId, Integer index, Integer size) {
		String sql = "select * from article where article_type_id = ? order by createTime desc limit ?,?";
		return this.query(sql, typeId, index, size);
	}

	/**
	 * 关键字查询博主文章
	 */
	public List<Article> finArticleByKeyWord(int idStr, String keyWord) {
		String sql = "select * from article where author_id = ? and keyword like ?";
		return this.query(sql, idStr, "%" + keyWord + "%");
	}

	/**
	 * 根据关键字查询文章总数量
	 */
	public Long findArticleTotalBykeyword(Integer authorId, String keyword) {
		String sql = "select count(*) from article where author_id = ? and keyword like ?";
		
		return this.findEntityNumber(sql, authorId, keyword);
	}

	/**
	 * 根据关键字查询分页结果集
	 */
	public List<Article> findPageByKeyword(Integer authorId, String keyword, Integer index, Integer size) {
		String sql = "select * from article where author_id = ? and keyword like ? limit ?, ?";
		return this.query(sql, authorId, keyword, index, size);
	}

}
