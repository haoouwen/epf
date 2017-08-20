/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SysfundDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISysfundDao;
import com.rbt.model.Sysfund;

/**
 * @function 功能  平台总资金dao层业务接口实现类
 * @author 创建人 HXM
 * @date 创建日期 Sun Jul 13 17:17:59 CST 2014
 */
@Repository
public class SysfundDao extends GenericDao<Sysfund,String> implements ISysfundDao {
	
	public SysfundDao() {
		super(Sysfund.class);
	}
	
}

