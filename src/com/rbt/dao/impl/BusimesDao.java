/*
 
 * Package:com.rbt.dao.impl
 * FileName: BusimesDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IBusimesDao;
import com.rbt.model.Busimes;

/**
 * @function 功能  记录商家留言信息dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Fri Mar 30 12:29:33 CST 2014
 */
@Repository
public class BusimesDao extends GenericDao<Busimes,String> implements IBusimesDao {
	
	public BusimesDao() {
		super(Busimes.class);
	}
	
}

