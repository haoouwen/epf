/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: ShopconfigDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IShopconfigDao;
import com.rbt.model.Memberuser;
import com.rbt.model.Shopconfig;

/**
 * @function 功能  记录商城设置信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Fri Jan 04 10:10:44 CST 2014
 */
@Repository
public class ShopconfigDao extends GenericDao<Shopconfig,String> implements IShopconfigDao {
	
	public ShopconfigDao() {
		super(Shopconfig.class);
	}
	//通过cust_id获取shopconfig的对象
	public Shopconfig getByCustID(String cust_id) {
		return  (Shopconfig)this.getSqlMapClientTemplate().queryForObject("shopconfig.getByCustId", cust_id);
	}
	
}

