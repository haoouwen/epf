/*
 * Package:com.rbt.servie.impl
 * FileName: MessagepushService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IMessagepushDao;
import com.rbt.model.Messagepush;
import com.rbt.service.IMessagepushService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 消息推送Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Thu Jul 07 14:40:46 CST 2016
 */
@Service
public class MessagepushService extends GenericService<Messagepush,String> implements IMessagepushService {
	
	IMessagepushDao messagepushDao;

	@Autowired
	public MessagepushService(IMessagepushDao messagepushDao) {
		super(messagepushDao);
		this.messagepushDao = messagepushDao;
	}
	
}

