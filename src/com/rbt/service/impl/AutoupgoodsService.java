/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: AutoupgoodsService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.IAutoupgoodsDao;
import com.rbt.model.Autoupgoods;
import com.rbt.service.IAutoupgoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商品上下架管理信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Fri Feb 01 10:46:02 CST 2014
 */
@Service
public class AutoupgoodsService extends GenericService<Autoupgoods,String> implements IAutoupgoodsService {
	
	IAutoupgoodsDao autoupgoodsDao;

	@Autowired
	public AutoupgoodsService(IAutoupgoodsDao autoupgoodsDao) {
		super(autoupgoodsDao);
		this.autoupgoodsDao = autoupgoodsDao;
	}
	public void deleteByGoodsId(String id){
		this.autoupgoodsDao.deleteByGoodsId(id);
	}
}

