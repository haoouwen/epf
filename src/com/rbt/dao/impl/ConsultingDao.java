/*
 
 * Package:com.rbt.dao.impl
 * FileName: ConsultingDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IConsultingDao;
import com.rbt.model.Consulting;
import com.rbt.model.Shopconfig;

/**
 * @function 功能  记录商品咨询l回复信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 28 16:48:34 CST 2014
 */
@Repository
public class ConsultingDao extends GenericDao<Consulting,String> implements IConsultingDao {
	
	public ConsultingDao() {
		super(Consulting.class);
	}
	//通过Trade_id获取ConsultingDao的对象
	public Consulting getByTradeId(String trade_id) {
		return  (Consulting)this.getSqlMapClientTemplate().queryForObject("consulting.getByTradeId", trade_id);
	}
}

