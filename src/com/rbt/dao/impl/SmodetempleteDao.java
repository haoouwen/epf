/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SmodetempleteDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISmodetempleteDao;
import com.rbt.model.Smodetemplete;

/**
 * @function 功能  记录区域设置信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Thu Jan 10 10:52:23 CST 2014
 */
@Repository
public class SmodetempleteDao extends GenericDao<Smodetemplete,String> implements ISmodetempleteDao {
	
	public SmodetempleteDao() {
		super(Smodetemplete.class);
	}
	public Smodetemplete getSmodetempleteBySmodeId(String smode_id){
		return (Smodetemplete) this.getSqlMapClientTemplate().queryForObject(
				"smodetemplete.getPkBySmodeId", smode_id);
	}

	
}

