/*
 * Package:com.rbt.dao
 * FileName: INavigationDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Navigation;

/**
 * @function 功能 导航列表信息dao层业务接口
 * @author  创建人ZMS
 * @date  创建日期 Thu Aug 13 11:37:53 CST 2015
 */

public interface INavigationDao extends IGenericDao<Navigation,String>{
	@SuppressWarnings("unchecked")
	public int getWebCount(Map map);
	/**
	 * 方法描述：按照map中的条件找出商品信息
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getWebList(Map map);
}

