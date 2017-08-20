/*
  
 
 * Package:com.rbt.servie
 * FileName: IReceiptmanageService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Receiptmanage;

/**
 * @function 功能 记录单据管理信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Wed Jan 30 11:13:55 CST 2014
 */

public interface IReceiptmanageService extends IGenericService<Receiptmanage,String>{
	/**
	 * @author : HZX
	 * @param cust_id 
	 * @date : Feb 11, 2014 5:16:56 PM
	 * @Method Description :处理更新
	 */
	boolean dealUpdate(Receiptmanage receiptmanage, String cust_id);
	
}

