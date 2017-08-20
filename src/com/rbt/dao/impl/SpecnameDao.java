/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SpecnameDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISpecnameDao;
import com.rbt.model.Specname;

/**
 * @function 功能  记录规格名称信息dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Thu Jan 10 15:40:54 CST 2014
 */
@Repository
public class SpecnameDao extends GenericDao<Specname,String> implements ISpecnameDao {
	
	public SpecnameDao() {
		super(Specname.class);
	}
	
}

