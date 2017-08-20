/*
 * Package:com.rbt.dao.impl
 * FileName: ExcouponsDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IExcouponsDao;
import com.rbt.model.Excoupons;

/**
 * @function 功能  优惠券兑换表dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Fri Oct 09 13:10:41 CST 2015
 */
@Repository
public class ExcouponsDao extends GenericDao<Excoupons,String> implements IExcouponsDao {
	
	public ExcouponsDao() {
		super(Excoupons.class);
	}
	
}

