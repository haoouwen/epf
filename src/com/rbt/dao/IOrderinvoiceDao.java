/*
 
 * Package:com.rbt.dao
 * FileName: IOrderinvoiceDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memberuser;
import com.rbt.model.Orderinvoice;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 发票dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Thu Aug 13 13:00:29 CST 2015
 */

public interface IOrderinvoiceDao extends IGenericDao<Orderinvoice,String>{
	Orderinvoice getByCustID(String cust_id);
	Orderinvoice getByInvoid(String invoice_id);
}

