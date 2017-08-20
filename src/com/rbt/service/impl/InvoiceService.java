/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: InvoiceService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IInvoiceDao;
import com.rbt.model.Invoice;
import com.rbt.service.IInvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 发票打印Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Thu May 22 13:06:59 CST 2014
 */
@Service
public class InvoiceService extends GenericService<Invoice,String> implements IInvoiceService {
	
	IInvoiceDao invoiceDao;

	@Autowired
	public InvoiceService(IInvoiceDao invoiceDao) {
		super(invoiceDao);
		this.invoiceDao = invoiceDao;
	}
	
}

