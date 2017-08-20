/*
  
 
 * Package:com.rbt.servie
 * FileName: IMemroleService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memrole;

/**
 * @function 功能 商城后台角色信息Service层业务接口实现类
 * @author  创建人 LSQ
 * @date  创建日期 Thu Jan 24 16:33:37 CST 2014
 */

public interface IMemroleService extends IGenericService<Memrole,String>{
	/**
	 * @author : HZX
	 * @param ssession_cust_id 
	 * @date : Feb 12, 2014 2:01:41 PM
	 * @Method Description : 找出角色对应的子账号数量
	 */
	List getmemroleList(List memroleList, String ssession_cust_id);
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 2:11:38 PM
	 * @Method Description :公共获取列表的菜单、操作权限
	 */
	List queryCommon(String[] menu_right_x, String syscode_b2c_value,
			 String level, String enabled);
	
}

