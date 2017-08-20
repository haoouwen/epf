/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SmshistoryDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISmshistoryDao;
import com.rbt.model.Smshistory;

/**
 * @function 功能  记录短信发送历史记录dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 09 13:50:23 CST 2014
 */
@Repository
public class SmshistoryDao extends GenericDao<Smshistory,String> implements ISmshistoryDao {
	
	public SmshistoryDao() {
		super(Smshistory.class);
	}
	
}

