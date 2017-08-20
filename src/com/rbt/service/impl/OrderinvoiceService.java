/*
 
 * Package:com.rbt.servie.impl
 * FileName: OrderinvoiceService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IOrderinvoiceDao;
import com.rbt.model.Orderinvoice;
import com.rbt.model.Shopconfig;
import com.rbt.service.IOrderinvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 发票Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Thu Aug 13 13:00:29 CST 2015
 */
@Service
public class OrderinvoiceService extends GenericService<Orderinvoice,String> implements IOrderinvoiceService {
	
	IOrderinvoiceDao orderinvoiceDao;

	@Autowired
	public OrderinvoiceService(IOrderinvoiceDao orderinvoiceDao) {
		super(orderinvoiceDao);
		this.orderinvoiceDao = orderinvoiceDao;
	}
	public Orderinvoice getByCustID(String cust_id){
		return  (Orderinvoice) this.orderinvoiceDao.getByCustID(cust_id);
	}
	public Orderinvoice getByInvoid(String invoice_id)
	{
		return (Orderinvoice) this.orderinvoiceDao.getByInvoid(invoice_id);
	}
}

