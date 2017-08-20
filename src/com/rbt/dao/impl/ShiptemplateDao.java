/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: ShiptemplateDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IShiptemplateDao;
import com.rbt.model.Shiptemplate;

/**
 * @function 功能  记录运费模板信息dao层业务接口实现类
 * @author 创建人 LJQ
 * @date 创建日期 Thu May 24 15:00:05 CST 2014
 */
@Repository
public class ShiptemplateDao extends GenericDao<Shiptemplate,String> implements IShiptemplateDao {
	
	public ShiptemplateDao() {
		super(Shiptemplate.class);
	}
	
}

