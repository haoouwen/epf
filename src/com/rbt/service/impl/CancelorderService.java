/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: CancelorderService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ICancelorderDao;
import com.rbt.model.Cancelorder;
import com.rbt.service.ICancelorderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 取消订单理由Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Sat Jan 10 13:47:37 CST 2015
 */
@Service
public class CancelorderService extends GenericService<Cancelorder,String> implements ICancelorderService {
	
	ICancelorderDao cancelorderDao;

	@Autowired
	public CancelorderService(ICancelorderDao cancelorderDao) {
		super(cancelorderDao);
		this.cancelorderDao = cancelorderDao;
	}
	
	public Cancelorder getByOrderId(String order_id){
		return this.cancelorderDao.getByOrderId(order_id);
	}
}

