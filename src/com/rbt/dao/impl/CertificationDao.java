/*
 
 * Package:com.rbt.dao.impl
 * FileName: CertificationDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.ICertificationDao;
import com.rbt.model.Certification;

/**
 * @function 功能  记录企业身份认证信息dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Wed Nov 30 13:34:38 CST 2014
 */
@Repository
public class CertificationDao extends GenericDao<Certification,String> implements ICertificationDao {
	
	public CertificationDao() {
		super(Certification.class);
	}
	
	public void auditState(Certification t) {
		this.getSqlMapClientTemplate().update("certification.auditState", t);
	}
}

