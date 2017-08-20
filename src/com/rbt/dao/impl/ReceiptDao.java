/*
 
 * Package:com.rbt.dao.impl
 * FileName: ReceiptDao.java 
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rbt.dao.IReceiptDao;
import com.rbt.model.Receipt;

/**
 * @function 功能  记录单据模板信息dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 30 11:12:44 CST 2014
 */
@Repository
public class ReceiptDao extends GenericDao<Receipt,String> implements IReceiptDao {
	
	public ReceiptDao() {
		super(Receipt.class);
	}
	
}

