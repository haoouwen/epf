/*
  
 
 * Package:com.rbt.dao
 * FileName: ICategoryattrDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Categoryattr;

/**
 * @function 功能 产品属性列表dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Tue Jul 19 08:48:08 CST 2014
 */

public interface ICategoryattrDao extends IGenericDao<Categoryattr,String>{
	
	public List getCatAttrList(Map map);
	
	public void deleteAttr_id(String id);
	
}

