/*
  
 
 * Package:com.rbt.dao
 * FileName: IMemprotectDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memprotect;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 记录会员密保信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Thu Feb 21 09:21:06 CST 2014
 */

public interface IMemprotectDao extends IGenericDao<Memprotect,String>{
	Memprotect getByCustID(String cust_id);
}

