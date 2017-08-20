/*
 * Package:com.rbt.dao.impl
 * FileName: RechargeablecardsDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IRechargeablecardsDao;
import com.rbt.model.Goods;
import com.rbt.model.Rechargeablecards;

/**
 * @function 功能  充值卡dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Sun Oct 11 14:01:50 CST 2015
 */
@Repository
public class RechargeablecardsDao extends GenericDao<Rechargeablecards,String> implements IRechargeablecardsDao {
	
	public RechargeablecardsDao() {
		super(Rechargeablecards.class);
	}
	
	/**
	 * @Method Description :通过key找到充值卡
	 * @author : CYC
	 * @date : Apr 19, 2014 11:01:04 AM
	 */
	public Rechargeablecards getCardkey(String readkey) {
		return  (Rechargeablecards)this.getSqlMapClientTemplate().queryForObject("rechargeablecards.getCardkey", readkey);
	}
	
}

