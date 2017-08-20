/*
 * Package:com.rbt.dao
 * FileName: IRechargeablecardsDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Rechargeablecards;

/**
 * @function 功能 充值卡dao层业务接口
 * @author  创建人CYC
 * @date  创建日期 Sun Oct 11 14:01:50 CST 2015
 */

public interface IRechargeablecardsDao extends IGenericDao<Rechargeablecards,String>{
	/**
	 * @Method Description :通过key找到充值卡
	 * @author : CYC
	 * @date : Apr 19, 2014 11:01:04 AM
	 */
	public Rechargeablecards getCardkey(String readkey);
}

