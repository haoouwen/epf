/*
  
 
 * Package:com.rbt.dao
 * FileName: IMalltemplateDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Malltemplate;

/**
 * @function 功能 商城模板信息dao层业务接口
 * @author  创建人LSQ
 * @date  创建日期 Fri Dec 28 10:31:27 CST 2014
 */

public interface IMalltemplateDao extends IGenericDao<Malltemplate,String>{
	public void updateisenable();
}

