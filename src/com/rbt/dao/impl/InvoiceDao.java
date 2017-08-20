/*
 
 * Package:com.rbt.dao.impl
 * FileName: InvoiceDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IInvoiceDao;
import com.rbt.model.Invoice;

/**
 * @function 功能  发票打印dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Thu May 22 13:06:59 CST 2014
 */
@Repository
public class InvoiceDao extends GenericDao<Invoice,String> implements IInvoiceDao {
	
	public InvoiceDao() {
		super(Invoice.class);
	}
	
}

