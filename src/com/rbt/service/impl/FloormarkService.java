/*
 
 * Package:com.rbt.servie.impl
 * FileName: FloormarkService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IFloormarkDao;
import com.rbt.model.Floormark;
import com.rbt.service.IFloormarkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 楼层标签Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Sat Aug 08 16:35:30 CST 2015
 */
@Service
public class FloormarkService extends GenericService<Floormark,String> implements IFloormarkService {
	
	IFloormarkDao floormarkDao;

	@Autowired
	public FloormarkService(IFloormarkDao floormarkDao) {
		super(floormarkDao);
		this.floormarkDao = floormarkDao;
	}
	 public void updateCheckbox(Map map){
		 this.floormarkDao.updateCheckbox(map);
		 
	 }
	 
	 public void updateInfo(Map map){
		 this.floormarkDao.updateInfo(map);
	 }
	
}

