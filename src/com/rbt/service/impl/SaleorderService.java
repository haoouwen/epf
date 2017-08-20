/*
 
 * Package:com.rbt.servie.impl
 * FileName: SaleorderService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISaleorderDao;
import com.rbt.model.Saleorder;
import com.rbt.service.ISaleorderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 订单促销Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Wed Aug 12 11:01:23 CST 2015
 */
@Service
public class SaleorderService extends GenericService<Saleorder,String> implements ISaleorderService {
	
	ISaleorderDao saleorderDao;

	@Autowired
	public SaleorderService(ISaleorderDao saleorderDao) {
		super(saleorderDao);
		this.saleorderDao = saleorderDao;
	}
	
}

