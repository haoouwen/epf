/*
  
 
 * Package:com.rbt.dao
 * FileName: IAutofckDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Autofck;

/**
 * @function 功能 在线编辑器dao层业务接口
 * @author  创建人LHY
 * @date  创建日期 Mon Jan 28 12:54:12 CST 2014
 */

public interface IAutofckDao extends IGenericDao<Autofck,String>{
	//更新fck
	public void updaterandom(Map map);
	//获取对象
	public Autofck getrandom(Map map);
}

