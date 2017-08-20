/*
 
 * Package:com.rbt.servie.impl
 * FileName: ComsumerService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IComsumerDao;
import com.rbt.model.Comsumer;
import com.rbt.service.IComsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 优惠券消费码Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Wed Aug 12 14:55:34 CST 2015
 */
@Service
public class ComsumerService extends GenericService<Comsumer,String> implements IComsumerService {
	
	IComsumerDao comsumerDao;

	@Autowired
	public ComsumerService(IComsumerDao comsumerDao) {
		super(comsumerDao);
		this.comsumerDao = comsumerDao;
	}
	
}

