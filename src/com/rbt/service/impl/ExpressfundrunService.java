/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ExpressfundrunService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.IExpressfundrunDao;
import com.rbt.model.Expressfundrun;
import com.rbt.service.IExpressfundrunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录直通车资金流动信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 23 16:31:46 CST 2014
 */
@Service
public class ExpressfundrunService extends GenericService<Expressfundrun,String> implements IExpressfundrunService {
	
	IExpressfundrunDao expressfundrunDao;

	@Autowired
	public ExpressfundrunService(IExpressfundrunDao expressfundrunDao) {
		super(expressfundrunDao);
		this.expressfundrunDao = expressfundrunDao;
	}
	
}

