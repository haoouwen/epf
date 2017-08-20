/*
 
 * Package:com.rbt.servie
 * FileName: IOrderinvoiceService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memberuser;
import com.rbt.model.Orderinvoice;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 发票Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Thu Aug 13 13:00:29 CST 2015
 */

public interface IOrderinvoiceService extends IGenericService<Orderinvoice,String>{
	public Orderinvoice getByCustID(String id);
	public Orderinvoice getByInvoid(String id);
}

