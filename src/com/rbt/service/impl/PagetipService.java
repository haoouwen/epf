/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: PagetipService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IPagetipDao;
import com.rbt.model.Pagetip;
import com.rbt.service.IPagetipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录页面显示管理信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Tue Jan 29 13:10:46 CST 2014
 */
@Service
public class PagetipService extends GenericService<Pagetip,String> implements IPagetipService {
	
	IPagetipDao pagetipDao;

	@Autowired
	public PagetipService(IPagetipDao pagetipDao) {
		super(pagetipDao);
		this.pagetipDao = pagetipDao;
	}
	
	public List getAll() {
		return this.pagetipDao.getAll();
	}
}

