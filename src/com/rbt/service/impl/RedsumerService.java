/*
 
 * Package:com.rbt.servie.impl
 * FileName: RedsumerService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IRedsumerDao;
import com.rbt.model.Redsumer;
import com.rbt.service.IRedsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 红包消费码Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Wed Aug 12 17:05:07 CST 2015
 */
@Service
public class RedsumerService extends GenericService<Redsumer,String> implements IRedsumerService {
	
	IRedsumerDao redsumerDao;

	@Autowired
	public RedsumerService(IRedsumerDao redsumerDao) {
		super(redsumerDao);
		this.redsumerDao = redsumerDao;
	}
	
}

