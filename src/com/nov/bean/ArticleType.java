package com.nov.bean;

public class ArticleType {
	private Integer typeId;
	// 类型名
	private String typeName;
	// 类型信息
	private String info;
	// 所属作者
	private Integer author_id;
	private Author author;
	public ArticleType() {
	}
	public ArticleType(Integer typeId, String typeName, String info, Integer author_id, Author author) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.info = info;
		this.author_id = author_id;
		this.author = author;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
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
	
	
}
