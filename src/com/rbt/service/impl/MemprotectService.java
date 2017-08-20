/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MemprotectService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IMemprotectDao;
import com.rbt.model.Memprotect;
import com.rbt.model.Shopconfig;
import com.rbt.service.IMemprotectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录会员密保信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 21 09:21:06 CST 2014
 */
@Service
public class MemprotectService extends GenericService<Memprotect,String> implements IMemprotectService {
	
	IMemprotectDao memprotectDao;

	@Autowired
	public MemprotectService(IMemprotectDao memprotectDao) {
		super(memprotectDao);
		this.memprotectDao = memprotectDao;
	}
	public Memprotect getByCustID(String cust_id){
		return  (Memprotect) this.memprotectDao.getByCustID(cust_id);
	}
}

