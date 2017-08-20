/*
 * Package:com.rbt.servie.impl
 * FileName: StoreintroService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IStoreintroDao;
import com.rbt.model.Storeintro;
import com.rbt.service.IStoreintroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 门店服务介绍Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Wed Sep 23 13:59:28 CST 2015
 */
@Service
public class StoreintroService extends GenericService<Storeintro,String> implements IStoreintroService {
	
	IStoreintroDao storeintroDao;

	@Autowired
	public StoreintroService(IStoreintroDao storeintroDao) {
		super(storeintroDao);
		this.storeintroDao = storeintroDao;
	}
	/**
	 * @Method Description :查询前台条数
	 */
	public int getWebCount(Map map) {
		return this.storeintroDao.getWebCount(map);
	}

	/**
	 * @Method Description :查询前台列表
	 */
	public List getWebList(Map map) {
		return this.storeintroDao.getWebList(map);
	}
}

