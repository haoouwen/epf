/*
 
 * Package:com.rbt.dao.impl
 * FileName: CatnavDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ICatnavDao;
import com.rbt.model.Catnav;

/**
 * @function 功能  分类信息dao层业务接口实现类
 * @author 创建人 ZMS
 * @date 创建日期 Fri Aug 14 20:22:09 CST 2015
 */
@Repository
public class CatnavDao extends GenericDao<Catnav,String> implements ICatnavDao {
	
	public CatnavDao() {
		super(Catnav.class);
	}
	
}

