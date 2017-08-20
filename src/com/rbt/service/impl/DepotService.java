/*
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: DepotService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IDepotDao;
import com.rbt.model.Depot;
import com.rbt.service.IDepotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 仓库信息Service层业务接口实现
 * @author 创建人 ZMS
 * @date 创建日期 Wed Aug 05 20:27:34 CST 2015
 */
@Service
public class DepotService extends GenericService<Depot,String> implements IDepotService {
	
	IDepotDao depotDao;

	@Autowired
	public DepotService(IDepotDao depotDao) {
		super(depotDao);
		this.depotDao = depotDao;
	}
	
}

