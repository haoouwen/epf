/*
 
 * Package:com.rbt.dao.impl
 * FileName: TaxrateDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ITaxrateDao;
import com.rbt.model.Taxrate;

/**
 * @function 功能  税率信息dao层业务接口实现类
 * @author 创建人 ZMS
 * @date 创建日期 Tue Aug 18 16:12:24 CST 2015
 */
@Repository
public class TaxrateDao extends GenericDao<Taxrate,String> implements ITaxrateDao {
	
	public TaxrateDao() {
		super(Taxrate.class);
	}
	
	public List getDeleteList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("taxrate.getDeleteList",map);
	}
	
	public List getAll() {
		return this.getSqlMapClientTemplate().queryForList("taxrate.getAll");
	}
}

