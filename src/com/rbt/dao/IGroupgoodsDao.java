/*
  
 
 * Package:com.rbt.dao
 * FileName: IGroupgoodsDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Groupgoods;

/**
 * @function 功能 团购商品信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Thu Mar 28 14:55:26 CST 2014
 */

public interface IGroupgoodsDao extends IGenericDao<Groupgoods,String>{
	/**
	 * @author:HXM
	 * @date:May 9, 20142:33:22 PM
	 * @param:
	 * @Description:修改资金冻结状态
	 */
	public void updateApply(Map map);
}

