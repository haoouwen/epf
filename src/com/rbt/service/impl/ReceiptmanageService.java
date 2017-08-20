/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ReceiptmanageService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IReceiptmanageDao;
import com.rbt.model.Receiptmanage;
import com.rbt.service.IReceiptmanageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录单据管理信息Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 30 11:13:55 CST 2014
 */
@Service
public class ReceiptmanageService extends GenericService<Receiptmanage,String> implements IReceiptmanageService {
	
	IReceiptmanageDao receiptmanageDao;

	@Autowired
	public ReceiptmanageService(IReceiptmanageDao receiptmanageDao) {
		super(receiptmanageDao);
		this.receiptmanageDao = receiptmanageDao;
	}

	public boolean dealUpdate(Receiptmanage receiptmanage,String cust_id) {
		if(receiptmanage!=null){
			if(receiptmanage.getReceipt_code()!=null&&receiptmanage.getReceipt_enable()!=null&&receiptmanage.getTrade_id()!=null){
					String[] receipt_code_array = receiptmanage.getReceipt_code().split(",");
					String[] receipt_enable_array = receiptmanage.getReceipt_enable().split(",");
					String[] trade_id_array = receiptmanage.getTrade_id().split(",");
						for(int i =0;i<receipt_code_array.length;i++){
							Receiptmanage rm = new Receiptmanage();
							rm.setReceipt_code(receipt_code_array[i].trim());
							rm.setReceipt_enable(receipt_enable_array[i].trim());
							rm.setTrade_id(trade_id_array[i].trim());
							rm.setCust_id(cust_id);
							this.receiptmanageDao.update(rm);
							
						}
			}else {
				return false;
			}
		}else {
			return false;
		}
		return true;
		
	}

	
	
}

