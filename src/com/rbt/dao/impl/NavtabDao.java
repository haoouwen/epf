/*
 
 * Package:com.rbt.dao.impl
 * FileName: NavtabDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.INavtabDao;
import com.rbt.model.Navtab;

/**
 * @function 功能  导航标签信息dao层业务接口实现类
 * @author 创建人 ZMS
 * @date 创建日期 Wed Aug 12 20:56:05 CST 2015
 */
@Repository
public class NavtabDao extends GenericDao<Navtab,String> implements INavtabDao {
	
	public NavtabDao() {
		super(Navtab.class);
	}
	public Navtab getByTaxNumber(String tab_number) {
		return (Navtab)this.getSqlMapClientTemplate().queryForObject("navtab.getByTabNumber", tab_number);
	}
	
}

