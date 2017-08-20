/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SearchfilterDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISearchfilterDao;
import com.rbt.model.Searchfilter;

/**
 * @function 功能  前台搜索过滤规则dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Fri Apr 03 11:11:49 CST 2015
 */
@Repository
public class SearchfilterDao extends GenericDao<Searchfilter,String> implements ISearchfilterDao {
	
	public SearchfilterDao() {
		super(Searchfilter.class);
	}
	
}

