/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SysconfigDao.java 
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
import com.rbt.common.util.DbUtil;
import com.rbt.dao.ISysconfigDao;
import com.rbt.model.Sysconfig;

/**
 * @function 功能 系统基本设置dao层接口实现
 * @author 创建人 QJY
 * @date 创建日期 Jul 6, 2014 11:15:07 AM
 */
@Repository
public class SysconfigDao extends GenericDao<Sysconfig,String> implements
		ISysconfigDao {

	public SysconfigDao() {
		super(Sysconfig.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.ISysconfigDao#updateSysconfigBatch(java.util.List)
	 */
	public void updateSysconfigBatch(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if (list != null && list.size() > 0) {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap) iter.next();
						executor.update("sysconfig.updateSysconfigBatch", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.ISysconfigDao#getWebname(java.lang.String)
	 */
	public Sysconfig getWebname(String var_value) {
		return (Sysconfig) this.getSqlMapClientTemplate().queryForObject("sysconfig.getwebname",var_value);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ISysconfigDao#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List getAll() {
		return this.getSqlMapClientTemplate().queryForList("sysconfig.getAll");
	}

	public void updateSys(Sysconfig sysconfig) {
		this.getSqlMapClientTemplate().update(getModelName()+".updateSys", sysconfig);
	}

	public void updateByvarname(HashMap map) {
		this.getSqlMapClientTemplate().update("sysconfig.updatevarname", map);
	}

	public void updateParma(Sysconfig sysconfig) {
		this.getSqlMapClientTemplate().update("sysconfig.updateParma",sysconfig);
	}


}
