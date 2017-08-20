/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SysmoduleDao.java 
 */
package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.ISysmoduleDao;
import com.rbt.model.Sysmodule;

/**
 * @function 功能  系统模块表dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Fri Jan 13 12:48:48 CST 2014
 */
@Repository
public class SysmoduleDao extends GenericDao<Sysmodule,String> implements ISysmoduleDao {
	
	public SysmoduleDao() {
		super(Sysmodule.class);
	}

}

