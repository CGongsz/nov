package com.nov.bean;

import java.util.Date;

public class Article {
	private String id;
	// 该文章作者标识
	private Integer author_id;
	private Author author;
	// 文章标题
	private String title;
	private Date createTime;
	// 文章内容
	private String content;
	// 文章类型标识
	private Integer article_type_id;
	private ArticleType articleType;
	// 文章关键字
	private String keyword;
	// 点击量
	private Integer clickRate;
	public Article() {
	}
	public Article(String id, Integer author_id, Author author, String title, Date createTime, String content,
			Integer article_type_id, ArticleType articleType, String keyword, Integer clickRate) {
		super();
		this.id = id;
		this.author_id = author_id;
		this.author = author;
		this.title = title;
		this.createTime = createTime;
		this.content = content;
		this.article_type_id = article_type_id;
		this.articleType = articleType;
		this.keyword = keyword;
		this.clickRate = clickRate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getArticle_type_id() {
		return article_type_id;
	}
	public void setArticle_type_id(Integer article_type_id) {
		this.article_type_id = article_type_id;
	}
	public ArticleType getArticleType() {
		return articleType;
	}
	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getClickRate() {
		return clickRate;
	}
	public void setClickRate(Integer clickRate) {
		this.clickRate = clickRate;
	}
	
	
}
