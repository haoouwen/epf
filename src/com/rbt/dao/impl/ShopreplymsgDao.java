/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: ShopreplymsgDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IShopreplymsgDao;
import com.rbt.model.Shopreplymsg;

/**
 * @function 功能  店铺留言表dao层业务接口实现类
 * @author 创建人 LHY
 * @date 创建日期 Thu Feb 28 15:36:59 CST 2014
 */
@Repository
public class ShopreplymsgDao extends GenericDao<Shopreplymsg,String> implements IShopreplymsgDao {
	
	public ShopreplymsgDao() {
		super(Shopreplymsg.class);
	}

	public Shopreplymsg getByMsgId(String id) {
		return (Shopreplymsg) this.getSqlMapClientTemplate().queryForObject(getModelName()+".getByMsgId", id);
	}

	
}

