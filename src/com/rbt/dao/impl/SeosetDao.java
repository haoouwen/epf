/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SeosetDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISeosetDao;
import com.rbt.model.Seoset;

/**
 * @function 功能  SEO优化表dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Fri Jan 04 11:06:43 CST 2014
 */
@Repository
public class SeosetDao extends GenericDao<Seoset,String> implements ISeosetDao {
	
	public SeosetDao() {
		super(Seoset.class);
	}
	
}

