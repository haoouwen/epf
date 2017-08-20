/*
 
 * Package:com.rbt.dao.impl
 * FileName: ArticleDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IArticleDao;
import com.rbt.model.Article;

/**
 * @function 功能  记录文章管理表信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Feb 20 10:44:52 CST 2014
 */
@Repository
public class ArticleDao extends GenericDao<Article,String> implements IArticleDao {
	
	public ArticleDao() {
		super(Article.class);
	}
	
}

