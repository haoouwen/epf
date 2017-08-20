/*
 
 * Package:com.rbt.servie
 * FileName: INavigationService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Navigation;

/**
 * @function 功能 导航列表信息Service层业务接口实现类
 * @author  创建人 ZMS
 * @date  创建日期 Thu Aug 13 11:37:53 CST 2015
 */

public interface INavigationService extends IGenericService<Navigation,String>{
	
	/**
	 * @function 批量插入导航商品数据
	 * @param goods_str
	 * @param tab_id
	 */
	public int insertNavGoods(String goods_str,String tab_id,String tab_number);
	/**
	 * @Method Description :查询前台条数
	 */
	public int getWebCount(Map map);
	
	/**
	 * @Method Description :查询前台列表
	 */
	public List getWebList(Map map);
	
}

