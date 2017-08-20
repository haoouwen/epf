/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: AutofckService.java 
 */
package com.rbt.service.impl;

import java.util.Map;

import com.rbt.dao.IAutofckDao;
import com.rbt.model.Autofck;
import com.rbt.service.IAutofckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 在线编辑器Service层业务接口实现
 * @author 创建人 LHY
 * @date 创建日期 Mon Jan 28 12:54:12 CST 2014
 */
@Service
public class AutofckService extends GenericService<Autofck,String> implements IAutofckService {
	
	IAutofckDao autofckDao;

	@Autowired
	public AutofckService(IAutofckDao autofckDao) {
		super(autofckDao);
		this.autofckDao = autofckDao;
	}

	public void updaterandom(Map map) {
		this.autofckDao.updaterandom(map);
		
	}

	public Autofck getrandom(Map map) {
		return this.autofckDao.getrandom(map);
	}

}

