/*
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: CouponDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ICouponDao;
import com.rbt.model.Coupon;

/**
 * @function 功能  优惠券dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Fri Aug 07 14:37:49 CST 2015
 */
@Repository
public class CouponDao extends GenericDao<Coupon,String> implements ICouponDao {
	
	public CouponDao() {
		super(Coupon.class);
	}
	
}

