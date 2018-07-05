package com.nov.dao.impl;

import java.util.List;

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

}
