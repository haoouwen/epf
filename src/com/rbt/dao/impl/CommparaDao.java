/*
 
 * Package:com.rbt.dao.impl
 * FileName: CommparaDao.java 
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
import com.rbt.dao.ICommparaDao;
import com.rbt.model.Commpara;
/**
 * @function 功能 系统参数dao层接口实现
 * @author  创建人 HXK
 * @date  创建日期  July 6, 2014
 */
@Repository
public class CommparaDao extends GenericDao<Commpara,String> implements ICommparaDao {

	public CommparaDao() {
		super(Commpara.class);
	}
	/* (non-Javadoc)
	 * @see com.rbt.dao.ICommparaDao#getWebCommparaList(java.util.Map)
	 */
	public List getWebCommparaList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("commpara.getWebCommparaList", map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ICommparaDao#getCommparaGroupList(java.util.Map)
	 */
	public List getCommparaGroupList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("commpara.getGroupList", map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.ICommparaDao#getCommparaIndexList(java.util.Map)
	 */
	public List getCommparaIndexList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("commpara.getCommparaIndexList",map);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:37:14 AM
	 * @param:
	 * @Description:分组查询通过cust_id 分组
	 */
	public List getListByGroup(Map map) {
		return this.getSqlMapClientTemplate().queryForList("commpara.getListByGroup",map);
	}
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:54:39 AM
	 * @param:
	 * @Description:查询获得分组条数
	 */
	public int getGroupCount(Map map){
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject("commpara.getGroupCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}
}
