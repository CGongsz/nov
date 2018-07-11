package com.nov.service;


import java.util.List;

import com.nov.bean.Comment;
import com.nov.bean.Visitor;

/**
 * 前台评论模块业务层接口
 * @author nov
 *
 */
public interface StageCommentService {

	/**
	 * 对邮箱进行验证
	 * @param email
	 * @return
	 */
	public Integer vaildateEmail(String email);

	/**
	 * 根据邮箱查询游客
	 * @param email
	 * @return
	 */
	public Visitor findVisitorByEmail(String email);

	/**
	 * 已存游客发表评论
	 * @param comment 
	 * @return
	 */
	public Comment saveExistVisitorSubmitComment(Comment comment);

	/**
	 * 获得该文章的所有评论
	 * @param articleId
	 * @return
	 */
	public List<Comment> findAllCommentByArticleId(String articleId);

	/**
	 * 保存评论
	 * @param comment
	 */
	public void saveComment(Comment comment);

}
