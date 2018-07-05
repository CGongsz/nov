package com.nov.dao;

import java.util.List;

/**
 * 顶级数据持久层接口
 * @author nov
 *
 */
public interface BaseDao<T> {
	/**
	 * 查询方法通用接口
	 * @param sql 查询的sql语句
	 * @param objects sql语句的参数
	 * @return 如果查询结果存在返回集合，否则集合为null
	 */
	public List<T> query(String sql, Object ...objects);
	
	/**
	 * 增、删、改通用接口
	 * @param sql 增、删、改sql语句
	 * @param objects sql语句的参数
	 */
	public void update(String sql, Object ...objects);
	
	/**
	 * 专用模糊查询的通用接口
	 * @param sql 模糊查询的sql语句
	 * @param objects 查询关键词
	 * @return 如果查询结果存在返回集合，否则集合为null
	 */
	public List<T> search(String sql, Object ...objects);
}
