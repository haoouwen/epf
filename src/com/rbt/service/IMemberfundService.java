/*
  
 
 * Package:com.rbt.servie
 * FileName: IMemberfundService.java 
 */
package com.rbt.service;

import java.util.HashMap;
import java.util.Map;
import com.rbt.model.Memberfund;

/**
 * @function 功能 会员资金Service层业务接口实现类
 * @author  创建人 CYC
 * @date  创建日期 Tue Jul 12 09:26:58 CST 2014
 */

public interface IMemberfundService extends IGenericService<Memberfund,String>{

	/**
	 * 方法描述：修改会员资金
	 * @param java.util.Map
	 */
	public void updateMemberfund(Map map);
	//修改会员资金
	public void insertfundoption(String cust_id,String session_user_id,String fund_num);
	//运营商平台的总资金,包括总资金，可用总资金，冻结总资金
	public HashMap getTotalFund();
	
	/**
	 * @author:HXM
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:cust_id：对应冻结/解冻资金账号，freeze_num:冻结/解冻金额，
	 * flag_int：标记冻结（0）/解冻（1）
	 * @Description:出金和入金操作（可用资金和总资金的操作）（单纯的对memberfunt进行资金操作不涉及记录表），返回可用资金
	 */
	public double freezeNum(String cust_id,double freeze_num,int flag_int);
	
	public double freezePlusNum(String cust_id,double freeze_num,int flag_int);
	
	/**
	 * @author:HXM
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:cust_id：对应出金和入金资金账号，outorin_num:出金/入金金额，
	 * flag_int：标记出金（0）/入金（1）
	 * @Description:出金和入金操作（单纯的对memberfunt进行资金操作不涉及记录表），返回可用资金
	 */
	public double outAndInNum(String cust_id,double outorin_num,int flag_int);
	/**
	 * @author:HXM
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:cust_id：添加对应的账号，addNum:添加的金额
	 * @Description:用于在线支付订单对运营商账号的操作，返回可用余额（总资金和冻结资金增加，可用资金不变）
	 */
	public double addNumByOnLion(String cust_id,double addNum);
}

