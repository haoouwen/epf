/*
  
 
 * Package:com.rbt.dao
 * FileName: IMemberinterDao.java 
 */
package com.rbt.dao;

import java.util.Map;

import com.rbt.model.Interhistory;
import com.rbt.model.Memberinter;

/**
 * @function 功能 记录会员积分数dao层业务接口
 * @author  创建人CYC
 * @date  创建日期 Thu Jul 14 14:30:38 CST 2014
 */

public interface IMemberinterDao extends IGenericDao<Memberinter,String>{
	//跟新积分
	public void updateinter(Interhistory interhistory,Memberinter memberinter);
	
	public Double getSumCount(Map map);
}

