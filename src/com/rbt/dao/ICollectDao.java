/*
  
 
 * Package:com.rbt.dao
 * FileName: ICollectDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Collect;

/**
 * @function 功能 记录会员商机收藏信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Mon Jan 14 10:40:08 CST 2014
 */

public interface ICollectDao extends IGenericDao<Collect,String>{
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过cust_id 分组
	 */
	public List getListByGroup(Map map);
	/**
	 * @author:HXM
	 * @date:May 30, 201410:54:39 AM
	 * @param:
	 * @Description:查询获得分组总条数
	 */
	public int getGroupCount(Map map);
	public void deleteByGoodsId(String goods_id);
}

