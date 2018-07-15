package com.nov.service;

import com.nov.bean.Author;

/**
 * 博主业务层接口
 * @author nov
 *
 */
public interface AuthorService {

	/**
	 * 根据博主ID查询博主实体
	 * @param id
	 * @return
	 */
	public Author findAuthorById(Integer id);
	
	/**
	 * 更新博主信息
	 * @param oldAuthor
	 * @param username
	 * @param password
	 * @param introduction
	 */
	public void updateAuthor(Author oldAuthor, String username, String password, String introduction);

}
