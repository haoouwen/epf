/*
  
 
 * Package:com.rbt.servie
 * FileName: IAdvposService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Advpos;

/**
 * @function 功能 广告位Service层业务接口
 * @author 创建人 QJY
 * @date 创建日期 Jul 7, 2014 5:34:30 PM
 */
public interface IAdvposService extends IGenericService<Advpos,String>{

	/**
	 * 方法描述：批量修改广告位排序
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateAdvposBatch(List list);
	
	/**
	 * 方法描述：按照map中的条件找出广告位名称的信息
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getPosNameList(Map map);
}
