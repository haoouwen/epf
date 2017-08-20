/*
 
 * Package:com.rbt.dao.impl
 * FileName: PaymentDao.java 
 */
package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IPaymentDao;
import com.rbt.model.Payment;

/**
 * @function 功能  记录平台支付方式信息dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Mon Oct 24 10:57:44 CST 2014
 */
@Repository
public class PaymentDao extends GenericDao<Payment,String> implements IPaymentDao {

	public PaymentDao() {
		super(Payment.class);
	}
}

