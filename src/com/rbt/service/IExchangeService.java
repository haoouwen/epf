/*
  
 
 * Package:com.rbt.servie
 * FileName: IExchangeService.java 
 */
package com.rbt.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.rbt.model.Exchange;
import com.rbt.model.Refundapp;

/**
 * @function 功能 换货Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Mon Jan 12 13:50:08 CST 2015
 */

public interface IExchangeService extends IGenericService<Exchange,String>{
	public Exchange getByOrderId(String order_id);
}

