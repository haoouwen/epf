/*
 * Package:com.rbt.servie.impl
 * FileName: GoodsshareService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IGoodsshareDao;
import com.rbt.model.Goodsshare;
import com.rbt.service.IGoodsshareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 晒单Service层业务接口实现
 * @author 创建人 QJY
 * @date 创建日期 Wed Oct 29 14:36:43 CST 2014
 */
@Service
public class GoodsshareService extends GenericService<Goodsshare,String> implements IGoodsshareService {
	
	IGoodsshareDao goodsshareDao;

	@Autowired
	public GoodsshareService(IGoodsshareDao goodsshareDao) {
		super(goodsshareDao);
		this.goodsshareDao = goodsshareDao;
	}
	
	/*
	 * 
	 */
	public List getWebList(Map map){
		return goodsshareDao.getWebList(map);
		
	}

	public int getWebCount(Map map) {
		
		return goodsshareDao.getWebCount(map);
	}

}

