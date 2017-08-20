/*
 
 * Package:com.rbt.dao.impl
 * FileName: GoodsattrDao.java 
 */
package com.rbt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IGoodsattrDao;
import com.rbt.model.Goodsattr;
import com.rbt.model.Memberuser;

/**
 * @function 功能  商品属性信息dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Jan 29 14:31:37 CST 2014
 */
@Repository
public class GoodsattrDao extends GenericDao<Goodsattr,String> implements IGoodsattrDao {
	
	public GoodsattrDao() {
		super(Goodsattr.class);
	}

	public Goodsattr getGoodsattr(String goods_id,String spec_id) {
		Map map=new HashMap();
		map.put("goods_id", goods_id);
		map.put("spec_id", spec_id);
		return (Goodsattr) this.getSqlMapClientTemplate().queryForObject(getModelName()+".getByGoodsId", map);
	}
	/**
	 * @author : LJQ
	 * @date : Feb 19, 2014 10:52:44 AM
	 * @Method Description :根据商品ID删除信息
	 */
	public void deleteByGoodsid(String goods_id) {
		this.getSqlMapClientTemplate().delete("goodsattr.deleteByGoodsid", goods_id);
	}
	/**	
	 * @author : WXP
	 * @param :goods_id
	 * @date Feb 20, 2014 2:24:39 PM
	 * @Method Description :前台获取商品属性
	 */
	public List getWebList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("goodsattr.getWebList",map);
	}
	
	/**	
	 * @author : WXP
	 * @param :map
	 * @date Feb 26, 2014 10:24:33 AM
	 * @Method Description :根据条件统计库存
	 */
	public int getTotalStock(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("goodsattr.getTotalStock", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	
	/**
	 * @author：XBY
	 * @date：Dec 2, 2014 3:17:33 PM
	 * @Method Description：更新库存
	 */
	public void updateStock(Goodsattr goodsattr){
		this.getSqlMapClientTemplate().update("goodsattr.updateStock",goodsattr);
	}
	
	/**
	 * @author :CYC
	 * @date : Apr 19, 2014 10:58:48 AM
	 * @Method Description :获取对象
	 */
	public Goodsattr goodsidAttr(String id) {
		return (Goodsattr)this.getSqlMapClientTemplate().queryForObject(getModelName()+".getGoodsPk", id);
	}
}

