package com.nov.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.nov.bean.Article;
import com.nov.bean.Comment;
import com.nov.bean.Visitor;
import com.nov.dao.ArticleDao;
import com.nov.dao.CommentDao;
import com.nov.dao.VisitorDao;
import com.nov.dao.impl.ArticleDaoImpl;
import com.nov.dao.impl.CommentDaoImpl;
import com.nov.dao.impl.VisitorDaoImpl;
import com.nov.service.StageCommentService;

import cn.com.webxml.ValidateEmailWebService;
import cn.com.webxml.ValidateEmailWebServiceSoap;

/**
 * 前台评论模块实现类
 * @author nov
 *
 */
public class StageCommentServiceImpl implements StageCommentService {

	// 游客模块数据持久层
	private VisitorDao visitorDao = new VisitorDaoImpl();
	
	// 文章模块数据持久层
	private ArticleDao articleDao = new ArticleDaoImpl();
	
	// 评论模块数据持久层
	private CommentDao commentDao = new CommentDaoImpl();
	
	/**
	 * 验证邮箱有效性
	 */
	public Integer vaildateEmail(String email) {
		ValidateEmailWebService vews = new ValidateEmailWebService();
		ValidateEmailWebServiceSoap ve = vews.getValidateEmailWebServiceSoap();
		return (int) ve.validateEmailAddress(email);
	}

	/**
	 * 根据邮箱查询游客
	 */
	public Visitor findVisitorByEmail(String email) {
		return visitorDao.findVisitorByEmail(email);
	}

	/**
	 * 已存在游客发表评论
	 */
	public Comment saveExistVisitorSubmitComment(Comment comment) {
		// 获得游客实体,并设置到评论中
		Visitor visitor = visitorDao.findVisitorByEmail(comment.getVisitor_email());
		if (visitor == null) {
			return null;
		}
		comment.setVisitor_username(visitor.getUsername());
		
		// 检查文章和对应的博主是否正确
		Article article = articleDao.findArticleById(comment.getArticle_id());
		if (article == null) {
			return null;
		}
		
		if(article.getAuthor_id() != comment.getAuthor_id()) {
			return null;
		}
		
		//设置评论时间、ID
		comment.setCreateTime(new Date());
		comment.setId(UUID.randomUUID().toString());
		
		commentDao.save(comment);
		
		return comment;
	}

	/**
	 * 获得该文章的所有评论
	 */
	public List<Comment> findAllCommentByArticleId(String articleId) {
		// 判断文章是否存在
		Article article = articleDao.findArticleById(articleId);
		if (article == null) {
			return null;
		}
		
		// 获得文章所有评论
		List<Comment> commentList = commentDao.findCommentByArticleIdSortList(article.getId());
		
		return commentList;
	}

	/**
	 * 保存文章评论
	 */
	public void saveComment(Comment comment) {
		commentDao.save(comment);
		
	}

	

}
