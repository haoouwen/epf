/*
 
 * Package:com.rbt.dao.impl
 * FileName: CancelorderDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ICancelorderDao;
import com.rbt.model.Cancelorder;

/**
 * @function 功能  取消订单理由dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Sat Jan 10 13:47:37 CST 2015
 */
@Repository
public class CancelorderDao extends GenericDao<Cancelorder,String> implements ICancelorderDao {
	
	public CancelorderDao() {
		super(Cancelorder.class);
	}
	
	public Cancelorder getByOrderId(String order_id){
		return (Cancelorder)this.getSqlMapClientTemplate().queryForObject("cancelorder.getByOrderId", order_id);
	}
}

