package com.nov.service;

import java.util.List;

import com.nov.bean.Article;

/**
 * 文章模块业务层接口
 * @author nov
 *
 */
public interface ArticleService {

	/**
	 * 查询博主所有文章的业务层方法
	 * @param 查询的博主ID
	 * @return 如果存在文章返回
	 */
	public List<Article> findAllArticleByAuthorIdList(Integer id);

}
