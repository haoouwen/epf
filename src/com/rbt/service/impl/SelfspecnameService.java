/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SelfsepcnameService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISelfspecnameDao;
import com.rbt.model.Selfspecname;
import com.rbt.service.ISelfspecnameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 自定义规格名称Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Wed Jan 30 15:32:16 CST 2014
 */
@Service
public class SelfspecnameService extends GenericService<Selfspecname,String> implements ISelfspecnameService {
	
	ISelfspecnameDao selfspecnameDao;

	@Autowired
	public SelfspecnameService(ISelfspecnameDao selfspecnameDao) {
		super(selfspecnameDao);
		this.selfspecnameDao = selfspecnameDao;
	}
	
	public void deleteByGoodsid(String goods_id){
		this.selfspecnameDao.deleteByGoodsid(goods_id);
	}
}

