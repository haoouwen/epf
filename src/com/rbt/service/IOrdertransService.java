/*
  
 
 * Package:com.rbt.servie
 * FileName: IOrdertransService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Ordertrans;

/**
 * @function 功能 记录商品订单异动信息Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Thu Feb 28 10:02:15 CST 2014
 */

public interface IOrdertransService extends IGenericService<Ordertrans,String>{
	/**
	 * 删除订单详情
	 * @param order_id
	 * @return
	 */
	public int deleteByOrderId(String order_id);
	
	
	public void  inserOrderTran(String order_id,String cust_id,String user_id,String reason,String order_state,String opt_username);
}

