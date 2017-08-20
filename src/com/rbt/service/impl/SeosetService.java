/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SeosetService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ISeosetDao;
import com.rbt.model.Seoset;
import com.rbt.service.ISeosetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 SEO优化表Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Fri Jan 04 11:06:43 CST 2014
 */
@Service
public class SeosetService extends GenericService<Seoset,String> implements ISeosetService {
	
	ISeosetDao seosetDao;

	@Autowired
	public SeosetService(ISeosetDao seosetDao) {
		super(seosetDao);
		this.seosetDao = seosetDao;
	}
	
}

