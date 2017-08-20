/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: PrintstyletemService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IPrintstyletemDao;
import com.rbt.model.Printstyletem;
import com.rbt.service.IPrintstyletemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录打印样式模板信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 28 10:45:36 CST 2014
 */
@Service
public class PrintstyletemService extends GenericService<Printstyletem,String> implements IPrintstyletemService {
	
	IPrintstyletemDao printstyletemDao;

	@Autowired
	public PrintstyletemService(IPrintstyletemDao printstyletemDao) {
		super(printstyletemDao);
		this.printstyletemDao = printstyletemDao;
	}
	
}

