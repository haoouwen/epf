/*
 
 * Package:com.rbt.dao.impl
 * FileName: SalegoodsDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISalegoodsDao;
import com.rbt.model.Salegoods;

/**
 * @function 功能  商品促销信息dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Tue Aug 11 09:31:09 CST 2015
 */
@Repository
public class SalegoodsDao extends GenericDao<Salegoods,String> implements ISalegoodsDao {
	
	public SalegoodsDao() {
		super(Salegoods.class);
	}
	
}

