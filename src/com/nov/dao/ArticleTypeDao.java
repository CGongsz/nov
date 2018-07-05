package com.nov.dao;

import java.util.List;

import com.nov.bean.ArticleType;
/**
 * 文章类型数据持久层接口
 * @author nov
 *
 */
public interface ArticleTypeDao extends BaseDao<ArticleType>{

	/**
	 * 根据文章类型的ID查询文章类型实体
	 * @param article_type_id 文章类型的唯一标识
	 * @return 如果查询的ID存在返回文章类型实体，否则返回null
	 */
	public ArticleType findArticleTypeById(Integer article_type_id);

	/**
	 * 根据博主ID查询属于该博主的文章类型实体
	 * @param id 博主的唯一标识
	 * @return 如果存在博主文章类型实体返回集合，否则返回null
	 */
	public List<ArticleType> findArticleTypeByAuthorIdList(Integer id);

}
