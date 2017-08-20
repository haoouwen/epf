/*
 * Package:com.rbt.servie.impl
 * FileName: StoreservceService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IStoreservceDao;
import com.rbt.model.Storeservce;
import com.rbt.service.IStoreservceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 门店服务Service层业务接口实现
 * @author 创建人 ZMS
 * @date 创建日期 Sat Aug 29 16:01:36 CST 2015
 */
@Service
public class StoreservceService extends GenericService<Storeservce,String> implements IStoreservceService {
	
	IStoreservceDao storeservceDao;

	@Autowired
	public StoreservceService(IStoreservceDao storeservceDao) {
		super(storeservceDao);
		this.storeservceDao = storeservceDao;
	}
	
}

