/*
  
 
 * Package:com.rbt.servie
 * FileName: IRefundappService.java 
 */
package com.rbt.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.rbt.model.Goodsorder;
import com.rbt.model.Refundapp;

/**
 * @function 功能 退款申请表Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Fri Mar 29 16:04:49 CST 2014
 */

public interface IRefundappService extends IGenericService<Refundapp,String>{
	
	public Refundapp getByOrderId(String order_id);
	/**
	 * @author : HZX
	 * @param type 
	 * @date : Feb 11, 2014 1:59:20 PM
	 * @Method Description :处理退款
	 */
	public void dealRefund(Map refundMap);
	/**
	 * @author : HZX
	 * @throws ParseException 
	 * @date : Feb 18, 2014 2:04:04 PM
	 * @Method Description :取消退款
	 */
	boolean cancelRefund(String order_id,String canceldesc,String o_type) throws ParseException;
	
	/**
	 * @Method Description :更新订单详情的 orderdetail_state订单详情商品状态表示0：正常，1，退款中，2：退款关闭，3：退款成功 
	 * @author: HXK
	 * @date : Jan 13, 2015 1:49:13 PM
	 * @param goods_is_str 商品ID串，order_state状态订单详情商品状态表示0：正常，1，退款中，2：退款关闭，3：退款成功
	 * @return return_type
	 */
	public void updateOrderDetailState(String goods_is_str,String order_state);
	
	//通过退款编号取得
	public Refundapp getByRefundNo(String refund_no);
	
	//通过支付宝退款批次号取得，仅在支付宝退款时调用
	public Refundapp getByBatchNo(String batch_no);
}

