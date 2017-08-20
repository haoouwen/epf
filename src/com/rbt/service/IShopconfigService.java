/*
  
 
 * Package:com.rbt.servie
 * FileName: IShopconfigService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memberuser;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 记录商城设置信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Fri Jan 04 10:10:44 CST 2014
 */

public interface IShopconfigService extends IGenericService<Shopconfig,String>{
	public Shopconfig getByCustID(String id);
	/**
	 * @author : HZX
	 * @date : Feb 10, 2014 4:18:46 PM
	 * @Method Description :运营商审核通过后,会员成为卖家的设置
	 */
	public void ChangeSeller(String cust_id);
	/**
	 * @author : HZX
	 * @param temp_loc 
	 * @date : Feb 13, 2014 4:06:16 PM
	 * @Method Description :获取店铺首页所需值
	 */
	public Map getIndexMap(Shopconfig shopconfig, String temp_loc);
}

