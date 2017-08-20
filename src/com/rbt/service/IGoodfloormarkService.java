/*
 
 * Package:com.rbt.servie
 * FileName: IGoodfloormarkService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodfloormark;

/**
 * @function 功能 商品楼层信息Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Mon Aug 10 11:00:14 CST 2015
 */

public interface IGoodfloormarkService extends IGenericService<Goodfloormark,String>{
	
	/**
	 * @Method Description :获取楼层标签对于的商品列表
	 * @author: HXK
	 * @date : Aug 15, 2015 10:32:50 AM
	 * @param 
	 * @return return_type
	 */
	public List getGoodsFloorList(Map map);
	
}

