/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: GoodsattrService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IGoodsattrDao;
import com.rbt.model.Goodsattr;
import com.rbt.service.IGoodsattrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 商品属性信息Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jan 29 14:31:37 CST 2014
 */
@Service
public class GoodsattrService extends GenericService<Goodsattr,String> implements IGoodsattrService {
	
	IGoodsattrDao goodsattrDao;

	@Autowired
	public GoodsattrService(IGoodsattrDao goodsattrDao) {
		super(goodsattrDao);
		this.goodsattrDao = goodsattrDao;
	}

	public Goodsattr getGoodsattr(String id,String spec_id) {
		return this.goodsattrDao.getGoodsattr(id, spec_id);
	}

	public List getWebList(Map map){
		return this.goodsattrDao.getWebList(map);
	}
	
	public int getTotalStock(Map map) {
		return this.goodsattrDao.getTotalStock(map);
	}
	public void deleteByGoodsid(String goods_id){
		this.goodsattrDao.deleteByGoodsid(goods_id);
	}
	
	public void updateStock(Goodsattr goodsattr){
		this.goodsattrDao.updateStock(goodsattr);
	}
	
	/**
	 * @author :CYC
	 * @date : Apr 19, 2014 10:58:48 AM
	 * @Method Description :获取对象
	 */
	public Goodsattr goodsidAttr(String id){
		return this.goodsattrDao.goodsidAttr(id);
	}
}

