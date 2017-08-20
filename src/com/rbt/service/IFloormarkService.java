/*
 
 * Package:com.rbt.servie
 * FileName: IFloormarkService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Floormark;

/**
 * @function 功能 楼层标签Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Sat Aug 08 16:35:30 CST 2015
 */

public interface IFloormarkService extends IGenericService<Floormark,String>{
	 public void updateCheckbox(Map map);
	 public void updateInfo(Map map);
	
}

