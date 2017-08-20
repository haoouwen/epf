/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ShopreplymsgService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IShopreplymsgDao;
import com.rbt.model.Shopreplymsg;
import com.rbt.service.IShopreplymsgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 店铺留言表Service层业务接口实现
 * @author 创建人 LHY
 * @date 创建日期 Thu Feb 28 15:36:59 CST 2014
 */
@Service
public class ShopreplymsgService extends GenericService<Shopreplymsg,String> implements IShopreplymsgService {
	
	IShopreplymsgDao shopreplymsgDao;

	@Autowired
	public ShopreplymsgService(IShopreplymsgDao shopreplymsgDao) {
		super(shopreplymsgDao);
		this.shopreplymsgDao = shopreplymsgDao;
	}

	public Shopreplymsg getByMsgId(String id) {
		return this.shopreplymsgDao.getByMsgId(id);
	}

	
	
}

