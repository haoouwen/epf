/*
  
 
 * Package:com.rbt.servie
 * FileName: ITryapplyService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Tryapply;

/**
 * @function 功能 申请试用Service层业务接口实现类
 * @author  创建人 CYC
 * @date  创建日期 Sat Jul 12 09:25:36 CST 2014
 */

public interface ITryapplyService extends IGenericService<Tryapply,String>{
	
	//免费试用生成订单
	public void addOrder(String chb_id);
	
	//免费试用删掉订单
	public void delOrder(String chb_id);
	
}

