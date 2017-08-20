/*
 
 * Package:com.rbt.dao.impl
 * FileName: CartgoodsDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ICartgoodsDao;
import com.rbt.model.Cartgoods;

/**
 * @function 功能  购物车dao层业务接口实现类
 * @author 创建人 WXP
 * @date 创建日期 Mon May 13 14:10:06 CST 2014
 */
@Repository
public class CartgoodsDao extends GenericDao<Cartgoods,String> implements ICartgoodsDao {
	
	public CartgoodsDao() {
		super(Cartgoods.class);
	}
	//修改购物车
	public void updateCustId(Cartgoods cart){
		this.getSqlMapClientTemplate().update("cartgoods.updateCustId",cart);
	}
}

