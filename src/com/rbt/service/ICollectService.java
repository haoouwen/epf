/*
  
 
 * Package:com.rbt.servie
 * FileName: ICollectService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Collect;

/**
 * @function 功能 记录会员商机收藏信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Mon Jan 14 10:40:08 CST 2014
 */

public interface ICollectService extends IGenericService<Collect,String>{
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过goods_name分组
	 */
	public List getListByGroup(Map map);
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:58:48 AM
	 * @param:
	 * @Description:获得分组以后的总条数
	 */
	public int getGroupCount(Map map);
}

