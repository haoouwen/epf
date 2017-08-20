/*
  
 
 * Package:com.rbt.dao
 * FileName: IConsultationDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Consultation;

/**
 * @function 功能 记录商品咨询信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Thu Feb 28 16:47:46 CST 2014
 */

public interface IConsultationDao extends IGenericDao<Consultation,String>{
	@SuppressWarnings("unchecked")
	public int getWebCount(Map map);
	/**
	 * 方法描述：按照map中的条件找出商品咨询信息
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getWebList(Map map);
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过goods_name 分组
	 */
	public List getListByGroup(Map map);
	/**
	 * @author:HXM
	 * @date:May 30, 201410:54:39 AM
	 * @param:
	 * @Description:查询获得分组总条数
	 */
	public int getGroupCount(Map map);
}

