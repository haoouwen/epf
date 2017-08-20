/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MemberfundrunService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IMemberfundrunDao;
import com.rbt.model.Memberfundrun;
import com.rbt.service.IMemberfundrunService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 审核余额调整Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Mon Jun 09 15:36:58 CST 2014
 */
@Service
public class MemberfundrunService extends GenericService<Memberfundrun,String> implements IMemberfundrunService {
	
	IMemberfundrunDao memberfundrunDao;

	@Autowired
	public MemberfundrunService(IMemberfundrunDao memberfundrunDao) {
		super(memberfundrunDao);
		this.memberfundrunDao = memberfundrunDao;
	}
	
}

