/*
  
 
 * Package:com.rbt.servie
 * FileName: IMembercreditService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Membercredit;

/**
 * @function 功能 会员信誉Service层业务接口实现类
 * @author  创建人 XBY
 * @date  创建日期 Tue Apr 22 19:59:28 CST 2014
 */

public interface IMembercreditService extends IGenericService<Membercredit,String>{
	
	/**
	 * 通过UserId获取会员信誉
	 * @param membercredit
	 * @return
	 */
	public Membercredit getByCustId(String cust_id);
	
	/**
	 * 修改买家信誉
	 * @param membercredit
	 */
	public void updateBuyNum(Membercredit membercredit);
}

