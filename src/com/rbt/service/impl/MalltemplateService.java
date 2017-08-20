/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MalltemplateService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.IMalltemplateDao;
import com.rbt.model.Malltemplate;
import com.rbt.service.IMalltemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 商城模板信息Service层业务接口实现
 * @author 创建人 LSQ
 * @date 创建日期 Fri Dec 28 10:31:27 CST 2014
 */
@Service
public class MalltemplateService extends GenericService<Malltemplate,String> implements IMalltemplateService {
	
	IMalltemplateDao malltemplateDao;
	

	@Autowired
	public MalltemplateService(IMalltemplateDao malltemplateDao) {
		super(malltemplateDao);
		this.malltemplateDao = malltemplateDao;
	}
	
	public void updateisenable() {
		// TODO Auto-generated method stub
		this.malltemplateDao.updateisenable();
	}
	
}

