package com.nov.dao;

import java.util.List;

import com.nov.bean.Article;

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
	
}
