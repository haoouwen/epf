/*
  
 
 * Package:com.rbt.dao
 * FileName: IMembercreditDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Membercredit;

/**
 * @function 功能 会员信誉dao层业务接口
 * @author  创建人XBY
 * @date  创建日期 Tue Apr 22 19:59:28 CST 2014
 */

public interface IMembercreditDao extends IGenericDao<Membercredit,String>{
	
	/**
	 * 通过UserId获取会员信誉
	 * @param membercredit
	 * @return
	 */
	public Membercredit getByCustId(String cust_id);
	
	/**
	 * 修改买家信誉
	 * @param membercredit
	 */
	public void updateBuyNum(Membercredit membercredit);
}

