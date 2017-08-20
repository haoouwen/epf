/*
 * Package:com.rbt.dao.impl
 * FileName: StoreservceDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IStoreservceDao;
import com.rbt.model.Storeservce;

/**
 * @function 功能  门店服务dao层业务接口实现类
 * @author 创建人 ZMS
 * @date 创建日期 Sat Aug 29 16:01:36 CST 2015
 */
@Repository
public class StoreservceDao extends GenericDao<Storeservce,String> implements IStoreservceDao {
	
	public StoreservceDao() {
		super(Storeservce.class);
	}
	
}

