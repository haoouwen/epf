/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: GoodsevalService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IGoodsevalDao;
import com.rbt.model.Goodseval;
import com.rbt.service.IGoodsevalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商品评价表信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Tue Jan 08 11:06:50 CST 2014
 */
@Service
public class GoodsevalService extends GenericService<Goodseval,String> implements IGoodsevalService {
	
	IGoodsevalDao goodsevalDao;

	@Autowired
	public GoodsevalService(IGoodsevalDao goodsevalDao) {
		super(goodsevalDao);
		this.goodsevalDao = goodsevalDao;
	}
	
	@SuppressWarnings("unchecked")
	public int getWebCount(Map map) {
		return this.goodsevalDao.getWebCount(map);
	}
	
	@SuppressWarnings("unchecked")
	public List getWebList(Map map){
		return this.goodsevalDao.getWebList(map);
	}
	@SuppressWarnings("unchecked")
	public void updatebuy(Goodseval goodseval) {
		this.goodsevalDao.updatebuy(goodseval);

	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过goods_name 分组
	 */
	public List getListByGroup(Map map){
		return this.goodsevalDao.getListByGroup(map);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:58:48 AM
	 * @param:
	 * @Description:获得分组以后的条数
	 */
	public int getGroupCount(Map map){
		return this.goodsevalDao.getGroupCount(map);
	}
}

