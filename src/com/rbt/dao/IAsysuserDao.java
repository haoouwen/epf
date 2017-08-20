/*
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IAsysuserDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Asysuser;

/**
 * @function 功能 代理商dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Wed Aug 05 14:52:52 CST 2015
 */

public interface IAsysuserDao extends IGenericDao<Asysuser,String>{
	/**
	 * @MethodDescribe 方法描述    更新最后运营商最后一次登录的ID
	 * @author  创建人  LJQ
	 * @date  创建日期  Sep 21, 2011 10:33:01 AM
	 */	
	public void updatelaststate(Asysuser asysuser);
	
	/**
	 * 前台
	 * @param map
	 * @return
	 */
	public int getWebCount(Map map);
	/**
	 * 前台
	 * @param map
	 * @return
	 */
	public List getWebList(Map map);
	
}

