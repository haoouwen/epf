/*
  
 
 * Package:com.rbt.servie
 * FileName: IOrderdetailService.java 
 */
package com.rbt.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Groupgoods;
import com.rbt.model.Orderdetail;
import com.rbt.model.Refundapp;

/**
 * @function 功能 记录订单商品详细信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Fri Jan 11 16:38:19 CST 2014
 */

public interface IOrderdetailService extends IGenericService<Orderdetail,String>{
	/**
	 * @author : HZX
	 * @date : Feb 14, 2014 11:00:08 AM
	 * @Method Description :判断是否团购
	 */
	boolean isGroup(Groupgoods groupgoods, String order_num_str);
	/**
	 * @author : HZX
	 * @param user_id 
	 * @param cust_id 
	 * @param request
	 * @throws Exception 
	 * @date : Feb 14, 2014 1:52:41 PM
	 * @Method Description :获取地区html
	 */
	String getAddrDiv(HttpServletRequest request, String cust_id, String user_id) throws Exception;
	/**
	 * @author : HZX
	 * @throws Exception 
	 * @date : Feb 18, 2014 10:20:06 AM
	 * @Method Description :生成订单
	 */
	String addOrder(Map order_varMap, HttpServletResponse response) throws Exception;
	/**
	 * @author : HZX
	 * @date : Feb 18, 2014 12:42:10 PM
	 * @Method Description :更新库存
	 */
	void updateStock(char c, String string, String goods_id_str,
			String spec_id_str, int parseInt);
	/**
	 * @author : HZX
	 * @date : Feb 18, 2014 4:16:54 PM
	 * @Method Description :更新退款操作
	 */
	void updateRefund(Refundapp refundapp, String seller_state,
			String session_user_id,String refund_state,String reason);
	/**
	 * @author : HZX
	 * @date : Feb 19, 2014 1:56:35 PM
	 * @Method Description :评价
	 */
	public void saveEval(Map evalMap);
	String useNumPay(String session_user_id, String buy_cust_id,
			String[] order_id) throws Exception;
	public int getBuyCount(Map<String,String> map);
	
	public String updateAddrDiv(HttpServletRequest request,String cust_id,String user_id) throws Exception;
	
	public void updateState(Map map);
	
	/**
	 * 删除订单详情
	 * @param order_id
	 * @return
	 */
	public int deleteByOrderId(String order_id);
	/**
	 * @Method Description :更新商品库存 下订单 支付完成减少库存
	 * @author: HXK
	 * @date : Oct 31, 2015 1:54:42 PM
	 * @param 
	 * @return return_type
	 */
	public void updateStockBypayment(String orderId);
	/**
	 * @Method Description :更新商品库存 下订单 支付完成减少库存
	 * @author: HXK
	 * @date : Oct 31, 2015 1:54:42 PM
	 * @param 
	 * @return return_type
	 */
	@SuppressWarnings("unchecked")
	public boolean updateStockByOnlinepayment(String orderId);
	/**
	 * @Method Description :在线付款的时候 检查库存是否够
	 * @author: HXK
	 * @param 
	 * @return return_type
	 */
	@SuppressWarnings("unchecked")
	public boolean checkGoodsNum(String orderId);

	/**
	 * @Method Description :更新商品库存 支付完成 会员取消订单
	 * @author: HXK
	 * @date : Oct 31, 2015 1:54:42 PM
	 * @param 
	 * @return return_type
	 */
	@SuppressWarnings("unchecked")
	public boolean addStockByCancleOrder(String orderId);
}

