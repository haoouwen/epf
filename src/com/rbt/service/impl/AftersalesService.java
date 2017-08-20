/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: AftersalesService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.IAftersalesDao;
import com.rbt.model.Aftersales;
import com.rbt.service.IAftersalesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录售后维护信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Tue Feb 26 16:32:24 CST 2014
 */
@Service
public class AftersalesService extends GenericService<Aftersales,String> implements IAftersalesService {
	
	IAftersalesDao aftersalesDao;

	@Autowired
	public AftersalesService(IAftersalesDao aftersalesDao) {
		super(aftersalesDao);
		this.aftersalesDao = aftersalesDao;
	}
	
}

