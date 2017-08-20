/*
  
 
 * Package:com.rbt.dao
 * FileName: IDirectorderdetailDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Directorderdetail;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 预售订单商品详细信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Wed Jul 17 13:26:38 CST 2014
 */

public interface IDirectorderdetailDao extends IGenericDao<Directorderdetail,String>{
	Directorderdetail getByOrderId(String order_id);
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 4:44:52 PM
	 * @Method Description：删除订单详情
	 */
	public int deleteByOrderId(String order_id);
}

