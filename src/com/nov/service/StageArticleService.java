package com.nov.service;

import java.util.List;

import com.nov.bean.Article;
import com.nov.bean.Author;

/**
 * 前台文章业务层的接口
 * @author nov
 *
 */
public interface StageArticleService {

	/**
	 * 根据文章类型查询所有文章
	 * @param typeId 
	 * @return
	 */
	public List<Article> findArticleByArticleTypeIdList(Integer typeId);

	/**
	 * 查询博主
	 * @param authorId
	 * @return
	 */
	public Author findAuthorById(Integer authorId);

	/**
	 * 根据ID查询文章
	 * @param articleIdStr
	 * @return
	 */
	public Article findArticleById(String articleIdStr);

	/**
	 * 点击量加一
	 * @param articleIdStr
	 * @param integer 
	 */
	public void updateArticleClickRateById(String articleIdStr, Integer integer);

}
