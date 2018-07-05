package com.nov.service;

import com.nov.bean.Author;

/**
 * 登录模块的业务层接口
 * @author nov
 *
 */
public interface LoginService {

	/**
	 * 根据用户名和密码查询博主实体
	 * @param username 用户名
	 * @param password 密码
	 * @return 如果存在返回博主实体，否则返回null
	 */
	Author findAuthorByUsernameAndPassword(String username, String password);
	
}
