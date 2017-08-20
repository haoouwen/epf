/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MessagealertService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IMessagealertDao;
import com.rbt.model.Messagealert;
import com.rbt.service.IMessagealertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录信息提醒设置信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Sat Feb 02 10:29:52 CST 2014
 */
@Service
public class MessagealertService extends GenericService<Messagealert,String> implements IMessagealertService {
	
	IMessagealertDao messagealertDao;

	@Autowired
	public MessagealertService(IMessagealertDao messagealertDao) {
		super(messagealertDao);
		this.messagealertDao = messagealertDao;
	}
	
}

