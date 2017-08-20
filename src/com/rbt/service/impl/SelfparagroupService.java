/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SelfparagroupService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISelfparagroupDao;
import com.rbt.model.Selfparagroup;
import com.rbt.service.ISelfparagroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录自定义参数组信息Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Sat Feb 16 13:36:38 CST 2014
 */
@Service
public class SelfparagroupService extends GenericService<Selfparagroup,String> implements ISelfparagroupService {
	
	ISelfparagroupDao selfparagroupDao;

	@Autowired
	public SelfparagroupService(ISelfparagroupDao selfparagroupDao) {
		super(selfparagroupDao);
		this.selfparagroupDao = selfparagroupDao;
	}
	public void deleteByGoodsid(String goods_id){
		this.selfparagroupDao.deleteByGoodsid(goods_id);
	}
}

