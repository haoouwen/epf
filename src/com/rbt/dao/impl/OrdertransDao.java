/*
 
 * Package:com.rbt.dao.impl
 * FileName: OrdertransDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IOrdertransDao;
import com.rbt.model.Ordertrans;

/**
 * @function 功能  记录商品订单异动信息dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Thu Feb 28 10:02:15 CST 2014
 */
@Repository
public class OrdertransDao extends GenericDao<Ordertrans,String> implements IOrdertransDao {
	
	public OrdertransDao() {
		super(Ordertrans.class);
	}
	
	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 4:44:52 PM
	 * @Method Description：删除订单详情
	 */
	public int deleteByOrderId(String order_id){
		return this.getSqlMapClientTemplate().delete("ordertrans.deleteByOrderId", order_id);
	}
}

