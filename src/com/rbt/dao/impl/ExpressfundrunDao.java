/*
 
 * Package:com.rbt.dao.impl
 * FileName: ExpressfundrunDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IExpressfundrunDao;
import com.rbt.model.Expressfundrun;

/**
 * @function 功能  记录直通车资金流动信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 23 16:31:46 CST 2014
 */
@Repository
public class ExpressfundrunDao extends GenericDao<Expressfundrun,String> implements IExpressfundrunDao {
	
	public ExpressfundrunDao() {
		super(Expressfundrun.class);
	}
	
}

