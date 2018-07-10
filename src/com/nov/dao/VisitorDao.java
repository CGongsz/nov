package com.nov.dao;

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

}
