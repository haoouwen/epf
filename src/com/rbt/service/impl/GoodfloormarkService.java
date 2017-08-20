/*
 
 * Package:com.rbt.servie.impl
 * FileName: GoodfloormarkService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IGoodfloormarkDao;
import com.rbt.model.Goodfloormark;
import com.rbt.service.IGoodfloormarkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 商品楼层信息Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Mon Aug 10 11:00:14 CST 2015
 */
@Service
public class GoodfloormarkService extends GenericService<Goodfloormark,String> implements IGoodfloormarkService {
	
	IGoodfloormarkDao goodfloormarkDao;

	@Autowired
	public GoodfloormarkService(IGoodfloormarkDao goodfloormarkDao) {
		super(goodfloormarkDao);
		this.goodfloormarkDao = goodfloormarkDao;
	}
	/**
	 * @Method Description :获取楼层标签对于的商品列表
	 * @author: HXK
	 * @date : Aug 15, 2015 10:32:50 AM
	 * @param 
	 * @return return_type
	 */
	public List getGoodsFloorList(Map map){
		return this.goodfloormarkDao.getGoodsFloorList(map);
	}
	
	
}

