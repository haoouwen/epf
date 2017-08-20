/*
 
 * Package:com.rbt.dao.impl
 * FileName: MalltemplateDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMalltemplateDao;
import com.rbt.model.Malltemplate;

/**
 * @function 功能  商城模板信息dao层业务接口实现类
 * @author 创建人 LSQ
 * @date 创建日期 Fri Dec 28 10:31:27 CST 2014
 */
@Repository
public class MalltemplateDao extends GenericDao<Malltemplate,String> implements IMalltemplateDao {

	public MalltemplateDao() {
		super(Malltemplate.class);
	}
	
	public void updateisenable() {
		this.getSqlMapClientTemplate().update("malltemplate.updateisenable");
	}
	
}

