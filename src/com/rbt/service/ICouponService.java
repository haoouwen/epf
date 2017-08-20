/*
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ICouponService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Coupon;

/**
 * @function 功能 优惠券Service层业务接口实现类
 * @author  创建人 XBY
 * @date  创建日期 Fri Aug 07 14:37:49 CST 2015
 */

public interface ICouponService extends IGenericService<Coupon,String>{
	
	/**
	 * 下载优惠券
	 * @param num 优惠券数量
	 * @param coupon_id 优惠券ID
	 * @return
	 * @throws Exception
	 */
	public boolean downCoupon(String num, String coupon_id) throws Exception;
	/**
	 * @Method Description :通过商品ID 获得前台支持领取优惠券
	 * @author: 胡惜坤
	 * @date : Nov 5, 2015 5:15:07 PM
	 * @param 
	 * @return List
	 */
	public List getCouponByGoodsID(String  goods_id,String menberlevel);
	/**
	 * @Method Description :通过商品ID 获取商品参加几个优惠券
	 * @author: 胡惜坤
	 * @date : Nov 5, 2015 5:15:07 PM
	 * @param 
	 * @return List
	 */
	public List getCouponByGoodsList(List coupongoodsList);
	
}

