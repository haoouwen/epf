/*
 
 * Package:com.rbt.dao.impl
 * FileName: ExchangeDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IExchangeDao;
import com.rbt.model.Exchange;
import com.rbt.model.Refundapp;

/**
 * @function 功能  换货dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Mon Jan 12 13:50:08 CST 2015
 */
@Repository
public class ExchangeDao extends GenericDao<Exchange,String> implements IExchangeDao {
	
	public ExchangeDao() {
		super(Exchange.class);
	}
	public Exchange getByOrderId(String order_id) {
		return (Exchange) this.getSqlMapClientTemplate().queryForObject("exchange.getByOrderId",order_id);
	}
	
}

