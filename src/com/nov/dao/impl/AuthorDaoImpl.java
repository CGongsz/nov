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

	/**
	 * 查询所有的博主实体
	 */
	public List<Author> findAllAuthorList() {
		String sql = "select * from author ";
		return this.query(sql);
	}

	/**
	 * 获得博主对象
	 */
	public Author findAuthorById(Integer id) {
		String sql = "select * from author where id = ?";
		List<Author> list = this.query(sql, id);
		
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
		
	}

	/**
	 * 更新博主账号
	 */
	public void updateUsername(Integer id, String username) {
		String sql = "update author set username = ? where id = ? ";
		this.update(sql, username, id);
	}

	/**
	 * 修改博主密码
	 */
	public void updatePassword(Integer id, String password) {
		String sql = "update author set password = ? where id = ? ";
		this.update(sql, password, id);
	}

	/**
	 * 修改博主介绍
	 */
	public void updateIntroduction(Integer id, String introduction) {
		String sql = "update author set introduction = ? where id = ? ";
		this.update(sql, introduction, id);
	}

}
