/*
  
 
 * Package:com.rbt.servie
 * FileName: IBuyeraddrService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Buyeraddr;

/**
 * @function 功能 收货地址管理Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Thu Dec 27 16:33:56 CST 2014
 */

public interface IBuyeraddrService extends IGenericService<Buyeraddr,String>{
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过cust_id 分组
	 */
	public List getListByGroup(Map map);
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:58:48 AM
	 * @param:
	 * @Description:获得分组以后的条数
	 */
	public int getGroupCount(Map map);
	
	  /**
	 * @Method Description :获取商家退货地址
	 * @author: HXK
	 * @date : Oct 31, 2015 10:13:41 AM
	 * @param 
	 * @return return_type
	 */
	public Buyeraddr getbuyerByCust_id(Buyeraddr buyeraddr ,String sell_cust_id);

}

