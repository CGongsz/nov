package com.nov.service.impl;

import java.util.List;

import com.nov.bean.ArticleType;
import com.nov.dao.ArticleDao;
import com.nov.dao.ArticleTypeDao;
import com.nov.dao.CommentDao;
import com.nov.dao.impl.ArticleDaoImpl;
import com.nov.dao.impl.ArticleTypeDaoImpl;
import com.nov.dao.impl.CommentDaoImpl;
import com.nov.service.ArticleTypeService;
import com.nov.vo.PageBean;

/**
 * 文章类型模块业务层实现类
 * @author nov
 *
 */
public class ArticleTypeServiceImpl implements ArticleTypeService {

	// 文章类型模块的数据持久层
	private ArticleTypeDao articleTypeDao = new ArticleTypeDaoImpl();
	
	// 文章模块的数据持久层
	private ArticleDao articleDao = new ArticleDaoImpl();
	
	// 评论模块的数据持久层
	private CommentDao commentDao = new CommentDaoImpl();
	
	public List<ArticleType> findArticleTypeByAuthorIdList(Integer id) {
		return articleTypeDao.findArticleTypeByAuthorIdList(id);
	}

	/**
	 * 将文章模块的分页对象完善的业务层实现方法
	 */
	public void improvePageBean(Integer id, PageBean<ArticleType> pageBean) {
		Long total = articleTypeDao.findArticleTypeTotal(id);
		pageBean.setTotal(total.intValue());
		Integer size = pageBean.getPageSize();
		Integer index = (pageBean.getCurrentPage() - 1) * size;
		List<ArticleType> rows = articleTypeDao.findRowsByIndexSizeAndAuthorId(id, index, size);
		pageBean.setRows(rows);
	}

	/**
	 * 根据文章类型获得该类型所有文章
	 */
	public List<Object> findArticleIdByArticleTypeIdList(Integer id) {
		return articleDao.findArticleIdByArticleTypeIdList(id);
	}

	/**
	 * 批量删除文章
	 */
	public void deleteArticleById(List<Object> articleIdList) {
		for (Object object : articleIdList) {
			String id = object.toString();
			// 级联删除
			commentDao.deleteAllCommentOfArticle(id);
			articleDao.deleteArticleById(id);
		}
	}

	/**
	 * 删除文章类型
	 */
	public void deleteArticleTypeById(Integer id) {
		articleTypeDao.deleteArticleTypeById(id);
	}

	/**
	 * 级联删除文章类型
	 */
	public void cascadeDeleteArticleType(Integer id) {
		// 根据ID获得属于该文章类型的文章ID集合
		List<Object> articleIdList = findArticleIdByArticleTypeIdList(id);
		// 批量删除文章
		deleteArticleById(articleIdList);
		deleteArticleTypeById(id);
	}


}
