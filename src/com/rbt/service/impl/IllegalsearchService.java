/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: IllegalsearchService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IIllegalsearchDao;
import com.rbt.model.Illegalsearch;
import com.rbt.service.IIllegalsearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 前台搜索拦截信息Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Fri Apr 03 12:03:00 CST 2015
 */
@Service
public class IllegalsearchService extends GenericService<Illegalsearch,String> implements IIllegalsearchService {
	
	IIllegalsearchDao illegalsearchDao;

	@Autowired
	public IllegalsearchService(IIllegalsearchDao illegalsearchDao) {
		super(illegalsearchDao);
		this.illegalsearchDao = illegalsearchDao;
	}
	
}

