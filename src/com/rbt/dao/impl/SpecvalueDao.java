/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: SpecvalueDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.ISpecvalueDao;
import com.rbt.model.Specvalue;

/**
 * @function 功能  记录规格值信息dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Thu Jan 10 15:42:06 CST 2014
 */
@Repository
public class SpecvalueDao extends GenericDao<Specvalue,String> implements ISpecvalueDao {
	
	public SpecvalueDao() {
		super(Specvalue.class);
	}
	
}

