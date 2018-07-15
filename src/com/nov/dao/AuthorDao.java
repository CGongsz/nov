package com.nov.dao;

import java.util.List;

import com.nov.bean.Article;
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

	/**
	 * 获得所有的博主实体
	 * @return 如果存在博主返回博主集合，否则返回null
	 */
	public List<Author> findAllAuthorList();

	/**
	 * 获得博主对象
	 * @param id
	 * @return
	 */
	public Author findAuthorById(Integer id);

	/**
	 * 更新博主账号
	 * @param integer 
	 * @param username
	 */
	public void updateUsername(Integer id, String username);

	/**
	 * 修改博主密码
	 * @param id
	 * @param password
	 */
	public void updatePassword(Integer id, String password);

	/**
	 * 修改博主介绍
	 * @param id
	 * @param introduction
	 */
	public void updateIntroduction(Integer id, String introduction);
	

}
