package com.nov.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nov.bean.Article;
import com.nov.bean.Comment;
import com.nov.dao.ArticleDao;
import com.nov.dao.CommentDao;
import com.nov.dao.impl.ArticleDaoImpl;
import com.nov.dao.impl.CommentDaoImpl;
import com.nov.service.CommentService;
import com.nov.vo.PageBean;

/**
 * 评论业务层实现类
 * @author nov
 *
 */
public class CommentServiceImpl implements CommentService {

	// 注入文章模块数据持久层
	private ArticleDao articleDao = new ArticleDaoImpl();
	
	// 注入评论模块数据持久层
	private CommentDao commentDao = new CommentDaoImpl();
	/**
	 * 根据博主ID获得博主所有文章的评论
	 */
	public List<Comment> findAllCommentByAuthorIdList(Integer id) {
		// 根据博主ID查询博主所有文章类型的ID
		/*List<Article> articleList = articleDao.findAllArticleByAuthorIdList(id);
		for (Article article : articleList) {
			// 获得每篇文章的评论
			List<Comment> aArticleCommentList = commentDao.findCommentByArticleId(article.getId());
			// 将评论对应的文章设置进去
			for (Comment comment : aArticleCommentList) {
				comment.setArticle(article);
			}
			
			commentList.addAll(aArticleCommentList);
		}
		// 评论实体实现了Comparable接口，将评论集合排序，将评论按时间排序
		Collections.sort(commentList);
		Collections.reverse(commentList);
		*/
		
		// 根据博主ID查询博主文章所有的评论并按评论时间排序
	/*	List<Article> articleList = articleDao.findAllArticleOfCommentByAuthorIdList(id);*/
		
		return null;
	}

	/**
	 * 将文章模块的分页对象完善的业务层实现方法
	 */
	public void improveCommentPageBean(Integer id, PageBean<Comment> pageBean) {
		Long total = commentDao.findCommentTotal(id);
		pageBean.setTotal(total.intValue());
		Integer size = pageBean.getPageSize();
		Integer index = (pageBean.getCurrentPage() - 1) * size;
		
		if(index < 0) {
			index = 0;
		}
		List<Comment> rows = commentDao.findRowsByIndexSizeAndAuthorId(id, index, size);
		pageBean.setRows(rows);
	}

	/**
	 * 对分页对象里的评论设置文章实体
	 */
	public void setArticleOfComment(PageBean<Comment> pageBean) {
		List<Comment> rows = pageBean.getRows();
		if(rows == null) {
			return;
		}
		for (Comment comment : rows) {
			Article article = articleDao.findArticleById(comment.getArticle_id());
			comment.setArticle(article);
		}
	}

	/**
	 * 删除评论
	 */
	public void deleteCommentById(String id) {
		commentDao.deleteCommentById(id);
	}
	
	

}
