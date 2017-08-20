/*
  
 
 * Package:com.rbt.servie
 * FileName: ICartgoodsService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Cartgoods;

/**
 * @function 功能 购物车Service层业务接口实现类
 * @author  创建人 WXP
 * @date  创建日期 Mon May 13 14:10:06 CST 2014
 */

public interface ICartgoodsService extends IGenericService<Cartgoods,String>{
	
	//修改购物车
	public void updateCustId(Cartgoods cart);
	
	/**	
	 * @author : WXP
	 * @param :
	 * @date May 13, 2014 1:02:09 PM
	 * @Method Description :加入购物车
	 */
	public String addCartGoods(Cartgoods cartgoods,String session_cust_id);
	/**	
	 * @author : WXP
	 * @param :trade_id
	 * @date May 15, 2014 9:52:09 AM
	 * @Method Description :删除购物车商品
	 */
	public String delCartGoods(String trade_id);
}

