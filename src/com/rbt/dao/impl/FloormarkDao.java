/*
 
 * Package:com.rbt.dao.impl
 * FileName: FloormarkDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IFloormarkDao;
import com.rbt.model.Floormark;

/**
 * @function 功能  楼层标签dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Sat Aug 08 16:35:30 CST 2015
 */
@Repository
public class FloormarkDao extends GenericDao<Floormark,String> implements IFloormarkDao {
	
	public FloormarkDao() {
		super(Floormark.class);
	}
	 public void updateCheckbox(Map map){
		 this.getSqlMapClientTemplate().update(getModelName()+".updateCheckbox", map);
	 }
	 
	 public void updateInfo(Map map){
		 this.getSqlMapClientTemplate().update(getModelName()+".updateInfo",map);
	 }
}

