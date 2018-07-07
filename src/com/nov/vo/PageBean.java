package com.nov.vo;

import java.util.List;

public class PageBean<T> {
	// 当前页
	private Integer currentPage;
	// 当前显示条数
	private Integer pageSize;
	// 当前数据总条数
	private Integer total;
	// 总页数
	private Integer totalPage;
	// 数据集合
	private List<T> rows;
	public PageBean() {
	}
	public PageBean(Integer currentPage, Integer pageSize, Integer total, List<T> rows) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.total = total;
		this.rows = rows;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
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
		// 设置总页数
		this.totalPage = (total + pageSize -1)/ pageSize;
		if(this.currentPage > this.totalPage) {
			this.currentPage--;
		}
		
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
