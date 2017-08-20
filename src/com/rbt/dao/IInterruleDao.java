/*
  
 
 * Package:com.rbt.dao
 * FileName: IInterruleDao.java 
 */
package com.rbt.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.model.Interrule;

/**
 * @function 功能 积分规则表dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Thu Nov 10 14:26:30 CST 2014
 */

public interface IInterruleDao extends IGenericDao<Interrule,String>{
	
	public void updateInterruleList(final List list);
}

