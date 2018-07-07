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

	/**
	 * 文章类型总记录数
	 */
	public Long findArticleTypeTotal(Integer id) {
		String sql = "select count(*) from articletype where author_id = ? ";
		return this.findEntityNumber(sql, id);
	}

	/**
	 * 查询文章类型部分数据
	 */
	public List<ArticleType> findRowsByIndexSizeAndAuthorId(Integer id, Integer index, Integer size) {
		String sql = "select * from articletype where author_id = ? limit ?,? ";
		return this.query(sql, id, index, size);
	}

	/**
	 * 删除文章类型
	 */
	public void deleteArticleTypeById(Integer id) {
		String sql = "delete from articletype where typeId = ? ";
		this.update(sql, id);
	}

}
