/*
  
 
 * Package:com.rbt.dao
 * FileName: IAttrvalueDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Attrvalue;

/**
 * @function 功能 记录分类属性的值dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Tue Aug 21 15:25:18 CST 2014
 */

public interface IAttrvalueDao extends IGenericDao<Attrvalue,String>{
	public void deleteByattrid(String id);
}

