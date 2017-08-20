/*
  
 
 * Package:com.rbt.dao
 * FileName: IGroupladderDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Groupladder;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 团购阶梯价格dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Mon Apr 15 17:26:40 CST 2014
 */

public interface IGroupladderDao extends IGenericDao<Groupladder,String>{
	Groupladder getByGroupID(String group_id);
	public void deleteGroupID(String id);
}

