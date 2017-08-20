/*
  
 
 * Package:com.rbt.servie
 * FileName: IConsultingService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Consulting;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 记录商品咨询l回复信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Thu Feb 28 16:48:34 CST 2014
 */

public interface IConsultingService extends IGenericService<Consulting,String>{
	public Consulting getByTradeId(String id);
}

