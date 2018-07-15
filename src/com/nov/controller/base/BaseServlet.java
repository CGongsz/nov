package com.nov.controller.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nov.vo.PageBean;

public class BaseServlet<T> extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/**
	 * 设置初始化分页对象属性
	 * @param request
	 * @param response
	 * @return
	 */
	protected PageBean<T> getPageBean(HttpServletRequest request, HttpServletResponse response) {
		// 获得当前页和当前显示条数
		String currentPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		
		PageBean<T> pageBean = new PageBean<T>();
		
		// 非空判断
		if(currentPage != null && !"".equals(currentPage)) {
			pageBean.setCurrentPage(Integer.parseInt(currentPage));
		} else {
			// 默认第一页
			pageBean.setCurrentPage(1);
		}
		
		if(pageSize != null && !"".equals(pageSize)) {
			pageBean.setPageSize(Integer.parseInt(pageSize));
		} else {
			// 默认显示5条
			pageBean.setPageSize(5);
		}
		
		return pageBean;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
