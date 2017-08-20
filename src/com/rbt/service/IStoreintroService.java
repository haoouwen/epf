/*
 * Package:com.rbt.servie
 * FileName: IStoreintroService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Storeintro;

/**
 * @function 功能 门店服务介绍Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Wed Sep 23 13:59:28 CST 2015
 */

public interface IStoreintroService extends IGenericService<Storeintro,String>{
	public int getWebCount(Map map);
	public List getWebList(Map map);
}

