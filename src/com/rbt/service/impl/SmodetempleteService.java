/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SmodetempleteService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISmodetempleteDao;
import com.rbt.model.Emailtemplate;
import com.rbt.model.Goods;
import com.rbt.model.Smodetemplete;
import com.rbt.service.ISmodetempleteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录区域设置信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Thu Jan 10 10:52:23 CST 2014
 */

@Service
public  class SmodetempleteService extends GenericService<Smodetemplete,String> implements ISmodetempleteService {
	
	ISmodetempleteDao smodetempleteDao;
	@Autowired
	public SmodetempleteService(ISmodetempleteDao smodetempleteDao) {
		super(smodetempleteDao);
		this.smodetempleteDao = smodetempleteDao;
	}
	public Smodetemplete getSmodetempleteBySmodeId(String smode_id) {
		// TODO Auto-generated method stub	public Smodetemplete getSmodetemplateBySmodeId(String smode_id) {
		return this.smodetempleteDao.getSmodetempleteBySmodeId(smode_id);
	}
}

