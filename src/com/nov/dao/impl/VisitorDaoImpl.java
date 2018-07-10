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

}
