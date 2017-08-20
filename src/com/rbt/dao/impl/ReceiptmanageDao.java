/*
  
 
 * Package:com.rbt.dao.impl
 * FileName: ReceiptmanageDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IReceiptmanageDao;
import com.rbt.model.Receiptmanage;

/**
 * @function 功能  记录单据管理信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 30 11:13:55 CST 2014
 */
@Repository
public class ReceiptmanageDao extends GenericDao<Receiptmanage,String> implements IReceiptmanageDao {
	
	public ReceiptmanageDao() {
		super(Receiptmanage.class);
	}
	
}

