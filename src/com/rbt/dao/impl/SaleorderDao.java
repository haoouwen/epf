/*
 
 * Package:com.rbt.dao.impl
 * FileName: SaleorderDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISaleorderDao;
import com.rbt.model.Saleorder;

/**
 * @function 功能  订单促销dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Wed Aug 12 11:01:23 CST 2015
 */
@Repository
public class SaleorderDao extends GenericDao<Saleorder,String> implements ISaleorderDao {
	
	public SaleorderDao() {
		super(Saleorder.class);
	}
	
}

