/*
 * Package:com.rbt.dao.impl
 * FileName: RechargeablecardDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IRechargeablecardDao;
import com.rbt.model.Rechargeablecard;

/**
 * @function 功能  充值卡dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Sun Oct 11 11:07:29 CST 2015
 */
@Repository
public class RechargeablecardDao extends GenericDao<Rechargeablecard,String> implements IRechargeablecardDao {
	
	public RechargeablecardDao() {
		super(Rechargeablecard.class);
	}
	
}

