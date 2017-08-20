/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: FundtocashService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IFundtocashDao;
import com.rbt.model.Fundtocash;
import com.rbt.service.IFundtocashService;

/**
 * @function 功能 会员资金提现记录Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Wed Jul 13 09:52:27 CST 2014
 */
@Service
public class FundtocashService extends GenericService<Fundtocash,String> implements IFundtocashService {

	
	IFundtocashDao fundtocashDao;

	@Autowired
	public FundtocashService(IFundtocashDao fundtocashDao) {
		super(fundtocashDao);
		this.fundtocashDao = fundtocashDao;
	}
	
}

