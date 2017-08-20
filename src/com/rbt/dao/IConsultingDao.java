/*
  
 
 * Package:com.rbt.dao
 * FileName: IConsultingDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Consulting;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 记录商品咨询l回复信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Thu Feb 28 16:48:34 CST 2014
 */

public interface IConsultingDao extends IGenericDao<Consulting,String>{
	Consulting getByTradeId(String trade_id);
}

