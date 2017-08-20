/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ConsultingService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.IConsultingDao;
import com.rbt.model.Consulting;
import com.rbt.service.IConsultingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录商品咨询l回复信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 28 16:48:34 CST 2014
 */
@Service
public class ConsultingService extends GenericService<Consulting,String> implements IConsultingService {
	
	IConsultingDao consultingDao;

	@Autowired
	public ConsultingService(IConsultingDao consultingDao) {
		super(consultingDao);
		this.consultingDao = consultingDao;
	}
	public Consulting getByTradeId(String trade_id){
		return  (Consulting) this.consultingDao.getByTradeId(trade_id);
	}
	
}

