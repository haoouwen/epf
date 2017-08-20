/*
 
 * Package:com.rbt.dao.impl
 * FileName: OrganizeDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IOrganizeDao;
import com.rbt.model.Organize;

/**
 * @function 功能  记录组织部门dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Mon Nov 07 13:37:36 CST 2014
 */
@Repository
public class OrganizeDao extends GenericDao<Organize,String> implements IOrganizeDao {

	public OrganizeDao() {
		super(Organize.class);
	}
	@SuppressWarnings("unchecked")
	public List getSysList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getAreaProxyList",map);
	}

	public int getCounts(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getCounts", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	public List getAll() {
		return this.getSqlMapClientTemplate().queryForList("organize.getAll");
	}
	public List getDeleteList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("organize.getDeleteList",map);
	}
	

	
}

