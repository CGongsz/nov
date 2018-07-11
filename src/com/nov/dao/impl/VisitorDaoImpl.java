package com.nov.dao.impl;

import java.util.List;

import com.nov.bean.Visitor;
import com.nov.dao.VisitorDao;

/**
 * 游客数据持久层实现类
 * @author nov
 *
 */
public class VisitorDaoImpl extends BaseDaoImpl<Visitor> implements VisitorDao {

	/**
	 * 根据邮箱查询游客
	 */
	public Visitor findVisitorByEmail(String email) {
		String sql = "select * from visitor where email = ? ";
		List<Visitor> list = this.query(sql, email);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 查询所有游客用户名
	 */
	public List<Object> findAllVisitorUsername(String username) {
		String sql = "select * from visitor ";
		return this.findEntityOfOneColumn(sql, "username");
	}

	/**
	 * 保存游客
	 */
	public void save(Visitor visitor) {
		String sql = "insert into visitor values(?, ?) ";
		this.update(sql, visitor.getEmail(), visitor.getUsername());
		
	}

}
