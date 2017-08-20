/*
  
 
 * Package:com.rbt.servie
 * FileName: IConsultationService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Consultation;
import com.rbt.model.Member;

/**
 * @function 功能 记录商品咨询信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Thu Feb 28 16:47:46 CST 2014
 */

public interface IConsultationService extends IGenericService<Consultation,String>{
	@SuppressWarnings("unchecked")
	public int getWebCount(Map map);
	
	@SuppressWarnings("unchecked")
	public List getWebList(Map map);
	
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 1:37:18 PM
	 * @Method Description：List替换数据处理
	 */
	public List replaceList(List consultationList,Member member);
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过cust_id分组
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

