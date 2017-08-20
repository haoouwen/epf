/*
  
 
 * Package:com.rbt.servie
 * FileName: IRolerightService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Roleright;

/**
 * @function 功能 角色权限业务层接口
 * @author 创建人 QJY
 * @date 创建日期 Jun 28, 2014
 */
public interface IRolerightService extends IGenericService<Roleright, String> {

	/**
	 * 方法描述：按照map中的条件找出角色权限的信息
	 * 
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getRolerightMenuList(Map map);

	/**
	 * @MethodDescribe 方法描述 根据url返回提示字符串和操作权限名
	 * @author 创建人 LJQ
	 * @date 创建日期 Jul 5, 2014 3:15:22 PM
	 */
	public List getRolerightLogsList(Map map);
	
	/**
	 * @author:HXM
	 * @date:Jun 4, 20143:05:43 PM
	 * @param:syscode标记权限操作类型
	 * @param:权限操作字符串
	 * @Description:获得权限操作集合，用于添加 角色 时用于分配用户能添加那些指定的操作权限
	 */
	public List getOperRightList(String syscode,String oper_right);

}
