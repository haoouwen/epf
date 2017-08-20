/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SelfparavalueDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISelfparavalueDao;
import com.rbt.model.Selfparavalue;

/**
 * @function 功能  记录自定义参数值信息dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Sat Feb 16 14:34:05 CST 2014
 */
@Repository
public class SelfparavalueDao extends GenericDao<Selfparavalue,String> implements ISelfparavalueDao {
	
	public SelfparavalueDao() {
		super(Selfparavalue.class);
	}
	
}

