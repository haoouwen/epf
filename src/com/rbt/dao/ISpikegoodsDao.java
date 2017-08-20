/*
  
 
 * Package:com.rbt.dao
 * FileName: ISpikegoodsDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Spikegoods;

/**
 * @function 功能 秒杀商品dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Fri Mar 29 15:32:29 CST 2014
 */

public interface ISpikegoodsDao extends IGenericDao<Spikegoods,String>{
	
	@SuppressWarnings("unchecked")
	public int getWebCount(Map map);
	
	@SuppressWarnings("unchecked")
	public List getWebList(Map map);
}

