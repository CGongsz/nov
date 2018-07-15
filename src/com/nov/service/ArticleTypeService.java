package com.nov.service;

import java.util.List;

import com.nov.bean.ArticleType;
import com.nov.vo.PageBean;

/**
 * 文章类型模块业务层接口
 * @author nov
 *
 */
public interface ArticleTypeService {

	/**
	 * 根据博主ID查询文章类型实体集合
	 * @param integer 博主ID
	 * @return 如果有数据返回集合，否则返回null
	 */
	public List<ArticleType> findArticleTypeByAuthorIdList(Integer integer);

	/**
	 * 将分页对象完善
	 * @param id 博主的唯一标识
	 * @param pageBean 分页对象
	 */
	public void improvePageBean(Integer id, PageBean<ArticleType> pageBean);

	/**
	 * 根据文章类型ID查询属于该类型的所有文章ID
	 * @param id 文章类型唯一标识
	 * @return 返回该类型所有文章的ID集合
	 */
	public List<Object> findArticleIdByArticleTypeIdList(Integer id);

	/**
	 * 批量删除文章
	 * @param articleIdList 文章ID集合
	 */
	public void deleteArticleById(List<Object> articleIdList);

	/**
	 * 删除文章类型
	 * @param id 文章类型唯一标识
	 */
	public void deleteArticleTypeById(Integer id);

	/**
	 * 级联删除文章类型
	 * @param id 文章类型ID
	 */
	public void cascadeDeleteArticleType(Integer id);

	/**
	 * 保存文章类型
	 * @param articleType 文章类型实体
	 */
	public void saveArticleType(ArticleType articleType);

	/**
	 * 判断是否存在该文章类型
	 * @param id
	 * @param typeName
	 */
	public boolean exsitThisTypeName(Integer id, String typeName);

}
