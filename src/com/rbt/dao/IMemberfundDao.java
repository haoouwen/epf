/*
  
 
 * Package:com.rbt.dao
 * FileName: IMemberfundDao.java 
 */
package com.rbt.dao;

import java.util.HashMap;
import java.util.Map;
import com.rbt.model.Memberfund;

/**
 * @function 功能 会员资金dao层业务接口
 * @author  创建人CYC
 * @date  创建日期 Tue Jul 12 09:26:58 CST 2014
 */

public interface IMemberfundDao extends IGenericDao<Memberfund,String>{

	/**
	 * 方法描述：按照map中的条件修改会员资金信息
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public void updateMemberfund(Map map);
	//修改会员资金
	public void insertfundoption(String cust_id,String session_user_id,String fund_num);
	//运营商平台的总资金,包括总资金，可用总资金，冻结总资金
	public HashMap getTotalFund();
	
}

