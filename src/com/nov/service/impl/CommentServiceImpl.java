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
	 * 根据博主ID获得所有
	 */
	public List<Comment> findAllCommentByAuthorIdList(Integer id) {
		List<Comment> commentList = new ArrayList<Comment>();
		// 根据博主ID查询博主所有文章类型的ID
		List<Article> articleList = articleDao.findAllArticleByAuthorIdList(id);
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
		
		return commentList;
	}
	
	

}
