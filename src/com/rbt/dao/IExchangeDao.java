/*
  
 
 * Package:com.rbt.dao
 * FileName: IExchangeDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Exchange;
import com.rbt.model.Refundapp;

/**
 * @function 功能 换货dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Mon Jan 12 13:50:08 CST 2015
 */

public interface IExchangeDao extends IGenericDao<Exchange,String>{
	public Exchange getByOrderId(String order_id);
}

