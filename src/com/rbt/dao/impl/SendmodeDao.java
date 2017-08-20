/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SendmodeDao.java 
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
import com.rbt.dao.ISendmodeDao;
import com.rbt.model.Member;
import com.rbt.model.Sendmode;

/**
 * @function 功能  配送方式表dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Fri Mar 23 09:55:49 CST 2014
 */
@Repository
public class SendmodeDao extends GenericDao<Sendmode,String> implements ISendmodeDao {
	
	public SendmodeDao() {
		super(Sendmode.class);
	}

	
	public Sendmode getByEnname(String en_name) {
		return (Sendmode) this.getSqlMapClientTemplate().queryForObject("sendmode.getByEnname",en_name);
	}
	
}

