/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: PushlogisticsService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IPushlogisticsDao;
import com.rbt.model.Pushlogistics;
import com.rbt.service.IPushlogisticsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 物流推送Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Tue Jun 18 09:31:12 CST 2014
 */
@Service
public class PushlogisticsService extends GenericService<Pushlogistics,String> implements IPushlogisticsService {
	
	IPushlogisticsDao pushlogisticsDao;

	@Autowired
	public PushlogisticsService(IPushlogisticsDao pushlogisticsDao) {
		super(pushlogisticsDao);
		this.pushlogisticsDao = pushlogisticsDao;
	}
	
}

