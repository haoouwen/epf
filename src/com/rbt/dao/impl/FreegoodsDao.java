/*
 * Package:com.rbt.dao.impl
 * FileName: FreegoodsDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IFreegoodsDao;
import com.rbt.model.Freegoods;

/**
 * @function 功能  赠品dao层业务接口实现类
 * @author 创建人 ZMS
 * @date 创建日期 Tue Sep 29 17:12:09 CST 2015
 */
@Repository
public class FreegoodsDao extends GenericDao<Freegoods,String> implements IFreegoodsDao {
	
	public FreegoodsDao() {
		super(Freegoods.class);
	}
	
}

