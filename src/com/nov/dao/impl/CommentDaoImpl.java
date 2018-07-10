package com.nov.dao.impl;

import java.util.List;

import com.nov.bean.Article;
import com.nov.bean.Comment;
import com.nov.dao.CommentDao;

/**
 * 评论模块数据持久层实现类
 * @author nov
 *
 */
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {

	/**
	 * 根据文章ID查询该文章所有评论
	 */
	public List<Comment> findCommentByArticleId(String articleId) {
		String sql = "select * from comment where article_id = ? ";
		return this.query(sql, articleId);
	}

	/**
	 * 查询评论总记录数
	 */
	public Long findCommentTotal(Integer id) {
		String sql = "select count(*) from comment where author_id = ?";
		Long total = this.findEntityNumber(sql, id);
		return total;
	}

	/**
	 * 查询部分评论数据
	 */
	public List<Comment> findRowsByIndexSizeAndAuthorId(Integer id, Integer index, Integer size) {
		String sql = "select * from comment where author_id = ? order by createTime desc limit ?,?";
		List<Comment> rows = this.query(sql, id, index, size);
		return rows;
	}

	/**
	 * 根据评论ID删除评论
	 */
	public void deleteCommentById(String id) {
		String sql = "delete from comment where id = ? ";
		this.update(sql, id);
	}

	/**
	 * 根据文章ID删除评论
	 */
	public void deleteAllCommentOfArticle(String id) {
		String sql = "delete from comment where article_id = ? ";
		this.update(sql, id);
	}

	/**
	 * 保存评论
	 */
	public void save(Comment comment) {
		String sql = "insert into comment values(?, ?, ?, ?, ?, ?, ?)";
		this.update(sql, comment.getId(), comment.getAuthor_id(), comment.getVisitor_username(), comment.getVisitor_email(), comment.getArticle_id(), comment.getContent(), comment.getCreateTime());
	}

	/**
	 * 查询文章所有评论并排序
	 */
	public List<Comment> findCommentByArticleIdSortList(String id) {
		String sql = "select * from comment where article_id = ? order by createTime ";
		return this.query(sql, id);
	}

}
