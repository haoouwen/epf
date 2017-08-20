/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SelfparavalueService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISelfparavalueDao;
import com.rbt.model.Selfparavalue;
import com.rbt.service.ISelfparavalueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录自定义参数值信息Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Sat Feb 16 14:34:05 CST 2014
 */
@Service
public class SelfparavalueService extends GenericService<Selfparavalue,String> implements ISelfparavalueService {
	
	ISelfparavalueDao selfparavalueDao;

	@Autowired
	public SelfparavalueService(ISelfparavalueDao selfparavalueDao) {
		super(selfparavalueDao);
		this.selfparavalueDao = selfparavalueDao;
	}
	
}

