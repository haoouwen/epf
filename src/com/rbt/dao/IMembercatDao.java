/*
  
 
 * Package:com.rbt.dao
 * FileName: IMembercatDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Membercat;

/**
 * @function 功能 记录会员自定义分类信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Fri Jan 11 09:28:06 CST 2014
 */

public interface IMembercatDao extends IGenericDao<Membercat,String>{
	/**
	 * 获取级联数据
	 * @param map
	 * @return
	 */
	public List getDeleteList(Map map);

	//获取全部的membercat
	public List getAll();
	
}

