/*
  
 
 * Package:com.rbt.servie
 * FileName: IGroupgoodsService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Groupgoods;

/**
 * @function 功能 团购商品信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Thu Mar 28 14:55:26 CST 2014
 */

public interface IGroupgoodsService extends IGenericService<Groupgoods,String>{
	/**
	 * @Method Description :新增一口价
	 * @author : HZX
	 * @date : Apr 16, 2014 1:30:17 PM
	 */
	public void insertshot(String id,String shot);
	
	
	/**
	 * @Method Description :更新一口价
	 * @author : HZX
	 * @date : Apr 16, 2014 1:30:17 PM
	 */
	public void updateshot(String id,String shot);
	
	
	/**
	 * @Method Description :新增阶梯价格
	 * @author : HZX
	 * @date : Apr 16, 2014 10:34:14 AM
	 */
	public void insertladprice(String id,String lownums,String ladprices);
	
	
	
	/**
	 * @Method Description : 新增团购数据
	 * @author : HZX
	 * @date : Apr 16, 2014 8:33:11 PM
	 */
	public String  insertGroupGoods(Groupgoods groupgoods,String shot,String lownums,String ladprices);
	
	/**
	 * @Method Description :更新阶梯价格
	 * @author : HZX
	 * @date : Apr 16, 2014 8:47:33 PM
	 */
	public void updateGroupladder(String group_type,String id,String shot,String lownums,String ladprices);
	
	/**
	 * @author：XBY
	 * @date：Feb 17, 2014 10:43:56 AM
	 * @Method Description：团购价格详细
	 */
	public Map detail(String trade_id)throws Exception;
	
	/**
	 * @param price 
	 * @author：XBY
	 * @date：Feb 18, 2014 10:46:58 AM
	 * @Method Description：团购信息
	 */
	public Map groupList(String cat_attr,String en_name,String sele_area_id,String searchtype,String searchname, char price);
	
	/**
	 * @author:HXM
	 * @date:May 9, 20149:30:42 AM
	 * @param:
	 * @Description:为集合添加一个标识来判断，团购是在开始前后，或是进行
	 */
	public List changList(List list)throws Exception;
	
	/**
	 * @author:HXM
	 * @date:May 9, 20142:33:22 PM
	 * @param:
	 * @Description:修改资金冻结状态
	 */
	public void updateApply(Map map);


	public List getCatList(Map catMap);
}

