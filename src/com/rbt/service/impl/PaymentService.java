/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: PaymentService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import com.rbt.dao.IPaymentDao;
import com.rbt.model.Payment;
import com.rbt.service.IPaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录平台支付方式信息Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Mon Oct 24 10:57:44 CST 2014
 */
@Service
public class PaymentService extends GenericService<Payment,String> implements IPaymentService {

	
	IPaymentDao paymentDao;

	@Autowired
	public PaymentService(IPaymentDao paymentDao) {
		super(paymentDao);
		this.paymentDao = paymentDao;
	}

}

