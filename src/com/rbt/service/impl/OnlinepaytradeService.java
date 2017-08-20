/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: OnlinepaytradeService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IOnlinepaytradeDao;
import com.rbt.model.Onlinepaytrade;
import com.rbt.service.IOnlinepaytradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录网银支付流水信息Service层业务接口实现
 * @author 创建人 WXP
 * @date 创建日期 Fri Dec 07 14:55:14 CST 2014
 */
@Service
public class OnlinepaytradeService extends GenericService<Onlinepaytrade,String> implements IOnlinepaytradeService {
	
	IOnlinepaytradeDao onlinepaytradeDao;

	@Autowired
	public OnlinepaytradeService(IOnlinepaytradeDao onlinepaytradeDao) {
		super(onlinepaytradeDao);
		this.onlinepaytradeDao = onlinepaytradeDao;
	}
	
}

