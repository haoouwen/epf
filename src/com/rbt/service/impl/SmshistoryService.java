/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SmshistoryService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISmshistoryDao;
import com.rbt.model.Smshistory;
import com.rbt.service.ISmshistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录短信发送历史记录Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 09 13:50:23 CST 2014
 */
@Service
public class SmshistoryService extends GenericService<Smshistory,String> implements ISmshistoryService {
	
	ISmshistoryDao smshistoryDao;

	@Autowired
	public SmshistoryService(ISmshistoryDao smshistoryDao) {
		super(smshistoryDao);
		this.smshistoryDao = smshistoryDao;
	}
	
}

