/*
 * Package:com.rbt.dao
 * FileName: IKjtrecallDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Kjtrecall;

/**
 * @function 功能 跨境通回调表dao层业务接口
 * @author  创建人CYC
 * @date  创建日期 Fri Sep 18 16:21:49 CST 2015
 */

public interface IKjtrecallDao extends IGenericDao<Kjtrecall,String>{
	public void updatekjtpur(Map map);
	/**
	 * @Method Description :通过订单ID查找海关回传对象值
	 * @author: 胡惜坤
	 * @date : Feb 24, 2016 11:33:23 AM
	 * @param 
	 * @return return_type
	 */
	public Kjtrecall getByOrderId(String order_id);
}

