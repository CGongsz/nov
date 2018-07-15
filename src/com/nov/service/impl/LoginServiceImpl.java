package com.nov.service.impl;

import java.util.List;

import com.nov.bean.Author;
import com.nov.dao.AuthorDao;
import com.nov.dao.impl.AuthorDaoImpl;
import com.nov.service.LoginService;
import com.nov.utils.MD5Utils;
/**
 * 登录模块业务层实现类
 * @author nov
 *
 */
public class LoginServiceImpl implements LoginService {

	// 博主数据池久层对象
	private AuthorDao authorDao = new AuthorDaoImpl();
	
	/**
	 * 根据用户名和密码查询博主实体
	 */
	public Author findAuthorByUsernameAndPassword(String username, String password) {
		// 对密码进行md5加密
		password = MD5Utils.md5(password);
		return authorDao.findAuthorByUsernameAndPassword(username, password);
	}

}
