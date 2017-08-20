/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: PrintstyleService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.IPrintstyleDao;
import com.rbt.model.Printstyle;
import com.rbt.service.IPrintstyleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录打印样式信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Feb 27 15:56:03 CST 2014
 */
@Service
public class PrintstyleService extends GenericService<Printstyle,String> implements IPrintstyleService {
	
	IPrintstyleDao printstyleDao;

	@Autowired
	public PrintstyleService(IPrintstyleDao printstyleDao) {
		super(printstyleDao);
		this.printstyleDao = printstyleDao;
	}

	/**
	 * 通过快递单模板代码获取快递单模板实体
	 * @return
	 */
	public Printstyle getTemplate_code(String template_code) {
		return printstyleDao.getTemplate_code(template_code);
	}


}

