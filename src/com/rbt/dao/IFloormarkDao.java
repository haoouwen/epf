/*
 
 * Package:com.rbt.dao
 * FileName: IFloormarkDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Floormark;

/**
 * @function 功能 楼层标签dao层业务接口
 * @author  创建人HXK
 * @date  创建日期 Sat Aug 08 16:35:30 CST 2015
 */

public interface IFloormarkDao extends IGenericDao<Floormark,String>{
	
	
	 public void updateCheckbox(Map map);
	 public void updateInfo(Map map);
	
}

