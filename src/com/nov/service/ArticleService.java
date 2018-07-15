package com.nov.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nov.bean.Article;
import com.nov.vo.PageBean;

/**
 * 文章模块业务层接口
 * @author nov
 *
 */
public interface ArticleService {

	/**
	 * 查询博主所有文章的业务层方法
	 * @param id 查询的博主唯一标识
	 * @return 如果存在文章返回
	 */
	public List<Article> findAllArticleByAuthorIdList(Integer id);

	/**
	 * 将分页对象完善
	 * @param id 博主的唯一标识
	 * @param pageBean 分页对象
	 */
	public void improveArticlePageBean(Integer id, PageBean<Article> pageBean);

	/**
	 * 再文章实体中设置文章类型实体
	 * @param pageBean 分页对象
	 */
	public void setArticleTypeToArticle(PageBean<Article> pageBean);

	/**
	 * 根据文章的ID删除所有评论
	 * @param id 文章唯一标识
	 */
	public void deleteAllCommentOfArticle(String id);

	/**
	 * 根据文章的ID删除文章
	 * @param id 文章唯一标识
	 */
	public void deleteArticleById(String id,  HttpServletRequest request);

	/**
	 * 级联删除文章
	 * @param id 文章唯一标识
	 * @param request 
	 */
	public void cascadeDeleteArticle(String id, HttpServletRequest request);

	/**
	 * 保存编辑的文章
	 * @param art
	 */
	public void save(Article art);

	/**
	 * 判断博主的文章标题是否重复
	 * @param id 博主唯一标识
	 * @param title 文章标题
	 * @return
	 */
	public boolean exsitThisTitle(Integer id, String title);

	/**
	 * 根据搜索和条件来完善分页对象
	 * @param id 博主唯一标识
	 * @param search 模糊查询条件
	 * @param condition 搜索条件
	 * @param pageBean 
	 */
	public void improveArticlePageBeanByTerm(Integer id, String search, String condition, PageBean<Article> pageBean);

	/**
	 * 跟据ID查询文章
	 * @param articleId 
	 * @return
	 */
	public Article findArticleById(String articleId);

	/**
	 * 更新文章
	 * @param art
	 */
	public void update(Article art);



}
