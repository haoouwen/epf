/*
  
 
 * Package:com.rbt.dao
 * FileName: ICartgoodsDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Cartgoods;

/**
 * @function 功能 购物车dao层业务接口
 * @author  创建人WXP
 * @date  创建日期 Mon May 13 14:10:06 CST 2014
 */

public interface ICartgoodsDao extends IGenericDao<Cartgoods,String>{
	//修改购物车
	public void updateCustId(Cartgoods cart);
}

