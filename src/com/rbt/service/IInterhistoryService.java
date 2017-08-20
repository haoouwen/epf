/*
  
 
 * Package:com.rbt.servie
 * FileName: IInterhistoryService.java 
 */
package com.rbt.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.rbt.model.Interhistory;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;

/**
 * @function 功能 记录会员积分异动历史Service层业务接口实现类
 * @author  创建人 CYC
 * @date  创建日期 Thu Jul 14 15:01:09 CST 2014
 */

public interface IInterhistoryService extends IGenericService<Interhistory,String>{
	
	/**
	 * @Method Description : 获取每天会员所得的积分数
	 * @author : LJQ
	 * @date : Nov 11, 2014 2:18:22 PM
	 */
	@SuppressWarnings("unchecked")
	public int getInterhistorySumScore(Map map);
	
	/**
	 * @Method Description :运营商找出需要删除的ID值并减掉积分
	 * @author : LJQ
	 * @date : Nov 16, 2014 1:48:15 PM
	 */
	public List getReleaseCustId(Map map);

	
	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 8:56:35 PM
	 * @Method Description :积分兑换资金
	 */
	public void optioninter(Memberfund memberfund,String use_num,String session_cust_id,String session_user_id,Memberinter memberinter,String rech_fund,int rechange_value);
    
	/**	
	 * @author : WXP
	 * @param :
	 * @date May 6, 2014 4:14:30 PM
	 * @Method Description :获取每日积分
	 */
	public String getIntegral(String cust_id,String continue_day,String max_integral,String daily_integral) throws IOException;

}

