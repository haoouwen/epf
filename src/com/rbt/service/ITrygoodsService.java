/*
  
 
 * Package:com.rbt.servie
 * FileName: ITrygoodsService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rbt.model.Trygoods;

/**
 * @function 功能 试用商品Service层业务接口实现类
 * @author  创建人 CYC
 * @date  创建日期 Tue Jun 17 13:55:37 CST 2014
 */

public interface ITrygoodsService extends IGenericService<Trygoods,String>{

public String getAddrDiv(HttpServletRequest request,String cust_id,String user_id) throws Exception;
	
public String addOrder(Map map)throws Exception; 

}

