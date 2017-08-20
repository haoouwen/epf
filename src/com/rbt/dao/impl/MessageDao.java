package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IMessageDao;
import com.rbt.model.Message;

@Repository
public class MessageDao extends GenericDao<Message,String> implements IMessageDao {
	
	public MessageDao() {
		super(Message.class);
	}
}
