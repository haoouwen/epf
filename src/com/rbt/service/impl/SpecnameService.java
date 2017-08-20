/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SpecnameService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISpecnameDao;
import com.rbt.model.Specname;
import com.rbt.service.ISpecnameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录规格名称信息Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Thu Jan 10 15:40:54 CST 2014
 */
@Service
public class SpecnameService extends GenericService<Specname,String> implements ISpecnameService {
	
	ISpecnameDao specnameDao;

	@Autowired
	public SpecnameService(ISpecnameDao specnameDao) {
		super(specnameDao);
		this.specnameDao = specnameDao;
	}
	
}

