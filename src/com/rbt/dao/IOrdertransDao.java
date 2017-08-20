/*
  
 
 * Package:com.rbt.dao
 * FileName: IOrdertransDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Ordertrans;

/**
 * @function 功能 记录商品订单异动信息dao层业务接口
 * @author  创建人HXK
 * @date  创建日期 Thu Feb 28 10:02:15 CST 2014
 */

public interface IOrdertransDao extends IGenericDao<Ordertrans,String>{
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 4:44:52 PM
	 * @Method Description：删除订单详情
	 */
	public int deleteByOrderId(String order_id);
}

