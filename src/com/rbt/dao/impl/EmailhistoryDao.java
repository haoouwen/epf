/*
 
 * Package:com.rbt.dao.impl
 * FileName: EmailhistoryDao.java 
 */
package com.rbt.dao.impl;


import org.springframework.stereotype.Repository;
import com.rbt.dao.IEmailhistoryDao;
import com.rbt.model.Emailhistory;

/**
 * @function 功能  记录邮件发送历史记录dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 09 12:52:35 CST 2014
 */
@Repository
public class EmailhistoryDao extends GenericDao<Emailhistory,String> implements IEmailhistoryDao {
	
	public EmailhistoryDao() {
		super(Emailhistory.class);
	}
	
}

