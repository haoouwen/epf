/*
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: DepotDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IDepotDao;
import com.rbt.model.Depot;

/**
 * @function 功能  仓库信息dao层业务接口实现类
 * @author 创建人 ZMS
 * @date 创建日期 Wed Aug 05 20:27:34 CST 2015
 */
@Repository
public class DepotDao extends GenericDao<Depot,String> implements IDepotDao {
	
	public DepotDao() {
		super(Depot.class);
	}
	
}

