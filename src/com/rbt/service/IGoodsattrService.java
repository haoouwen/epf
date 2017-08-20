/*
  
 
 * Package:com.rbt.servie
 * FileName: IGoodsattrService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodsattr;

/**
 * @function 功能 商品属性信息Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Tue Jan 29 14:31:37 CST 2014
 */

public interface IGoodsattrService extends IGenericService<Goodsattr,String>{
	public Goodsattr getGoodsattr(String id,String spec_id);

	public List getWebList(Map map);
	
	/**	
	 * @author : WXP
	 * @param :map
	 * @date Feb 26, 2014 10:24:33 AM
	 * @Method Description :根据条件统计库存
	 */
	@SuppressWarnings("unchecked")
	public int getTotalStock(Map map);
	public void deleteByGoodsid(String goods_id);
	
	/**
	 * @author：XBY
	 * @date：Dec 2, 2014 3:17:33 PM
	 * @Method Description：更新库存
	 */
	public void updateStock(Goodsattr goodsattr);
	
	/**
	 * @author :CYC
	 * @date : Apr 19, 2014 10:58:48 AM
	 * @Method Description :获取对象
	 */
	public Goodsattr goodsidAttr(String id);
}

