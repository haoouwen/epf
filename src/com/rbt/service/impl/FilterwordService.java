/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: FilterwordService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IFilterwordDao;
import com.rbt.model.Filterword;
import com.rbt.service.IFilterwordService;

/**
 * @function 功能 敏感字Service层业务接口实现
 * @author 创建人 QJY
 * @date 创建日期 Jul 5, 2014 9:56:04 AM
 */
@Service
public class FilterwordService extends GenericService<Filterword,String> implements IFilterwordService {
	
	IFilterwordDao filterwordDao;

	@Autowired
	public FilterwordService(IFilterwordDao filterwordDao) {
		super(filterwordDao);
		this.filterwordDao = filterwordDao;
	}
}
