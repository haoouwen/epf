/*
  
 
 * Package:com.rbt.dao
 * FileName: IPagetipDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Pagetip;

/**
 * @function 功能 记录页面显示管理信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Tue Jan 29 13:10:46 CST 2014
 */

public interface IPagetipDao extends IGenericDao<Pagetip,String>{
	/**
	 * 方法描述：找出全部数据
	 * 
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getAll();
}

