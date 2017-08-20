/*
 
 * Package:com.rbt.dao.impl
 * FileName: ComsumerDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IComsumerDao;
import com.rbt.model.Comsumer;

/**
 * @function 功能  优惠券消费码dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Wed Aug 12 14:55:34 CST 2015
 */
@Repository
public class ComsumerDao extends GenericDao<Comsumer,String> implements IComsumerDao {
	
	public ComsumerDao() {
		super(Comsumer.class);
	}
	
}

