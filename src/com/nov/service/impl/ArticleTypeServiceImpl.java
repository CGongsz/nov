package com.nov.service.impl;

import java.util.List;

import com.nov.bean.ArticleType;
import com.nov.dao.ArticleTypeDao;
import com.nov.dao.impl.ArticleTypeDaoImpl;
import com.nov.service.ArticleTypeService;

/**
 * 文章类型模块业务层实现类
 * @author nov
 *
 */
public class ArticleTypeServiceImpl implements ArticleTypeService {

	// 文章类型模块的数据持久层
	private ArticleTypeDao articleTypeDao = new ArticleTypeDaoImpl();
	
	public List<ArticleType> findArticleTypeByAuthorIdList(Integer id) {
		return articleTypeDao.findArticleTypeByAuthorIdList(id);
	}


}
