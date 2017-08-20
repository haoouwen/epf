/*
 
 * Package:com.rbt.servie.impl
 * FileName: InternationaltariffService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IInternationaltariffDao;
import com.rbt.model.Internationaltariff;
import com.rbt.service.IInternationaltariffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 国际物流Service层业务接口实现
 * @author 创建人 ZMS
 * @date 创建日期 Thu Aug 20 18:55:20 CST 2015
 */
@Service
public class InternationaltariffService extends GenericService<Internationaltariff,String> implements IInternationaltariffService {
	
	IInternationaltariffDao internationaltariffDao;

	@Autowired
	public InternationaltariffService(IInternationaltariffDao internationaltariffDao) {
		super(internationaltariffDao);
		this.internationaltariffDao = internationaltariffDao;
	}
	
}

