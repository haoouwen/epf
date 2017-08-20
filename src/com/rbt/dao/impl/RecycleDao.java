/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: RecycleDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IRecycleDao;
import com.rbt.model.Recycle;

/**
 * @function 功能  记录回收站信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Mon Mar 04 15:55:48 CST 2014
 */
@Repository
public class RecycleDao extends GenericDao<Recycle,String> implements IRecycleDao {
	
	public RecycleDao() {
		super(Recycle.class);
	}
	
}

