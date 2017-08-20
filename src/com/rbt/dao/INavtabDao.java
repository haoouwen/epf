/*
 
 * Package:com.rbt.dao
 * FileName: INavtabDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Navtab;

/**
 * @function 功能 导航标签信息dao层业务接口
 * @author  创建人ZMS
 * @date  创建日期 Wed Aug 12 20:56:05 CST 2015
 */

public interface INavtabDao extends IGenericDao<Navtab,String>{
	
	public Navtab getByTaxNumber(String tab_number);
	
}

