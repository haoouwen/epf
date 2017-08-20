/*
  
 
 * Package:com.rbt.servie
 * FileName: IAreasetService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Areaset;

/**
 * @function 功能 记录区域设置信息Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Wed Mar 28 13:22:27 CST 2014
 */

public interface IAreasetService extends IGenericService<Areaset,String>{
	public void deleteByShopid(String id);
	
	/**
	 * 方法描述：构造LIST用于信息的回选
	 * @return
	 * @throws Exception
	 */
	public void area_attr_list(String area_ids,List area_attr_list);
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 9:47:27 AM
	 * @Method Description：List判断处理
	 */
	public List replaceList(List areasetList);
}

