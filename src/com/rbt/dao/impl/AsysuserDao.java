/*
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: AsysuserDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IAsysuserDao;
import com.rbt.model.Asysuser;

/**
 * @function 功能  代理商dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Aug 05 14:52:52 CST 2015
 */
@Repository
public class AsysuserDao extends GenericDao<Asysuser,String> implements IAsysuserDao {
	
	public AsysuserDao() {
		super(Asysuser.class);
	}
	public void updatelaststate(Asysuser asysuser) {
		this.getSqlMapClientTemplate().update("asysuser.updatelaststate", asysuser);		
	}
	
	public int getWebCount(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("asysuser.getWebCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	public List getWebList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("asysuser.getWebList",map);
	}
	
	
}

