/*
 
 * Package:com.rbt.dao.impl
 * FileName: PagetipDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IPagetipDao;
import com.rbt.model.Pagetip;

/**
 * @function 功能  记录页面显示管理信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Tue Jan 29 13:10:46 CST 2014
 */
@Repository
public class PagetipDao extends GenericDao<Pagetip,String> implements IPagetipDao {
	
	public PagetipDao() {
		super(Pagetip.class);
	}
	
	@SuppressWarnings("unchecked")
	public List getAll() {
		return this.getSqlMapClientTemplate().queryForList("pagetip.getAll");
	}
}

