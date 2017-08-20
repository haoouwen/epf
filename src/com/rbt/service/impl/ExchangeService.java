/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ExchangeService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IExchangeDao;
import com.rbt.model.Exchange;
import com.rbt.model.Refundapp;
import com.rbt.service.IExchangeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 换货Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Mon Jan 12 13:50:08 CST 2015
 */
@Service
public class ExchangeService extends GenericService<Exchange,String> implements IExchangeService {
	
	IExchangeDao exchangeDao;

	@Autowired
	public ExchangeService(IExchangeDao exchangeDao) {
		super(exchangeDao);
		this.exchangeDao = exchangeDao;
	}
	public Exchange getByOrderId(String order_id)  {
		return this.exchangeDao.getByOrderId(order_id);
	}
}

