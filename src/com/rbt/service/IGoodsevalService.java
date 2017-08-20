/*
  
 
 * Package:com.rbt.servie
 * FileName: IGoodsevalService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodseval;

/**
 * @function 功能 记录商品评价表信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Tue Jan 08 11:06:50 CST 2014
 */

public interface IGoodsevalService extends IGenericService<Goodseval,String>{
	@SuppressWarnings("unchecked")
	public int getWebCount(Map map);
	
	@SuppressWarnings("unchecked")
   	public List getWebList(Map map); 
	/**
	 * @Method Description :买家评价更新
	 * @author : HZX
	 * @date : Apr 2, 2014 10:19:46 AM
	 */
	@SuppressWarnings("unchecked")
	public void updatebuy(Goodseval goodseval);
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过goods_name 分组
	 */
	public List getListByGroup(Map map);
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:58:48 AM
	 * @param:
	 * @Description:获得分组以后的总条数
	 */
	public int getGroupCount(Map map);
}

