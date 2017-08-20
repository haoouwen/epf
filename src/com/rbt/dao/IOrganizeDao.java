/*
  
 
 * Package:com.rbt.dao
 * FileName: IOrganizeDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Organize;

/**
 * @function 功能 记录组织部门dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Mon Nov 07 13:37:36 CST 2014
 */

public interface IOrganizeDao extends IGenericDao<Organize,String>{
	/**
	 * 获取所有一级代理的系统用户
	 * @param map
	 * @return
	 */
	public List getSysList(Map map);
	/**
	 * 获取管理sysuser的总数
	 * @param map
	 * @return
	 */
	public int getCounts(Map map);
	/**
	 * @author :LSQ
	 * @date : Feb 17, 2014 3:07:12 PM
	 * @Method Description :获取所有所属部门
	 */
	 public List getAll();
	 
	 public List getDeleteList(Map map);
}

