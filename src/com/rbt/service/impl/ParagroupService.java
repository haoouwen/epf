/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ParagroupService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IParagroupDao;
import com.rbt.model.Paragroup;
import com.rbt.service.IParagroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 商品扩展属性信息Service层业务接口实现
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 16 10:14:11 CST 2014
 */
@Service
public class ParagroupService extends GenericService<Paragroup,String> implements IParagroupService {
	
	

	IParagroupDao paragroupDao;

	@Autowired
	public ParagroupService(IParagroupDao paragroupDao) {
		super(paragroupDao);
		this.paragroupDao = paragroupDao;
	}
	
}

