/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: GoodsspreadService.java 
 */
package com.rbt.service.impl;


import com.rbt.dao.IGoodsspreadDao;
import com.rbt.model.Goodsspread;
import com.rbt.service.IGoodsspreadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商品推广信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Mar 20 13:21:09 CST 2014
 */
@Service
public class GoodsspreadService extends GenericService<Goodsspread,String> implements IGoodsspreadService {
	
	IGoodsspreadDao goodsspreadDao;

	@Autowired
	public GoodsspreadService(IGoodsspreadDao goodsspreadDao) {
		super(goodsspreadDao);
		this.goodsspreadDao = goodsspreadDao;
	}
	
}

