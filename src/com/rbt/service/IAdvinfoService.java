/*
  
 
 * Package:com.rbt.servie
 * FileName: IAdvinfoService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Advinfo;

/**
 * @function 功能 广告位Service层业务接口实现类
 * @author  创建人 QJY
 * @date  创建日期 Jul 7, 2014 5:35:06 PM
 */

public interface IAdvinfoService extends IGenericService<Advinfo,String>{
	
	
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
	/**
	 * 方法描述：各模块的名称关键字广告的名称
	 * @param pk
	 * @return java.util.Map
	 */
	public Advinfo getImg(String id);
	
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 9:35:24 AM
	 * @Method Description：List处理
	 */
	public List replaceList(List advinfoList);
	
	/**
	 * @author：XBY
	 * @date：Feb 13, 2014 2:16:59 PM
	 * @Method Description：获取信息
	 */
	public Advinfo getAdvinfo(String pos_id,Advinfo	advinfo);
}
