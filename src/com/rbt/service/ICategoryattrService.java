/*
  
 
 * Package:com.rbt.servie
 * FileName: ICategoryattrService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Categoryattr;

/**
 * @function 功能 产品属性列表Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Tue Jul 19 08:48:08 CST 2014
 */

public interface ICategoryattrService extends IGenericService<Categoryattr,String>{
	
	public List getCatAttrList(Map map);
	
	public void deleteAttr_id(String id);
	
	/**
	 * @author：XBY
	 * @date：Feb 11, 2014 3:30:21 PM
	 * @Method Description：添加之前跳转判断
	 */
	public Map getMap(String url_up_id,String level,String up_level_id,String up_level,String modtype_name_id);
}

