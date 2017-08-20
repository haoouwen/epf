/*
 * Package:com.rbt.servie.impl
 * FileName: FreegoodsService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IFreegoodsDao;
import com.rbt.model.Freegoods;
import com.rbt.service.IFreegoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 赠品Service层业务接口实现
 * @author 创建人 ZMS
 * @date 创建日期 Tue Sep 29 17:12:09 CST 2015
 */
@Service
public class FreegoodsService extends GenericService<Freegoods,String> implements IFreegoodsService {
	
	IFreegoodsDao freegoodsDao;

	@Autowired
	public FreegoodsService(IFreegoodsDao freegoodsDao) {
		super(freegoodsDao);
		this.freegoodsDao = freegoodsDao;
	}
	
}

