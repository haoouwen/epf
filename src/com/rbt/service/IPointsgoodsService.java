/*
  
 
 * Package:com.rbt.servie
 * FileName: IPointsgoodsService.java 
 */
package com.rbt.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.browseengine.bobo.api.BrowseException;
import com.rbt.model.Pointsgoods;
import com.rbt.model.Refundapp;

/**
 * @function 功能 记录积分商品信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Mon Mar 25 16:00:03 CST 2014
 */

public interface IPointsgoodsService extends IGenericService<Pointsgoods,String>{
	/**
	 * @author：CYC
	 * @date：Feb 17, 2014 10:43:56 AM
	 * @Method Description：积分详细页面
	 */
	public Map detail(String directs_id)throws Exception;
	
	/**
	 * @author：CYC
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
	 * @author：CYC
	 * @date：Feb 18, 2014 4:44:52 PM
	 * @Method Description：积分支付
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
}

