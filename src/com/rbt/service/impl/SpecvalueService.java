/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SpecvalueService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISpecvalueDao;
import com.rbt.model.Specvalue;
import com.rbt.service.ISpecvalueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录规格值信息Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Thu Jan 10 15:42:06 CST 2014
 */
@Service
public class SpecvalueService extends GenericService<Specvalue,String> implements ISpecvalueService {
	
	ISpecvalueDao specvalueDao;

	@Autowired
	public SpecvalueService(ISpecvalueDao specvalueDao) {
		super(specvalueDao);
		this.specvalueDao = specvalueDao;
	}
	
}

