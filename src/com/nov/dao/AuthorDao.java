package com.nov.dao;

import com.nov.bean.Author;

/**
 * 博主数据持久层接口
 * @author nov
 *
 */
public interface AuthorDao extends BaseDao<Author>{

	/**
	 * 根据用户名和密码查询博主实体
	 * @param username 用户名
	 * @param password 密码
	 * @return 如果存在返回博主实体，否则返回null
	 */
	public Author findAuthorByUsernameAndPassword(String username, String password);

}
