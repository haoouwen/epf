/*
  
 
 * Package:com.rbt.dao
 * FileName: IGoodsattrDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;
import com.rbt.model.Goodsattr;

/**
 * @function 功能 商品属性信息dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Tue Jan 29 14:31:37 CST 2014
 */

public interface IGoodsattrDao extends IGenericDao<Goodsattr,String>{
	public Goodsattr getGoodsattr(String id,String spec_id);
	/**
	 * @author : LJQ
	 * @date : Feb 19, 2014 10:57:14 AM
	 * @Method Description :根据商品ID删除数据
	 */
	public void deleteByGoodsid(String goods_id);
	
	/**	
	 * @author : WXP
	 * @param :goods_id
	 * @date Feb 20, 2014 2:24:39 PM
	 * @Method Description :前台获取商品属性
	 */
	public List getWebList(Map map);
	
	
	/**	
	 * @author : WXP
	 * @param :map
	 * @date Feb 26, 2014 10:24:33 AM
	 * @Method Description :根据条件统计库存
	 */
	@SuppressWarnings("unchecked")
	public int getTotalStock(Map map);
	
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


