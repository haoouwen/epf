/*
 * Package:com.rbt.dao
 * FileName: IStoreintroDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Storeintro;

/**
 * @function 功能 门店服务介绍dao层业务接口
 * @author  创建人HXK
 * @date  创建日期 Wed Sep 23 13:59:28 CST 2015
 */

public interface IStoreintroDao extends IGenericDao<Storeintro,String>{
	
	public int getWebCount(Map map);
	public List getWebList(Map map);
	
}

