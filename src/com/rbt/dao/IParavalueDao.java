/*
  
 
 * Package:com.rbt.dao
 * FileName: IParavalueDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memberuser;
import com.rbt.model.Paravalue;

/**
 * @function 功能 参数值信息dao层业务接口
 * @author  创建人LSQ
 * @date  创建日期 Wed Jan 16 16:29:11 CST 2014
 */

public interface IParavalueDao extends IGenericDao<Paravalue,String>{

	public List getParaValList(Map map);
	
}

