/*
 
 * Package:com.rbt.dao.impl
 * FileName: CapitalmanagementDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ICapitalmanagementDao;
import com.rbt.model.Capitalmanagement;

/**
 * @function 功能  运营商资金管理dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Fri Aug 23 14:17:23 CST 2014
 */
@Repository
public class CapitalmanagementDao extends GenericDao<Capitalmanagement,String> implements ICapitalmanagementDao {
	
	public CapitalmanagementDao() {
		super(Capitalmanagement.class);
	}
	
}

