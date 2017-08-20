/*
  
 
 * Package:com.rbt.dao
 * FileName: IGoodsevalDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodseval;

/**
 * @function 功能 记录商品评价表信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Tue Jan 08 11:06:50 CST 2014
 */

public interface IGoodsevalDao extends IGenericDao<Goodseval,String>{
	
	public List getWebList(Map map);
	
	public int getWebCount(Map map);
	/**
	 * @Method Description :买家评价
	 * @author : HZX
	 * @date : Apr 2, 2014 10:25:55 AM
	 */
	@SuppressWarnings("unchecked")
	public void updatebuy(Goodseval goodseval);
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过goods_name 分组
	 */
	public List getListByGroup(Map map);
	/**
	 * @author:HXM
	 * @date:May 30, 201410:54:39 AM
	 * @param:
	 * @Description:查询获得分组总条数
	 */
	public int getGroupCount(Map map);
}

