/*
  
 
 * Package:com.rbt.dao
 * FileName: IOrderdetailDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Orderdetail;

/**
 * @function 功能 记录订单商品详细信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Fri Jan 11 16:38:19 CST 2014
 */

public interface IOrderdetailDao extends IGenericDao<Orderdetail,String>{
	public int getBuyCount(Map<String,String> map);
	public void updateState(Map map);
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 4:44:52 PM
	 * @Method Description：删除订单详情
	 */
	public int deleteByOrderId(String order_id);
}

