/*
 * Package:com.rbt.servie.impl
 * FileName: RechargeablecardService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IRechargeablecardDao;
import com.rbt.model.Rechargeablecard;
import com.rbt.service.IRechargeablecardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 充值卡Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Sun Oct 11 11:07:29 CST 2015
 */
@Service
public class RechargeablecardService extends GenericService<Rechargeablecard,String> implements IRechargeablecardService {
	
	IRechargeablecardDao rechargeablecardDao;

	@Autowired
	public RechargeablecardService(IRechargeablecardDao rechargeablecardDao) {
		super(rechargeablecardDao);
		this.rechargeablecardDao = rechargeablecardDao;
	}
	
}

