package com.nov.service.impl;

import java.util.List;

import com.nov.bean.Article;
import com.nov.bean.ArticleType;
import com.nov.bean.Author;
import com.nov.dao.ArticleDao;
import com.nov.dao.ArticleTypeDao;
import com.nov.dao.AuthorDao;
import com.nov.dao.impl.ArticleDaoImpl;
import com.nov.dao.impl.ArticleTypeDaoImpl;
import com.nov.dao.impl.AuthorDaoImpl;
import com.nov.service.HomeService;
/**
 * 首页模块业务层的实现类
 * @author nov
 *
 */
public class HomeServiceImpl implements HomeService {

	// 博主数据持久层
	private AuthorDao authorDao = new AuthorDaoImpl();

	// 文章类型数据持久层
	private ArticleTypeDao articleTypeDao = new ArticleTypeDaoImpl();
	
	// 文章数据持久层
	private ArticleDao articleDao = new ArticleDaoImpl();
	/**
	 * 获得所有的博主实体
	 */
	public List<Author> getAllBlogAuthorList() {
		List<Author> authorList = authorDao.findAllAuthorList();
		return authorList;
	}

	/**
	 * 获得该博主的所有文章类型
	 */
	public List<ArticleType> findArticleTypeByAuthorIdList(Integer id) {
		return articleTypeDao.findArticleTypeByAuthorIdList(id);
	}

	/**
	 * 获得点击量前十的文章
	 */
	public List<Article> findTenOfArticleByClickRate(Integer id) {
		List<Article> articleList = articleDao.findTenOfArticleByClickRate(id);
		if (articleList != null && articleList.size() > 0) {
			for (Article article : articleList) {
				ArticleType articleType = articleTypeDao.findArticleTypeById(article.getArticle_type_id());
				article.setArticleType(articleType);
			}
		}
		return articleList;
	}

	/**
	 * 获得博主对象
	 */
	public Author findAuthorById(Integer id) {
		return authorDao.findAuthorById(id);
	}

	/**
	 * 关键字获得该博主的文章
	 */
	public List<Article> findArticleByKeyWord(int idStr, String keyWord) {
		return articleDao.finArticleByKeyWord(idStr, keyWord);
	}


}
