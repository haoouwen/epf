/*
 
 * Package:com.rbt.servie.impl
 * FileName: CatnavService.java 
 */
package com.rbt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.ICatnavDao;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Catnav;
import com.rbt.service.ICatnavService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 分类信息Service层业务接口实现
 * @author 创建人 ZMS
 * @date 创建日期 Fri Aug 14 20:22:09 CST 2015
 */
@Service
public class CatnavService extends GenericService<Catnav,String> implements ICatnavService {
	
	ICatnavDao catnavDao;

	@Autowired
	public CatnavService(ICatnavDao catnavDao) {
		super(catnavDao);
		this.catnavDao = catnavDao;
	}
	
}

