package com.nov.bean;

import java.util.Date;

public class Comment {
	private String id;
	// 评论的游客标识
	private String visitor_email;
	private Visitor visitor;
	// 评论的文章标识
	private String article_id;
	private Article article;
	// 评论内容
	private String content;
	// 评论时间
	private Date createTime;
	public Comment() {
	}
	public Comment(String id, String visitor_email, Visitor visitor, String article_id, Article article, String content,
			Date createTime) {
		super();
		this.id = id;
		this.visitor_email = visitor_email;
		this.visitor = visitor;
		this.article_id = article_id;
		this.article = article;
		this.content = content;
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVisitor_email() {
		return visitor_email;
	}
	public void setVisitor_email(String visitor_email) {
		this.visitor_email = visitor_email;
	}
	public Visitor getVisitor() {
		return visitor;
	}
	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
