/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SendshipmodeService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISendshipmodeDao;
import com.rbt.model.Sendmode;
import com.rbt.model.Sendshipmode;
import com.rbt.service.ISendshipmodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 快递方式Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Tue May 06 11:17:02 CST 2014
 */
@Service
public class SendshipmodeService extends GenericService<Sendshipmode,String> implements ISendshipmodeService {
	
	ISendshipmodeDao sendshipmodeDao;

	@Autowired
	public SendshipmodeService(ISendshipmodeDao sendshipmodeDao) {
		super(sendshipmodeDao);
		this.sendshipmodeDao = sendshipmodeDao;
	}
	/**
	 * @author : LJQ
	 * @date : May 31, 2014 2:30:24 PM
	 * @Method Description :获取名称
	 */
	public Sendshipmode getByEnname(String en_name) {
		return this.sendshipmodeDao.getByEnname(en_name);
	}
	
}

