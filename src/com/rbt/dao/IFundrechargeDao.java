/*
  
 
 * Package:com.rbt.dao
 * FileName: IFundrechargeDao.java 
 */
package com.rbt.dao;

import com.rbt.model.Fundrecharge;

/**
 * @function 功能 会员资金充值记录dao层业务接口
 * @author  创建人CYC
 * @date  创建日期 Tue Jul 12 13:10:48 CST 2014
 */

public interface IFundrechargeDao extends IGenericDao<Fundrecharge,String>{
	public Fundrecharge getByOrderId(String order_id)throws Exception;
	public Fundrecharge getByTrxid(String trxid)throws Exception;
}

