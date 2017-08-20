/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SelfextendattrService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISelfextendattrDao;
import com.rbt.model.Selfextendattr;
import com.rbt.service.ISelfextendattrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 商品自定义属性Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Sat Feb 16 10:59:08 CST 2014
 */
@Service
public class SelfextendattrService extends GenericService<Selfextendattr,String> implements ISelfextendattrService {
	
	ISelfextendattrDao selfextendattrDao;

	@Autowired
	public SelfextendattrService(ISelfextendattrDao selfextendattrDao) {
		super(selfextendattrDao);
		this.selfextendattrDao = selfextendattrDao;
	}
	public void deleteByGoodsid(String goods_id){
		this.selfextendattrDao.deleteByGoodsid(goods_id);
	} 
}

