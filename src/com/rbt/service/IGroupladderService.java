/*
  
 
 * Package:com.rbt.servie
 * FileName: IGroupladderService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Groupladder;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 团购阶梯价格Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Mon Apr 15 17:26:40 CST 2014
 */

public interface IGroupladderService extends IGenericService<Groupladder,String>{
	public Groupladder getByGroupID(String id);
	public void deleteGroupID(String id);
}

