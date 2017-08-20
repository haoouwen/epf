/*
  
 
 * Package:com.rbt.dao
 * FileName: IRefundappDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Member;
import com.rbt.model.Refundapp;

/**
 * @function 功能 退款申请表dao层业务接口
 * @author  创建人HXK
 * @date  创建日期 Fri Mar 29 16:04:49 CST 2014
 */

public interface IRefundappDao extends IGenericDao<Refundapp,String>{
	
	
	public Refundapp getByOrderId(String order_id);
	
	public Refundapp getByRefundNo(String refund_no);
	
	public Refundapp getByBatchNo(String batch_no);
	
}

