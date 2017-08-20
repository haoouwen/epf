/*
  
 
 * Package:com.rbt.service.impl
 * FileName: MessageService.java 
 */
package com.rbt.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMessageDao;
import com.rbt.model.Message;
import com.rbt.service.IMessageService;

@Service
public class MessageService extends GenericService<Message,String> implements IMessageService {

	IMessageDao messageDao;

	@Autowired
	public MessageService(IMessageDao messageDao) {
		super(messageDao);
		this.messageDao = messageDao;
	}
}
