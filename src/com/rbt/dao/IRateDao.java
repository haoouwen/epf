/*
  
 
 * Package:com.rbt.dao
 * FileName: IRateDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Rate;

/**
 * @function 功能 汇率信息dao层业务接口
 * @author  创建人LSQ
 * @date  创建日期 Mon Jan 21 12:46:55 CST 2014
 */

public interface IRateDao extends IGenericDao<Rate,String>{
	public void updateendefault();
	/**
	 * 方法描述：找出全部数据
	 * 
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getAll();
}

