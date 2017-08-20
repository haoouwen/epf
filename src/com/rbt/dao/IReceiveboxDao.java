/*
  
 
 * Package:com.rbt.dao
 * FileName: IReceiveboxDao.java 
 */
package com.rbt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.model.Receivebox;

/**
 * @function 功能 记录收件箱信息dao层业务接口
 * @author  创建人LSQ
 * @date  创建日期 Wed Jan 30 15:37:25 CST 2014
 */

public interface IReceiveboxDao extends IGenericDao<Receivebox,String>{
	
	public void deletelogic(HashMap map);
	
}

