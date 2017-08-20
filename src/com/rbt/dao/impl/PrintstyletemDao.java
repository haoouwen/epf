/*
 
 * Package:com.rbt.dao.impl
 * FileName: PrintstyletemDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IPrintstyletemDao;
import com.rbt.model.Printstyletem;

/**
 * @function 功能  记录打印样式模板信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 28 10:45:36 CST 2014
 */
@Repository
public class PrintstyletemDao extends GenericDao<Printstyletem,String> implements IPrintstyletemDao {
	
	public PrintstyletemDao() {
		super(Printstyletem.class);
	}
	
}

