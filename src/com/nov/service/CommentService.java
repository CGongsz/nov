package com.nov.service;

import java.util.List;

import com.nov.bean.Comment;

/**
 * 评论模块业务层接口
 * @author nov
 *
 */
public interface CommentService {

	/**
	 * 根据博主ID查询所有的评论
	 * @param id 博主的唯一标识
	 * @return 如果存在评论返回集合，否则返回null
	 */
	public List<Comment> findAllCommentByAuthorIdList(Integer id);

}
