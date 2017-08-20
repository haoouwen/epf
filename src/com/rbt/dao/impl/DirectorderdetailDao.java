/*
 
 * Package:com.rbt.dao.impl
 * FileName: DirectorderdetailDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IDirectorderdetailDao;
import com.rbt.model.Directorderdetail;
import com.rbt.model.Shopconfig;

/**
 * @function 功能  预售订单商品详细信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jul 17 13:26:38 CST 2014
 */
@Repository
public class DirectorderdetailDao extends GenericDao<Directorderdetail,String> implements IDirectorderdetailDao {
	
	public DirectorderdetailDao() {
		super(Directorderdetail.class);
	}
	//通过order_id获取Directorderdetail的对象
	public Directorderdetail getByOrderId(String order_id) {
		return  (Directorderdetail)this.getSqlMapClientTemplate().queryForObject("directorderdetail.getByOrderId", order_id);
	}

	/**
	 * @author：XBY
	 * @date：Feb 18, 2014 4:44:52 PM
	 * @Method Description：删除订单详情
	 */
	public int deleteByOrderId(String order_id){
		return this.getSqlMapClientTemplate().delete("directorderdetail.deleteByOrderId", order_id);
	}
}

