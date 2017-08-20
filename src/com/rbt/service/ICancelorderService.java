/*
  
 
 * Package:com.rbt.servie
 * FileName: ICancelorderService.java 
 */
package com.rbt.service;

import com.rbt.model.Cancelorder;

/**
 * @function 功能 取消订单理由Service层业务接口实现类
 * @author  创建人 XBY
 * @date  创建日期 Sat Jan 10 13:47:37 CST 2015
 */

public interface ICancelorderService extends IGenericService<Cancelorder,String>{
	public Cancelorder getByOrderId(String order_id);
}

