/*
 
 * Package:com.rbt.dao.impl
 * FileName: RedsumerDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IRedsumerDao;
import com.rbt.model.Redsumer;

/**
 * @function 功能  红包消费码dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Wed Aug 12 17:05:07 CST 2015
 */
@Repository
public class RedsumerDao extends GenericDao<Redsumer,String> implements IRedsumerDao {
	
	public RedsumerDao() {
		super(Redsumer.class);
	}
	
}

