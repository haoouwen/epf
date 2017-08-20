/*
  
 
 * Package:com.rbt.servie
 * FileName: IMembercatService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Membercat;

/**
 * @function 功能 记录会员自定义分类信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Fri Jan 11 09:28:06 CST 2014
 */

public interface IMembercatService extends IGenericService<Membercat,String>{
	public List getDeleteList(Map map);
	public List getAll();
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 4:41:25 PM
	 * @Method Description :递归删除
	 */
	public boolean dealDelete(String id,String session_cust_id);
	/**
	 * @Method Description :删除分类前，先判断是否已经有商品绑定了，如果没有可以删除，否则不让删除
	 * @author: HXK
	 * @date : May 29, 2014 6:16:07 PM
	 * @param 
	 * @return return_type
	 */
    public boolean memCatGoods(String cat_id,String cust_id);
	
}

