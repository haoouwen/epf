/*
  
 
 * Package:com.rbt.dao
 * FileName: ICancelorderDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Cancelorder;

/**
 * @function 功能 取消订单理由dao层业务接口
 * @author  创建人XBY
 * @date  创建日期 Sat Jan 10 13:47:37 CST 2015
 */

public interface ICancelorderDao extends IGenericDao<Cancelorder,String>{
	public Cancelorder getByOrderId(String order_id);
}

