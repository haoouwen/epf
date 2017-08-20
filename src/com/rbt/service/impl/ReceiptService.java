/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ReceiptService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IReceiptDao;
import com.rbt.model.Receipt;
import com.rbt.service.IReceiptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录单据模板信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 30 11:12:44 CST 2014
 */
@Service
public class ReceiptService extends GenericService<Receipt,String> implements IReceiptService {
	
	IReceiptDao receiptDao;

	@Autowired
	public ReceiptService(IReceiptDao receiptDao) {
		super(receiptDao);
		this.receiptDao = receiptDao;
	}
	
}

