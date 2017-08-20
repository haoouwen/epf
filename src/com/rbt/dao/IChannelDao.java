/*
  
 
 * Package:com.rbt.dao
 * FileName: IChannelDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Channel;

/**
 * @function 功能 记录网站栏目信息dao层业务接口
 * @author  创建人HXK
 * @date  创建日期 Mon Aug 15 10:57:10 CST 2014
 */

public interface IChannelDao  extends IGenericDao<Channel,String> {
	/**
	 * @MethodDescribe 方法描述   根据模块类型返回相应的对象
	 * @author  创建人  LJQ
	 * @date  创建日期  Sep 17, 2014 3:49:12 PM
	 */
	@SuppressWarnings("unchecked")
	public Channel getChannelByType(String type);
	
	public List getDeleteList(Map map);
	
}

