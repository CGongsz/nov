package com.nov.service;

import java.util.List;

import com.nov.bean.ArticleType;

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

}
