/*
 
 * Package:com.rbt.servie.impl
 * FileName: SalegoodsService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISalegoodsDao;
import com.rbt.model.Salegoods;
import com.rbt.service.ISalegoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 商品促销信息Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Tue Aug 11 09:31:09 CST 2015
 */
@Service
public class SalegoodsService extends GenericService<Salegoods,String> implements ISalegoodsService {
	
	ISalegoodsDao salegoodsDao;

	@Autowired
	public SalegoodsService(ISalegoodsDao salegoodsDao) {
		super(salegoodsDao);
		this.salegoodsDao = salegoodsDao;
	}
	
}

