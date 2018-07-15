package com.nov.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.nov.dao.BaseDao;
import com.nov.utils.ComPoolUtil;
/**
 * 通用数据持久层实现类
 * @author nov
 *
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<T> classType;
	
	
	public BaseDaoImpl() {
		// 在基础dao中获取Class类型
		ParameterizedType genericSuperclass = (ParameterizedType)this.getClass().getGenericSuperclass();
		classType = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}
	
	/**
	 * 通用查询实现方法
	 */
	public List<T> query(String sql, Object... objects) {
		QueryRunner queryRunner = ComPoolUtil.getQueryRunner();
		List<T> list = null;
		try {
			list = queryRunner.query(sql, new BeanListHandler<T>(classType), objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 通用增、删、改的实现方法
	 */
	public void update(String sql, Object... objects) {
		QueryRunner queryRunner = ComPoolUtil.getQueryRunner();
		try {
			queryRunner.update(sql, objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 专用模糊查询方法
	 */
	public List<T> search(String sql, Object ...objects) {
		QueryRunner queryRunner = ComPoolUtil.getQueryRunner();
		List<T> list = null;
		for (int i = 0; i < objects.length; i++) {
			// 通配符设置
			objects[i] = '%' + objects[i].toString() + '%';
		}
		try {
			list = queryRunner.query(sql, new BeanListHandler<T>(classType), objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获得总记录数
	 */
	public Long findEntityNumber(String sql,Object ...objects) {
		QueryRunner queryRunner = ComPoolUtil.getQueryRunner();
		Long total = null;
		try {
			total  = (Long) queryRunner.query(sql, new ScalarHandler(), objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(total != null && total > 0) {
			return total;
		} else {
			return (long) 0;
		}
		
	}

	/**
	 * 获得实体某列
	 */
	public List<Object> findEntityOfOneColumn(String sql, String name ,Object ...objects) {
		QueryRunner queryRunner = ComPoolUtil.getQueryRunner();
		List<Object> columnList = null;
		try {
			columnList = queryRunner.query(sql, new ColumnListHandler<Object>(name), objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columnList;
	}

	

}
