/*
 
 * Package:com.rbt.dao.impl
 * FileName: AuditmodelDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IAuditmodelDao;
import com.rbt.model.Auditmodel;

/**
 * @function 功能  审核模型信息dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Mon Aug 06 15:40:22 CST 2014
 */
@Repository
public class AuditmodelDao extends GenericDao<Auditmodel,String> implements IAuditmodelDao {
	
	public AuditmodelDao() {
		super(Auditmodel.class);
	}
	public List getModelList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"auditmodel.getModelList", map);
	}
	public int getModelCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"auditmodel.getModelCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
	/**
	 * 获取某一个用户需要审核的模块信息
	 * @param map
	 * @return
	 */
	public List getAuditList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"auditmodel.getAuditList", map);
	}
}

