/*
 
 * Package:com.rbt.dao.impl
 * FileName: AudithistoryDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IAudithistoryDao;
import com.rbt.model.Audithistory;

/**
 * @function 功能  记录模块审核历史dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Tue Nov 15 10:35:16 CST 2014
 */
@Repository
public class AudithistoryDao extends GenericDao<Audithistory,String> implements IAudithistoryDao {

	public AudithistoryDao() {
		super(Audithistory.class);
	}
	public List getAuditList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"audithistory.getAuditList", map);
	}
}

