package com.nov.dao.impl;

import java.util.List;

import com.nov.bean.Author;
import com.nov.dao.AuthorDao;

/**
 * 博主数据持久层实现类
 * @author nov
 *
 */
public class AuthorDaoImpl extends BaseDaoImpl<Author> implements AuthorDao{

	/**
	 * 根据用户名和密码查询实体
	 */
	public Author findAuthorByUsernameAndPassword(String username, String password) {
		String sql = "select * from author where username = ? and password = ? ";
		// 调用基类dao
		List<Author> list = this.query(sql, username, password);
		if(list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
		
	}

}
