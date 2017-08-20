/*
  
 
 * Package:com.rbt.dao
 * FileName: IMsgcheckDao.java 
 */
package com.rbt.dao;
import java.util.Map;

import com.rbt.model.Msgcheck;

/**
 * @function 功能 记录验证码dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Wed Oct 10 16:38:35 CST 2014
 */

public interface IMsgcheckDao extends IGenericDao<Msgcheck,String>{

	public void deleteMsgcheck(Map msgCkeckMap);
	public void deleteById(String id);
	/**
	 * @Method Description :将验证码 修改已经验证
	 * @author: 胡惜坤
	 * @date : Mar 18, 2016 3:37:18 PM
	 * @param 
	 * @return void
	 */
	public void updateUse(Map map);
}

