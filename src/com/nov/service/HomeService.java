package com.nov.service;

import java.util.List;

import com.nov.bean.Article;
import com.nov.bean.ArticleType;
import com.nov.bean.Author;

/**
 * 首页模块的业务层接口
 * @author nov
 *
 */
public interface HomeService {

	/**
	 * 获得所有的博主实体
	 * @return 存在博主返回集合，否则返回null
	 */
	public List<Author> getAllBlogAuthorList();

	/**
	 * 根据博主获取所有的文章类型实体
	 * @param id 博主唯一标识
	 * @return 如果存在返回文章类型集合，否则返回null
	 */
	public List<ArticleType> findArticleTypeByAuthorIdList(Integer id);

	/**
	 * 根据博主查询点击量前十的文章
	 * @param id 博主唯一标识
	 * @return 如果博主存在文章返回文章集合，否则返回null
	 */
	public List<Article> findTenOfArticleByClickRate(Integer id);

	/**
	 * 获得博主对象
	 * @param id
	 * @return
	 */
	public Author findAuthorById(Integer id);

	
	/**
	 * 通过博主id 和 关键字搜索到博主的文章
	 * @return
	 */
	public List<Article> findArticleByKeyWord(int idStr, String keyWord);
	

}
