/*
  
 
 * Package:com.rbt.dao
 * FileName: IInterhistoryDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Interhistory;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;

/**
 * @function 功能 记录会员积分异动历史dao层业务接口
 * @author  创建人CYC
 * @date  创建日期 Thu Jul 14 15:01:09 CST 2014
 */

public interface IInterhistoryDao extends IGenericDao<Interhistory,String>{
	
	/**
	 * @Method Description : 获取每天会员所得的积分数
	 * @author : LJQ
	 * @date : Nov 11, 2014 2:18:22 PM
	 */
	@SuppressWarnings("unchecked")
	public int getInterhistorySumScore(Map map);
	/**
	 * @Method Description :运营商找出需要删除的ID值并减掉积分
	 * @author : LJQ
	 * @date : Nov 16, 2014 1:48:15 PM
	 */
	public List getReleaseCustId(Map map);
}

