/*
 
 * Package:com.rbt.dao.impl
 * FileName: AreasetDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IAreasetDao;
import com.rbt.model.Areaset;

/**
 * @function 功能  记录区域设置信息dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Wed Mar 28 13:22:27 CST 2014
 */
@Repository
public class AreasetDao extends GenericDao<Areaset,String> implements IAreasetDao {
	
	public AreasetDao() {
		super(Areaset.class);
	}
	
	public void deleteByShopid(String id) {
		this.getSqlMapClientTemplate().delete(getModelName()+".deleteByShopid", id);
	}
	
}

