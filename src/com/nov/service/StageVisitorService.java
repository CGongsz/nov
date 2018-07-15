package com.nov.service;

import com.nov.bean.Visitor;

/**
 * 前台游客模块业务层接口
 * @author nov
 *
 */
public interface StageVisitorService {

	/**
	 * 判断是否存在用户名
	 * @param username
	 * @return
	 */
	public boolean existVisitorUsername(String username);

	/**
	 * 保存游客实体
	 * @param visitor
	 */
	public void save(Visitor visitor);

}
