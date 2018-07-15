package com.nov.service.impl;

import com.nov.bean.Author;
import com.nov.dao.AuthorDao;
import com.nov.dao.impl.AuthorDaoImpl;
import com.nov.service.AuthorService;
import com.nov.utils.MD5Utils;

/**
 * 博主业务层实现类
 * @author nov
 *
 */
public class AuthorServiceImpl implements AuthorService {

	private AuthorDao authorDao = new AuthorDaoImpl();
	
	/**
	 * 根据博主ID查询博主实体
	 */
	public Author findAuthorById(Integer id) {
		return authorDao.findAuthorById(id);
	}

	/**
	 * 更新博主信息
	 */
	public void updateAuthor(Author oldAuthor, String username, String password, String introduction) {
		// 如果账号名不同，则更新账号名
		if(!username.equals(oldAuthor.getUsername())) {
			authorDao.updateUsername(oldAuthor.getId(),username);
		}
		
		password = MD5Utils.md5(password);
		
		// 密码不同则修改
		if(!password.equals(oldAuthor.getPassword())) {
			authorDao.updatePassword(oldAuthor.getId(), password);
		}
		
		// 介绍不同修改
		if(!introduction.equals(oldAuthor.getIntroduction())) {
			authorDao.updateIntroduction(oldAuthor.getId(), introduction);
		}
	}

}
