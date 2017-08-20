/*
  
 
 * Package:com.rbt.dao
 * FileName: IBuyeraddrDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Buyeraddr;

/**
 * @function 功能 收货地址管理dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Thu Dec 27 16:33:56 CST 2014
 */

public interface IBuyeraddrDao extends IGenericDao<Buyeraddr,String>{
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过role_id 分组
	 */
	public List getListByGroup(Map map);
	/**
	 * @author:HXM
	 * @date:May 30, 201410:54:39 AM
	 * @param:
	 * @Description:查询获得分组条数
	 */
	public int getGroupCount(Map map);
}

