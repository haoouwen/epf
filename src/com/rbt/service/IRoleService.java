/*
  
 
 * Package:com.rbt.service
 * FileName: IRoleService.java 
 */
package com.rbt.service;

import java.util.List;

import com.rbt.model.Role;

/**
 * @function 功能 添加角色业务层类的接口
 * @author 创建人 LJQ
 * @date 创建日期 Jun 28, 2014 3:25:23 PM
 */
public interface IRoleService extends IGenericService<Role,String>{
	/**
	 * @author : HZX
	 * @date : Feb 11, 2014 10:47:43 AM
	 * @Method Description :找出角色对应的管理员数量
	 */
	List getRoleList(List rolelist);
	
	/**
	 * @author:HXM
	 * @date:Jun 4, 20141:22:19 PM
	 * @param:menu_right管理员操作对应的菜单项
	 * @param:syscode菜单所属的类别
	 * @param:level菜单项属于第几级
	 * @Description:获得会员对应的操作菜单项，用于添加角色时分配菜单的项使用
	 */
	public List queryCommon(String syscode,String level,String menu_right);
	
}
