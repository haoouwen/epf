/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SendmodeService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISendmodeDao;
import com.rbt.model.Member;
import com.rbt.model.Sendmode;
import com.rbt.service.ISendmodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 配送方式表Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Fri Mar 23 09:55:49 CST 2014
 */
@Service
public class SendmodeService extends GenericService<Sendmode,String> implements ISendmodeService {
	
	ISendmodeDao sendmodeDao;

	@Autowired
	public SendmodeService(ISendmodeDao sendmodeDao) {
		super(sendmodeDao);
		this.sendmodeDao = sendmodeDao;
	}
	
	/**
	 * @author : LJQ
	 * @date : May 31, 2014 2:30:24 PM
	 * @Method Description :获取名称
	 */
	public Sendmode getByEnname(String en_name) {
		return this.sendmodeDao.getByEnname(en_name);
	}
	
}

