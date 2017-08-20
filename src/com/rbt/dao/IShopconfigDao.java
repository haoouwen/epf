/*
  
 
 * Package:com.rbt.dao
 * FileName: IShopconfigDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Shopconfig;

/**
 * @function 功能 记录商城设置信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Fri Jan 04 10:10:44 CST 2014
 */

public interface IShopconfigDao extends IGenericDao<Shopconfig,String>{

	Shopconfig getByCustID(String cust_id);
	
}

