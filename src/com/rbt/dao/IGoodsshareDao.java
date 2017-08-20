/*
 * Package:com.rbt.dao
 * FileName: IGoodsshareDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodsshare;

/**
 * @function 功能 晒单dao层业务接口
 * @author  创建人QJY
 * @date  创建日期 Wed Oct 29 14:36:43 CST 2014
 */

public interface IGoodsshareDao extends IGenericDao<Goodsshare,String>{
	public List getWebList(Map map);
	public int getWebCount(Map map);
}

