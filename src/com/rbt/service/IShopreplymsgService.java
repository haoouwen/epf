/*
  
 
 * Package:com.rbt.servie
 * FileName: IShopreplymsgService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Shopreplymsg;

/**
 * @function 功能 店铺留言表Service层业务接口实现类
 * @author  创建人 LHY
 * @date  创建日期 Thu Feb 28 15:36:59 CST 2014
 */

public interface IShopreplymsgService extends IGenericService<Shopreplymsg,String>{
	public Shopreplymsg getByMsgId(String id);
}

