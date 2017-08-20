/*
  
 
 * Package:com.rbt.service
 * FileName: ISysuserService.java 
 */

package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Sysmenu;

/**
 * @function 功能  系统菜单业务层接口
 * @author  创建人 HXK
 * @date  创建日期  Jun 25, 2014
 */
public interface ISysmenuService extends IGenericService<Sysmenu,String>{
	
	/**
	 * 方法描述：菜单是否可用批量修改
	 * @param interrule
	 */
	public void updateEnable(final List list);
	
	public void updateEnabled(final List list);
	
	//递归更改菜单状态时，查询menu_id的拼串
	public List getEnableList(Map map);
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 3:01:50 PM
	 * @Method Description :递归更新
	 */
	public void updateValid(String id, String enabled);
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 3:16:48 PM
	 * @Method Description :递归调用删除数据
	 */
	public void recuDelete(String mid);
	/**
	 * @author : HZX
	 * @param menu_string 
	 * @date : Feb 10, 2014 3:23:26 PM
	 * @Method Description :匹配菜单串，若不存在删除节点
	 */
	public List removeMenuList(List menuList, String menu_string);
}
