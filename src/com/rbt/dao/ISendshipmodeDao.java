/*
  
 
 * Package:com.rbt.dao
 * FileName: ISendshipmodeDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Sendmode;
import com.rbt.model.Sendshipmode;

/**
 * @function 功能 快递方式dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Tue May 06 11:17:02 CST 2014
 */

public interface ISendshipmodeDao extends IGenericDao<Sendshipmode,String>{
	public Sendshipmode getByEnname(String en_name);
}

