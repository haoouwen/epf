/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: RolerightDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IRolerightDao;
import com.rbt.model.Roleright;

/**
 * @function 功能 系统管理员dao层接口实现
 * @author 创建人 QJY
 * @date 创建日期 Jun 28, 2014
 */
@Repository
public class RolerightDao extends GenericDao<Roleright, String> implements
		IRolerightDao {

	public RolerightDao() {
		super(Roleright.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IRolerightDao#getRolerightMenuList(java.util.Map)
	 */
	public List getRolerightMenuList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"roleright.getMenuList", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IRolerightDao#getRolerightLogsList(java.util.Map)
	 */
	public List getRolerightLogsList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"roleright.getLogsList", map);
	}

}
