package com.nov.vo;

import java.util.List;

public class PageBean {
	// 当前页
	private Integer currentPage;
	// 当前显示条数
	private Integer pageSize;
	// 当前数据总条数
	private Integer total;
	// 数据集合
	private List rows;
	
	public PageBean() {
	}
	public PageBean(Integer currentPage, Integer pageSize, Integer total, List rows) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.total = total;
		this.rows = rows;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
}
