/*
  
 
 * Package:com.rbt.dao
 * FileName: IDirectsellDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Directsell;

/**
 * @function 功能 预售商品dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Wed Jul 17 13:27:41 CST 2014
 */

public interface IDirectsellDao extends IGenericDao<Directsell,String>{
	
	/**
	 *@author :HXM
	 *@date Dec 10, 2014 9:34:53 AM
	 *@Method description:修改库存 
	 */
	public void updateStock(Map map);
	
	/**
	 *@author :CYC
	 *@date Dec 10, 2014 9:34:53 AM
	 *@Method description:获取短信通知列表 
	 */
	public List getdeliverpay(Map map);

	/**
	 * @author : QJY
	 * @date :  
	 * @Method Description :预售商品逻辑删除
	 */
	public void deletetorecycle(List chList);

	
}

