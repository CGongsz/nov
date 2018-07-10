package com.nov.dao;

import java.util.List;

import com.nov.bean.Article;
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

	/**
	 * 根据博主ID查询该博主所有文章评论总记录数
	 * @param id 博主唯一标识
	 * @return 返回评论总记录数
	 */
	public Long findCommentTotal(Integer id);

	/**
	 * 根据分页对象里的数据和博主ID查询对应的部分数据
	 * @param id 博主唯一标识
	 * @param index limit偏移量
	 * @param size 查询记录条数
	 * @return 返回查询文章集合
	 */
	public List<Comment> findRowsByIndexSizeAndAuthorId(Integer id, Integer index, Integer size);

	/**
	 * 删除评论
	 * @param id 评论的唯一标识
	 */
	public void deleteCommentById(String id);

	/**
	 * 根据文章ID删除所有评论
	 * @param id 文章的唯一标识
	 */
	public void deleteAllCommentOfArticle(String id);

	/**
	 * 保存评论
	 * @param comment
	 */
	public void save(Comment comment);

	/**
	 * 查询文章所有评论并排序
	 * @param id
	 * @return
	 */
	public List<Comment> findCommentByArticleIdSortList(String id);
	
}
