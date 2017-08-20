/*
  
 
 * Package:com.rbt.dao
 * FileName: ISendboxDao.java 
 */
package com.rbt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.model.Sendbox;

/**
 * @function 功能 记录发件箱信息dao层业务接口
 * @author  创建人LSQ
 * @date  创建日期 Wed Jan 30 15:36:28 CST 2014
 */

public interface ISendboxDao extends IGenericDao<Sendbox,String>{
	
	
	public void deletelogic(HashMap map);
	
	public List getDelSend();
	
}

