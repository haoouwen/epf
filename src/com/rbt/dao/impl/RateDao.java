/*
 
 * Package:com.rbt.dao.impl
 * FileName: RateDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.rbt.dao.IRateDao;
import com.rbt.model.Rate;

/**
 * @function 功能  汇率信息dao层业务接口实现类
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 21 12:46:55 CST 2014
 */
@Repository
public class RateDao extends GenericDao<Rate,String> implements IRateDao {
	
	public RateDao() {
		super(Rate.class);
	}
	public void updateendefault() {
		this.getSqlMapClientTemplate().update("rate.updateendefault");
	}
	
	@SuppressWarnings("unchecked")
	public List getAll() {
		return this.getSqlMapClientTemplate().queryForList("rate.getAll");
	}
}

