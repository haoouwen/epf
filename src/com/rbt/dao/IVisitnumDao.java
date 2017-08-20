/*
  
 
 * Package:com.rbt.dao
 * FileName: IVisitnumDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Visitnum;

/** 
 * @function 功能 记录日访问数dao层业务接口
 * @author  创建人LHY
 * @date  创建日期 Thu Oct 11 09:56:36 CST 2014
 */

public interface IVisitnumDao extends IGenericDao<Visitnum,String>{
	/**
	 * 根据日期获取访问量
	 */
	public int getSum(Map<String,String>map);
	
}

