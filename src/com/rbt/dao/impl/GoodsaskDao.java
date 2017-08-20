/*
 
 * Package:com.rbt.dao.impl
 * FileName: GoodsaskDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IGoodsaskDao;
import com.rbt.model.Goodsask;

/**
 * @function 功能  记录商品咨询信息dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Fri Mar 23 11:24:44 CST 2014
 */
@Repository
public class GoodsaskDao extends GenericDao<Goodsask,String> implements IGoodsaskDao {
	
	public GoodsaskDao() {
		super(Goodsask.class);
	}
	
}

