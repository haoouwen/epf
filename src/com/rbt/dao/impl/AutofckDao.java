/*
 
 * Package:com.rbt.dao.impl
 * FileName: AutofckDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IAutofckDao;
import com.rbt.model.Autofck;

/**
 * @function 功能  在线编辑器dao层业务接口实现类
 * @author 创建人 LHY
 * @date 创建日期 Mon Jan 28 12:54:12 CST 2014
 */
@Repository
public class AutofckDao extends GenericDao<Autofck,String> implements IAutofckDao {
	
	public AutofckDao() {
		super(Autofck.class);
	}

	public void updaterandom(Map map) {
		this.getSqlMapClientTemplate().update(this.getModelName()+".updaterandom",map);
		
	}

	public Autofck getrandom(Map map) {
		return (Autofck) this.getSqlMapClientTemplate().queryForObject(this.getModelName()+".getrandom",map);
	}

	

}

