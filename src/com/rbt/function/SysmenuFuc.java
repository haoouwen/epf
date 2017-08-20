/*
  
 
 * Package:com.rbt.function
 * FileName: SysmenuFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rbt.service.ISysmenuService;

/**
 * @function 功能  菜单管理
 * @author  创建人 HXK
 * @date  创建日期  2014-09-28
 */

public class SysmenuFuc extends CreateSpringContext{
	@SuppressWarnings("unchecked")
	
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 4:21:27 PM
	 * @Method Description :根据上一级菜单ID与平台编码找出对应的菜单
	 */
	public static List getMenuListByUpmenuid(String up_menu_id,String syscode){
		Map map = new HashMap();
		map.put("up_menu_id", up_menu_id);
		map.put("enabled","0");
		map.put("syscode", syscode);
		List list = getSysmenuList(map);
		return list;
	}
	
	/**
	 * @author : LJQ
	 * @date : Feb 25, 2014 4:22:05 PM
	 * @Method Description :查询数据库获取菜单列表
	 */
	public static List getSysmenuList(Map map){
		ISysmenuService sysmenuService = (ISysmenuService)getContext().getBean("sysmenuService");
		return sysmenuService.getList(map);
	}
	
	
}
