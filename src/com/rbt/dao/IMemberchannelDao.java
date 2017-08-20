/*
  
 
 * Package:com.rbt.dao
 * FileName: IMemberchannelDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memberchannel;

/**
 * @function 功能 记录会员企业站栏目信息dao层业务接口
 * @author  创建人CYC
 * @date  创建日期 Fri Aug 26 16:21:41 CST 2014
 */

public interface IMemberchannelDao extends IGenericDao<Memberchannel,String>{

	/**
	 * 方法描述：批量修改是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateState(List lists);
	
	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updatechannel(List lists);
}

