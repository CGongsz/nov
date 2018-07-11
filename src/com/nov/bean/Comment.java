package com.nov.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Comment implements Comparable<Comment>{
	private String id;
	// 评论的游客标识
	private String visitor_email;
	private String visitor_username;
	private Visitor visitor;
	// 评论的文章标识
	private String article_id;
	private Article article;
	// 评论内容
	private String content;
	// 评论时间
	@JSONField (format="yyyy-MM-dd HH:mm:ss") 
	private Date createTime;
	// 被评论文章的作者标识
	private Integer author_id;
	public Integer getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}
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
	public String getVisitor_username() {
		return visitor_username;
	}
	public void setVisitor_username(String visitor_username) {
		this.visitor_username = visitor_username;
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
	
	
	@Override
	public int compareTo(Comment o) {
		return this.getCreateTime().compareTo(o.getCreateTime());
	}
	
}
