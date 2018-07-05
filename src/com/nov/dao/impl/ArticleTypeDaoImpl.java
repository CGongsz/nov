package com.nov.dao.impl;

import java.util.List;

import com.nov.bean.ArticleType;
import com.nov.dao.ArticleTypeDao;
/**
 * 文章类型数据持久层实现类
 * @author nov
 *
 */
public class ArticleTypeDaoImpl extends BaseDaoImpl<ArticleType> implements ArticleTypeDao {

	/**
	 * 根据ID查询文章类型实体
	 */
	public ArticleType findArticleTypeById(Integer article_type_id) {
		String sql = "select * from articletype where typeId = ? ";
		List<ArticleType> list = this.query(sql, article_type_id);
		if(list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据博主ID查询该博主的所有文章类型实体
	 */
	public List<ArticleType> findArticleTypeByAuthorIdList(Integer id) {
		String sql = "select * from articletype where author_id = ? ";
		return this.query(sql, id);
	}

}
