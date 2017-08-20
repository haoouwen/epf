/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SelfsepcvalueService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISelfspecvalueDao;
import com.rbt.model.Selfspecvalue;
import com.rbt.service.ISelfspecvalueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 自定义规格值Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Wed Jan 30 15:33:30 CST 2014
 */
@Service
public class SelfspecvalueService extends GenericService<Selfspecvalue,String> implements ISelfspecvalueService {
	
	ISelfspecvalueDao selfspecvalueDao;

	@Autowired
	public SelfspecvalueService(ISelfspecvalueDao selfsepcvalueDao) {
		super(selfsepcvalueDao);
		this.selfspecvalueDao = selfspecvalueDao;
	}
	
}

