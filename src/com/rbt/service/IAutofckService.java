/*
  
 
 * Package:com.rbt.servie
 * FileName: IAutofckService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Autofck;

/**
 * @function 功能 在线编辑器Service层业务接口实现类
 * @author  创建人 LHY
 * @date  创建日期 Mon Jan 28 12:54:12 CST 2014
 */

public interface IAutofckService extends IGenericService<Autofck,String>{
	public void updaterandom(Map map);
	//获取对象
	public Autofck getrandom(Map map);
}

