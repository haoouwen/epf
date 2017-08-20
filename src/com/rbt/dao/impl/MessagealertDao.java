/*
 
 * Package:com.rbt.dao.impl
 * FileName: MessagealertDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMessagealertDao;
import com.rbt.model.Messagealert;

/**
 * @function 功能  记录信息提醒设置信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Sat Feb 02 10:29:52 CST 2014
 */
@Repository
public class MessagealertDao extends GenericDao<Messagealert,String> implements IMessagealertDao {
	
	public MessagealertDao() {
		super(Messagealert.class);
	}
	
}

