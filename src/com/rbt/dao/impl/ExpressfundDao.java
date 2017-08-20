/*
 
 * Package:com.rbt.dao.impl
 * FileName: ExpressfundDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IExpressfundDao;
import com.rbt.model.Expressfund;

/**
 * @function 功能  记录直通车资金信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 23 10:11:33 CST 2014
 */
@Repository
public class ExpressfundDao extends GenericDao<Expressfund,String> implements IExpressfundDao {
	
	public ExpressfundDao() {
		super(Expressfund.class);
	}
	
}

