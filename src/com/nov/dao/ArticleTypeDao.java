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

	/**
	 * 根据博主ID查询该博主的文章分类总记录数
	 * @param id 博主ID
	 * @return 文章分类总记录数
	 */
	public Long findArticleTypeTotal(Integer id);

	/**
	 * 根据分页对象里的数据和博主ID查询对应的部分数据
	 * @param id 博主唯一标识
	 * @param index limit偏移量
	 * @param size 查询记录条数
	 * @return 返回查询文章类型集合
	 */
	public List<ArticleType> findRowsByIndexSizeAndAuthorId(Integer id, Integer index, Integer size);

	/**
	 * 删除文章类型
	 * @param id 文章类型唯一标识
	 */
	public void deleteArticleTypeById(Integer id);

	/**
	 * 保存文章类型
	 * @param articleType 文章类型实体
	 */
	public void saveArticleType(ArticleType articleType);

	/**
	 * 查询文章类型的类型名字段集合
	 * @param id
	 * @return
	 */
	public List<Object> findArticleTypeOfTypeNameByAuthorIdList(Integer id);


}
