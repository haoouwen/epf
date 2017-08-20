/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: RateService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IRateDao;
import com.rbt.model.Rate;
import com.rbt.service.IRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 汇率信息Service层业务接口实现
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 21 12:46:55 CST 2014
 */
@Service
public class RateService extends GenericService<Rate,String> implements IRateService {
	
	IRateDao rateDao;

	@Autowired
	public RateService(IRateDao rateDao) {
		super(rateDao);
		this.rateDao = rateDao;
	}
	
	public void updateendefault() {
		// TODO Auto-generated method stub
		this.rateDao.updateendefault();
	}
	
	public List getAll() {
		return this.rateDao.getAll();
	}
}

