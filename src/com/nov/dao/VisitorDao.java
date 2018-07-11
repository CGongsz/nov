package com.nov.dao;

import java.util.List;

import com.nov.bean.Visitor;

/**
 * 游客数据持久层接口
 * @author nov
 *
 */
public interface VisitorDao extends BaseDao<Visitor> {

	/**
	 * 根据邮箱查询游客
	 * @param email 
	 * @return
	 */
	public Visitor findVisitorByEmail(String email);

	/**
	 * 查询所有游客的用户名
	 * @param username 
	 * @return
	 */
	public List<Object> findAllVisitorUsername(String username);

	/**
	 * 保存游客
	 * @param visitor
	 */
	public void save(Visitor visitor);

}
