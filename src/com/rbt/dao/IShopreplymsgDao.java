/*
  
 
 * Package:com.rbt.dao
 * FileName: IShopreplymsgDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Shopreplymsg;

/**
 * @function 功能 店铺留言表dao层业务接口
 * @author  创建人LHY
 * @date  创建日期 Thu Feb 28 15:36:59 CST 2014
 */

public interface IShopreplymsgDao extends IGenericDao<Shopreplymsg,String>{
	public Shopreplymsg getByMsgId(String id);
	
}

