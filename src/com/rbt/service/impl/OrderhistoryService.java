/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: OrderhistoryService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.IOrderhistoryDao;
import com.rbt.model.Orderhistory;
import com.rbt.service.IOrderhistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 订单状态历史记录Service层业务接口实现
 * @author 创建人 订单历史
 * @date 创建日期 Tue Nov 01 13:15:49 CST 2014
 */
@Service
public class OrderhistoryService extends GenericService<Orderhistory,String> implements IOrderhistoryService {

	IOrderhistoryDao orderhistoryDao;

	@Autowired
	public OrderhistoryService(IOrderhistoryDao orderhistoryDao) {
		super(orderhistoryDao);
		this.orderhistoryDao = orderhistoryDao;
	}
}

