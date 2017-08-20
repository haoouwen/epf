/*
  
 
 * Package:com.rbt.dao
 * FileName: IAdvinfoDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Advinfo;

/**
 * @function 功能 广告信息dao层业务接口
 * @author  创建人 QJY
 * @date  创建日期 Jul 7, 2014 4:57:11 PM
 */

public interface IAdvinfoDao extends IGenericDao<Advinfo,String>{
	
	/**
	 * 方法描述：按照map中的条件找出信息
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getAdvinfoIntr(Map map);

	/**
	 * 方法描述：各模块的名称关键字广告的名称
	 * @param pk
	 * @return java.util.Map
	 */
	public List getKeywordAdList(Map map);
	public Advinfo getImg(String id);
}
