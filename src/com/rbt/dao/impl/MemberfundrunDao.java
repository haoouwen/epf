/*
 
 * Package:com.rbt.dao.impl
 * FileName: MemberfundrunDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IMemberfundrunDao;
import com.rbt.model.Memberfundrun;

/**
 * @function 功能  审核余额调整dao层业务接口实现类
 * @author 创建人 XBY
 * @date 创建日期 Mon Jun 09 15:36:58 CST 2014
 */
@Repository
public class MemberfundrunDao extends GenericDao<Memberfundrun,String> implements IMemberfundrunDao {
	
	public MemberfundrunDao() {
		super(Memberfundrun.class);
	}
	
}

