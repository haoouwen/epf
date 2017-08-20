/*
 
 * Package:com.rbt.dao.impl
 * FileName: MemprotectDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMemprotectDao;
import com.rbt.model.Memprotect;
import com.rbt.model.Shopconfig;

/**
 * @function 功能  记录会员密保信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 21 09:21:06 CST 2014
 */
@Repository
public class MemprotectDao extends GenericDao<Memprotect,String> implements IMemprotectDao {
	
	public MemprotectDao() {
		super(Memprotect.class);
	}
	//通过cust_id获取Memprotect的对象
	public Memprotect getByCustID(String cust_id) {
		return  (Memprotect)this.getSqlMapClientTemplate().queryForObject("memprotect.getByCustId", cust_id);
	}
}

