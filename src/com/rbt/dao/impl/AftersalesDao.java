/*
 
 * Package:com.rbt.dao.impl
 * FileName: AftersalesDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IAftersalesDao;
import com.rbt.model.Aftersales;

/**
 * @function 功能  记录售后维护信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Tue Feb 26 16:32:24 CST 2014
 */
@Repository
public class AftersalesDao extends GenericDao<Aftersales,String> implements IAftersalesDao {
	
	public AftersalesDao() {
		super(Aftersales.class);
	}
	
}

