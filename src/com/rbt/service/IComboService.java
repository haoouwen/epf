/*
  
 
 * Package:com.rbt.servie
 * FileName: IComboService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Combo;

/**
 * @function 功能 套餐表Service层业务接口实现类
 * @author  创建人 LHY
 * @date  创建日期 Mon Mar 25 15:09:17 CST 2014
 */

public interface IComboService extends IGenericService<Combo,String>{
	public String getComboGoodsid(String goods_id);
	
	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 1:06:22 PM
	 * @Method Description：所有商品价格
	 */
	public Double allPrice(List goodsList,Double all_goods_price);
	
	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 1:11:22 PM
	 * @Method Description：获取用户名称
	 */
	public String getUserName(List list);
}

