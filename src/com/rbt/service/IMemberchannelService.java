/*
  
 
 * Package:com.rbt.servie
 * FileName: IMemberchannelService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memberchannel;
/**
 * @function 功能 记录会员企业站栏目信息Service层业务接口实现类
 * @author  创建人 CYC
 * @date  创建日期 Fri Aug 26 16:21:41 CST 2014
 */

public interface IMemberchannelService extends IGenericService<Memberchannel,String>{
	
	
	/**
	 * 方法描述：批量修改是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateisdis(List list);
	
	/**
	 * 方法描述：批量修改会员栏目
	 * @param pk
	 * @return java.util.Map
	 */
	public void updatechannel(String member_memberchannel_id,String member_sort,String member_name,String member_num) ;
}

