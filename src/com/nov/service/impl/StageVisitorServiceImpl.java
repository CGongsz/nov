package com.nov.service.impl;

import java.util.List;

import com.nov.bean.Visitor;
import com.nov.dao.VisitorDao;
import com.nov.dao.impl.VisitorDaoImpl;
import com.nov.service.StageVisitorService;

/**
 * 前台游客业务层实现类
 * @author nov
 *
 */
public class StageVisitorServiceImpl implements StageVisitorService {

	// 游客模块数据持久层
	private VisitorDao visitorDao = new VisitorDaoImpl();
	
	/**
	 * 判断是否存在该用户名
	 */
	public boolean existVisitorUsername(String username) {
		List<Object> usernameList = visitorDao.findAllVisitorUsername(username);
		if (usernameList == null) {
			return false;
		}
		for (Object object : usernameList) {
			if (username.equals(object.toString())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 保存游客实体
	 */
	public void save(Visitor visitor) {
		visitorDao.save(visitor);
	}

}
