/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: AboutusService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.model.Aboutus;
import com.rbt.service.IAboutusService;

/**
 * @function 功能 关于我们Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Mon Jul 11 12:15:32 CST 2014
 */

@Service
public class AboutusService extends GenericService<Aboutus,String> implements IAboutusService {
	
	IAboutusDao aboutusDao;

	@Autowired
	public AboutusService(IAboutusDao aboutusDao) {
		super(aboutusDao);
		this.aboutusDao = aboutusDao;
	}

}

