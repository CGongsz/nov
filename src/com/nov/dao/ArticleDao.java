package com.nov.dao;

import java.util.List;

import com.nov.bean.Article;
import com.nov.vo.PageBean;

/**
 * 文章管理模块数据持久层
 * @author nov
 *
 */
public interface ArticleDao extends BaseDao<Article>{

	/**
	 * 查询博主所有文章的数据持久层方法
	 * @param id 博主的ID
	 * @return 如果存在返回集合,否则返回null
	 */
	public List<Article> findAllArticleByAuthorIdList(Integer id);

	/**
	 * 查询博主所有文章的ID
	 * @param id 博主的唯一标识
	 * @return 如果存在文章返回集合，否则返回null
	 */
	public List<String> findAllArticleIdByAuthorIdList(Integer id);


	/**
	 * 根据博主ID查询博主所写的文章数
	 * @param id 博主唯一标识
	 * @return 返回文章数量
	 */
	public Long findArticleTotal(Integer id);

	/**
	 * 根据分页对象里的数据和博主ID查询对应的部分数据
	 * @param id 博主唯一标识
	 * @param index limit偏移量
	 * @param size 查询记录条数
	 * @return 返回查询文章集合
	 */
	public List<Article> findRowsByIndexSizeAndAuthorId(Integer id, Integer index, Integer size);

	/**
	 * 根据文章ID查询文章实体
	 * @param article_id 文章唯一标识
	 * @return 返回文章实体
	 */
	public Article findArticleById(String article_id);

	/**
	 * 根据文章ID删除文章
	 * @param id 文章唯一标识
	 */
	public void deleteArticleById(String id);

	/**
	 * 根据文章类型ID查询该类型所有文章ID
	 * @param id 文章类型唯一标识
	 * @return 文章ID集合
	 */
	public List<Object> findArticleIdByArticleTypeIdList(Integer id);
	
	/**
	 * 保存文章
	 * @param art
	 */
	public void save(Article art);
	
}
