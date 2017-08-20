/*
 * Package:com.rbt.dao.impl
 * FileName: KjtrecallDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IKjtrecallDao;
import com.rbt.model.Kjtrecall;

/**
 * @function 功能  跨境通回调表dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Fri Sep 18 16:21:49 CST 2015
 */
@Repository
public class KjtrecallDao extends GenericDao<Kjtrecall,String> implements IKjtrecallDao {
	
	public KjtrecallDao() {
		super(Kjtrecall.class);
	}
	
	 public void updatekjtpur(Map map){
		 this.getSqlMapClientTemplate().update(getModelName()+".updatepur",map);
	 }
	 /**
	 * @Method Description :通过订单ID查找海关回传对象值
	 * @author: 胡惜坤
	 * @date : Feb 24, 2016 11:33:23 AM
	 * @param 
	 * @return return_type
	 */
	public Kjtrecall getByOrderId(String order_id){
		return (Kjtrecall)this.getSqlMapClientTemplate().queryForObject(getModelName()+".getByOrderId", order_id);
	}
}

