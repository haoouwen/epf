/*
 
 * Package:com.rbt.dao.impl
 * FileName: ParavalueDao.java 
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
import com.rbt.dao.IParavalueDao;
import com.rbt.model.Memberuser;
import com.rbt.model.Paravalue;

/**
 * @function 功能  参数值信息dao层业务接口实现类
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 16 16:29:11 CST 2014
 */
@Repository
public class ParavalueDao extends GenericDao<Paravalue,String> implements IParavalueDao {
	
	public ParavalueDao() {
		super(Paravalue.class);
	}
	
	
	public List getParaValList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("paravalue.getParaList",map);
	}

	
}

