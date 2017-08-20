/*
  
 
 * Package:com.rbt.dao
 * FileName: ISysmenuDao.java 
 */

package com.rbt.dao;

import java.util.List;
import java.util.Map;
import com.rbt.model.Sysmenu;

/**
 * @function 功能 系统菜单dao层接口
 * @author  创建人 HXK
 * @date  创建日期  Jun 25, 2014
 */
public interface ISysmenuDao extends IGenericDao<Sysmenu,String>{
	
	/**
	 * 方法描述：菜单是否可用批量修改
	 * @param interrule
	 */
	public void updateEnable(final List list);
	public void updateEnabled(final List list);
	//递归更改菜单状态时，查询menu_id的拼串
	public List getEnableList(Map map);
	
}
