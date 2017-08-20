/*
 * Package:com.rbt.servie
 * FileName: IRechargeablecardsService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Rechargeablecards;

/**
 * @function 功能 充值卡Service层业务接口实现类
 * @author  创建人 CYC
 * @date  创建日期 Sun Oct 11 14:01:50 CST 2015
 */

public interface IRechargeablecardsService extends IGenericService<Rechargeablecards,String>{
	/**
	 * @Method Description :通过key找到充值卡
	 * @author : CYC
	 * @date : Apr 19, 2014 11:01:04 AM
	 */
	public Rechargeablecards getCardkey(String cardkey) ;
	public void recharge(Rechargeablecards rechargeablecards,String cust_id,String user_id);
	/**
	 * 重新下载
	 * @throws Exception
	 */
	public boolean exprotcradExcel(List excardList, HttpServletResponse response) throws Exception;
}

