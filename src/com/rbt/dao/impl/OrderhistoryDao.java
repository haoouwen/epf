/*
 
 * Package:com.rbt.dao.impl
 * FileName: OrderhistoryDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IOrderhistoryDao;
import com.rbt.model.Orderhistory;

/**
 * @function 功能  订单状态历史记录dao层业务接口实现类
 * @author 创建人 订单历史
 * @date 创建日期 Tue Nov 01 13:15:49 CST 2014
 */
@Repository
public class OrderhistoryDao extends GenericDao<Orderhistory,String> implements IOrderhistoryDao {

	public OrderhistoryDao() {
		super(Orderhistory.class);
	}
}

