/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: EmailhistoryService.java 
 */
package com.rbt.service.impl;
import com.rbt.dao.IEmailhistoryDao;
import com.rbt.model.Emailhistory;
import com.rbt.service.IEmailhistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录邮件发送历史记录Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 09 12:52:35 CST 2014
 */
@Service
public class EmailhistoryService extends GenericService<Emailhistory,String> implements IEmailhistoryService {
	
	IEmailhistoryDao emailhistoryDao;

	@Autowired
	public EmailhistoryService(IEmailhistoryDao emailhistoryDao) {
		super(emailhistoryDao);
		this.emailhistoryDao = emailhistoryDao;
	}
	
}

