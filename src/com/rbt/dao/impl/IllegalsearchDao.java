/*
 
 * Package:com.rbt.dao.impl
 * FileName: IllegalsearchDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IIllegalsearchDao;
import com.rbt.model.Illegalsearch;

/**
 * @function 功能  前台搜索拦截信息dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Fri Apr 03 12:03:00 CST 2015
 */
@Repository
public class IllegalsearchDao extends GenericDao<Illegalsearch,String> implements IIllegalsearchDao {
	
	public IllegalsearchDao() {
		super(Illegalsearch.class);
	}
	
}

