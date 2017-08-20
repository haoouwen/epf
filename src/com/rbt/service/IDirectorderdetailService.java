/*
  
 
 * Package:com.rbt.servie
 * FileName: IDirectorderdetailService.java 
 */
package com.rbt.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.browseengine.bobo.api.BrowseException;
import com.rbt.model.Directorderdetail;
import com.rbt.model.Refundapp;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 预售订单商品详细信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Wed Jul 17 13:26:38 CST 2014
 */

public interface IDirectorderdetailService extends IGenericService<Directorderdetail,String>{
	public Directorderdetail getByOrderId(String id);
	
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 12:50:31 PM
	 * @Method Description：构建店铺列表和商品列表
	 */
	public Map createList(Map map);
	
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 1:27:07 PM
	 * @Method Description：提交订单（实物商品）
	 */
	public String subOrder(Map map)throws IOException, ParseException, BrowseException;
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date Apr 16, 2014 11:22:36 AM
	 * @Method Description :提交虚拟订单
	 */
	public String subVirtualOrder(Map map) throws IOException, ParseException, BrowseException;
	
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 3:50:22 PM
	 * @Method Description：新增收货地址
	 */
	public String addAddr(Map map);
	
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 4:44:52 PM
	 * @Method Description：账户余额支付
	 */
    public void useNumPay(String[] order_id,String session_cust_id,String session_user_id,Double use_num_pay)throws Exception;
    
    /**	
	 * @author : HXK
	 * @throws Exception 
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台商品评价
	 */
	public void orderEvaluate(Map map)throws Exception;
	
	/**	
	 * @author : HXK
	 * @throws Exception 
	 * @date Mar 28, 2014 2:35:04 PM
	 * @Method Description :前台商品确认收货
	 */
	public boolean orderConfirmReceipt(String order_id,String pay_password,String session_cust_id,String session_user_id,Double use_num_pay)throws Exception;
	
	/**	
	 * @author : HXK
	 * @throws Exception 
	 * @date Mar 28, 2014 2:35:04 PM
	 * @Method Description :前台卖家退款申请-同意退款操作
	 */
	public boolean sellerAgreeRefundment(String order_id,String session_cust_id,String session_user_id,String pay_password,Double use_num_pay)throws Exception;
	
	/**	
	 * @author : HXK
	 * @throws Exception 
	 * @date Mar 28, 2014 2:35:04 PM
	 * @Method Description :前台买家退款申请-操作
	 */
	public void orderBuyRefundment(String order_id,Refundapp refundapp,String session_cust_id,String session_user_id)throws Exception;
	
	/**	
	 * @author : HXK
	 * @throws Exception 
	 * @date Mar 28, 2014 2:35:04 PM
	 * @Method Description :前台卖家退款申请-不同意退款操作
	 */
	public void sellerDisAgreeRefundment(String order_id,String seller_refund_reason,String session_cust_id,String session_user_id)throws Exception;
	
	/**	
	 * @author : CYC
	 * @throws Exception 
	 * @date Mar 28, 2014 2:35:04 PM
	 * @Method Description :确认收货打款给卖家
	 */
	public void sellerFundManage(String  oid,Double use_num_pay,String session_user_id,String directorder,String trade_id);

	public void onlinePay(String[] order_id, String buy_cust_id,
			String buy_user_id, Double use_num_pay, HttpServletResponse response) throws Exception;
    
	/**
	 * 删除订单详情
	 * @param order_id
	 * @return
	 */
	public int deleteByOrderId(String order_id);
}

