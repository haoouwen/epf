/*
 
 * Package:com.rbt.dao.impl
 * FileName: OrderdetailDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IOrderdetailDao;
import com.rbt.model.Orderdetail;

/**
 * @function 功能  记录订单商品详细信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Fri Jan 11 16:38:19 CST 2014
 */
@Repository
public class OrderdetailDao extends GenericDao<Orderdetail,String> implements IOrderdetailDao {
	
	public OrderdetailDao() {
		super(Orderdetail.class);
	}
	public int getBuyCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"orderdetail.getBuyCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
	
	public void updateState(Map map){
		this.getSqlMapClientTemplate().update("orderdetail.updateState", map);
	}
	
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 4:44:52 PM
	 * @Method Description：删除订单详情
	 */
	public int deleteByOrderId(String order_id){
		return this.getSqlMapClientTemplate().delete("orderdetail.deleteByOrderId", order_id);
	}
}

