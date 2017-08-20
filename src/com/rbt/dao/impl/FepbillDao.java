/*
 * Package:com.rbt.dao.impl
 * FileName: FepbillDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IFepbillDao;
import com.rbt.model.Fepbill;

/**
 * @function 功能  代购汇账单dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Sep 23 13:22:25 CST 2015
 */
@Repository
public class FepbillDao extends GenericDao<Fepbill,String> implements IFepbillDao {
	
	public FepbillDao() {
		super(Fepbill.class);
	}
	
}

