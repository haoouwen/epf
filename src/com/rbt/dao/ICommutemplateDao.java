/*
  
 
 * Package:com.rbt.dao
 * FileName: ICommutemplateDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Commutemplate;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 记录会员发送模板信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Sat Dec 29 13:20:17 CST 2014
 */

public interface ICommutemplateDao extends IGenericDao<Commutemplate,String>{
	Commutemplate getByTempcode(String temp_code);
}

