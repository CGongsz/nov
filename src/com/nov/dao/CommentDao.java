package com.nov.dao;

import java.util.List;

import com.nov.bean.Comment;

/**
 * 评论模块数据持久层接口
 * @author nov
 *
 */
public interface CommentDao extends BaseDao<Comment>{

	/**
	 * 根据文章ID查询评论实体
	 * @param articleId 文章唯一标识
	 * @return 如果文章有评论返回集合，否则返回null
	 */
	public List<Comment> findCommentByArticleId(String articleId);
	
}
