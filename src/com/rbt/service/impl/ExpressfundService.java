/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ExpressfundService.java 
 */
package com.rbt.service.impl;
import com.rbt.dao.IExpressfundDao;
import com.rbt.model.Expressfund;
import com.rbt.service.IExpressfundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录直通车资金信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 23 10:11:33 CST 2014
 */
@Service
public class ExpressfundService extends GenericService<Expressfund,String> implements IExpressfundService {
	
	IExpressfundDao expressfundDao;

	@Autowired
	public ExpressfundService(IExpressfundDao expressfundDao) {
		super(expressfundDao);
		this.expressfundDao = expressfundDao;
	}
	
}

