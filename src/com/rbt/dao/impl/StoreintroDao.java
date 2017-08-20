/*
 * Package:com.rbt.dao.impl
 * FileName: StoreintroDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IStoreintroDao;
import com.rbt.model.Storeintro;

/**
 * @function 功能  门店服务介绍dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Wed Sep 23 13:59:28 CST 2015
 */
@Repository
public class StoreintroDao extends GenericDao<Storeintro,String> implements IStoreintroDao {
	
	public StoreintroDao() {
		super(Storeintro.class);
	}
	public int getWebCount(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("storeintro.getWebCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	public List getWebList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("storeintro.getWebList",map);
	}
}

