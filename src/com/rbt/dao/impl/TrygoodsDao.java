/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: TrygoodsDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ITrygoodsDao;
import com.rbt.model.Trygoods;

/**
 * @function 功能  试用商品dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Tue Jun 17 13:55:37 CST 2014
 */
@Repository
public class TrygoodsDao extends GenericDao<Trygoods,String> implements ITrygoodsDao {
	
	public TrygoodsDao() {
		super(Trygoods.class);
	}
	
}

