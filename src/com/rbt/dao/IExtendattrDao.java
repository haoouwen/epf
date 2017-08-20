/*
  
 
 * Package:com.rbt.dao
 * FileName: IExtend_attrDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Extendattr;

/**
 * @function 功能 记录商品扩展属性信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Tue Jan 15 10:51:04 CST 2014
 */

public interface IExtendattrDao extends IGenericDao<Extendattr,String>{
	
	public List getExAttrList(Map map);
}

