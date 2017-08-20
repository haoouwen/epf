/*
  
 
 * Package:com.rbt.dao
 * FileName: IAutoupgoodsDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Autoupgoods;

/**
 * @function 功能 记录商品上下架管理信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Fri Feb 01 10:46:02 CST 2014
 */

public interface IAutoupgoodsDao extends IGenericDao<Autoupgoods,String>{
	public void deleteByGoodsId(String id);
}

