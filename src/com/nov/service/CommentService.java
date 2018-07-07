package com.nov.service;

import java.util.List;

import com.nov.bean.Comment;
import com.nov.vo.PageBean;

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

	/**
	 * 将分页对象完善
	 * @param id 博主的唯一标识
	 * @param pageBean 分页对象
	 */
	public void improveCommentPageBean(Integer id, PageBean<Comment> pageBean);

	/**
	 * 对分页对象里的评论设置文章实体
	 * @param pageBean 分页对象
	 */
	public void setArticleOfComment(PageBean<Comment> pageBean);

	/**
	 * 删除评论
	 * @param id
	 */
	public void deleteCommentById(String id);

}
