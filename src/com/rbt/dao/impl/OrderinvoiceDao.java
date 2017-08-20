/*
 
 * Package:com.rbt.dao.impl
 * FileName: OrderinvoiceDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IOrderinvoiceDao;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderinvoice;
import com.rbt.model.Shopconfig;

/**
 * @function 功能  发票dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Thu Aug 13 13:00:29 CST 2015
 */
@Repository
public class OrderinvoiceDao extends GenericDao<Orderinvoice,String> implements IOrderinvoiceDao {
	
	public OrderinvoiceDao() {
		super(Orderinvoice.class);
	}
	public Orderinvoice getByCustID(String cust_id) {
		return  (Orderinvoice)this.getSqlMapClientTemplate().queryForObject("orderinvoice.getByCustId", cust_id);
	}
	 
    public Orderinvoice getByInvoid(String invoice_id){
    	return (Orderinvoice)this.getSqlMapClientTemplate().queryForObject("orderinvoice.getByInvoid",invoice_id);
    }
}

