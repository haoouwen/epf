/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SearchfilterService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISearchfilterDao;
import com.rbt.model.Searchfilter;
import com.rbt.service.ISearchfilterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 前台搜索过滤规则Service层业务接口实现
 * @author 创建人 HXK
 * @date 创建日期 Fri Apr 03 11:11:49 CST 2015
 */
@Service
public class SearchfilterService extends GenericService<Searchfilter,String> implements ISearchfilterService {
	
	ISearchfilterDao searchfilterDao;

	@Autowired
	public SearchfilterService(ISearchfilterDao searchfilterDao) {
		super(searchfilterDao);
		this.searchfilterDao = searchfilterDao;
	}
	
}

