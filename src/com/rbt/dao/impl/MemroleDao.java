/*
 
 * Package:com.rbt.dao.impl
 * FileName: MemroleDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMemroleDao;
import com.rbt.model.Memrole;

/**
 * @function 功能  商城后台角色信息dao层业务接口实现类
 * @author 创建人 LSQ
 * @date 创建日期 Thu Jan 24 16:33:37 CST 2014
 */
@Repository
public class MemroleDao extends GenericDao<Memrole,String> implements IMemroleDao {
	
	public MemroleDao() {
		super(Memrole.class);
	}
	
}

