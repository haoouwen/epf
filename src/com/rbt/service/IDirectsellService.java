/*
  
 
 * Package:com.rbt.servie
 * FileName: IDirectsellService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Directsell;
import com.rbt.model.Groupgoods;

/**
 * @function 功能 预售商品Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Wed Jul 17 13:27:41 CST 2014
 */

public interface IDirectsellService extends IGenericService<Directsell,String>{

	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:45:47 PM
	 * @Method Description :新增阶梯价格
	 */
	public void insertladprice(String id,String lownums,String ladprices);
	
	
	
	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:45:55 PM
	 * @Method Description :新增预售数据
	 */
	public String  insertDirectsell(Directsell directsell,String lownums,String ladprices);
	
	/**
	 * @author : HZX
	 * @date : Jul 17, 2014 4:46:03 PM
	 * @Method Description :更新阶梯价格
	 */
	public void updateDirectladder(String id,String lownums,String ladprices);
	
	/**
	 *@author :HXM
	 *@date Dec 10, 2014 9:41:47 AM
	 *@Method description:修改库存 
	 */
	public void updateStock(Map map);

	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 10:43:56 AM
	 * @Method Description：预售价格详细
	 */
	public Map detail(String directs_id)throws Exception;
	
	/**
	 * @author：CYC
	 * @date：Feb 17, 2014 10:43:56 AM
	 * @Method Description：预售价格详细
	 */
	public List getdeliverpay(Map map)throws Exception;


	/**
	 * @author：QJY
	 * @date：
	 * @Method Description：批量删除预售商品
	 */
	public boolean deletetorecycle(String chb_id, String state);
	

}

