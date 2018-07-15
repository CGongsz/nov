package com.nov.utils;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ComPoolUtil {

	static ComboPooledDataSource dataSource = null;
	
	static{
		//初始化连接池的对象
		dataSource  = new ComboPooledDataSource();  //加载的是default-config 
	}
	
	
	/**
	 * 获取QueryRunnber
	 * @return
	 */
	public static QueryRunner getQueryRunner(){
		//核心控制器
		QueryRunner runner = new QueryRunner(dataSource);
		return runner;
	}
}