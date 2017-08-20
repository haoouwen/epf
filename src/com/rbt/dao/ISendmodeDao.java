/*
  
 
 * Package:com.rbt.dao
 * FileName: ISendmodeDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Member;
import com.rbt.model.Sendmode;

/**
 * @function 功能 配送方式表dao层业务接口
 * @author  创建人HXK
 * @date  创建日期 Fri Mar 23 09:55:49 CST 2014
 */

public interface ISendmodeDao extends IGenericDao<Sendmode,String>{
	
	public Sendmode getByEnname(String en_name);
}

