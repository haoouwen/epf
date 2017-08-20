/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: RefundappDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IRefundappDao;
import com.rbt.model.Refundapp;

/**
 * @function 功能  退款申请表dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Fri Mar 29 16:04:49 CST 2014
 */
@Repository
public class RefundappDao extends GenericDao<Refundapp,String> implements IRefundappDao {
	
	public RefundappDao() {
		super(Refundapp.class);
	}
	public Refundapp getByOrderId(String order_id) {
		return (Refundapp) this.getSqlMapClientTemplate().queryForObject("refundapp.getByOrderId",order_id);
	}
	
	//
	public Refundapp getByRefundNo(String refund_no){
		return (Refundapp) this.getSqlMapClientTemplate().queryForObject("refundapp.getByRefundNo",refund_no);

	}
	//
	public Refundapp getByBatchNo(String batch_no){
		return (Refundapp) this.getSqlMapClientTemplate().queryForObject("refundapp.getByBatchNo",batch_no);

	}
}

