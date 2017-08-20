/*
 
 * Package:com.rbt.dao.impl
 * FileName: OnlinepaytradeDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IOnlinepaytradeDao;
import com.rbt.model.Onlinepaytrade;

/**
 * @function 功能  记录网银支付流水信息dao层业务接口实现类
 * @author 创建人 WXP
 * @date 创建日期 Fri Dec 07 14:55:14 CST 2014
 */
@Repository
public class OnlinepaytradeDao extends GenericDao<Onlinepaytrade,String> implements IOnlinepaytradeDao {
	
	public OnlinepaytradeDao() {
		super(Onlinepaytrade.class);
	}
	
}

