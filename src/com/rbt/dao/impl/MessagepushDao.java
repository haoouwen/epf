/*
 * Package:com.rbt.dao.impl
 * FileName: MessagepushDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMessagepushDao;
import com.rbt.model.Messagepush;

/**
 * @function 功能  消息推送dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Thu Jul 07 14:40:46 CST 2016
 */
@Repository
public class MessagepushDao extends GenericDao<Messagepush,String> implements IMessagepushDao {
	
	public MessagepushDao() {
		super(Messagepush.class);
	}
	
}

