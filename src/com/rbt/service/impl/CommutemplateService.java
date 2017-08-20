/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: CommutemplateService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.ICommutemplateDao;
import com.rbt.model.Commutemplate;
import com.rbt.service.ICommutemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录会员发送模板信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Sat Dec 29 13:20:17 CST 2014
 */
@Service
public class CommutemplateService extends GenericService<Commutemplate,String> implements ICommutemplateService {
	
	ICommutemplateDao commutemplateDao;

	@Autowired
	public CommutemplateService(ICommutemplateDao commutemplateDao) {
		super(commutemplateDao);
		this.commutemplateDao = commutemplateDao;
	}
	public Commutemplate getByTempcode(String temp_code){
		return  (Commutemplate) this.commutemplateDao.getByTempcode(temp_code);
	}
}

