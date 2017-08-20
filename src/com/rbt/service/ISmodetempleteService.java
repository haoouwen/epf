/*
  
 
 * Package:com.rbt.servie
 * FileName: ISmodetempleteService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Emailtemplate;
import com.rbt.model.Goods;
import com.rbt.model.Smodetemplete;

/**
 * @function 功能 记录区域设置信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Thu Jan 10 10:52:23 CST 2014
 */

public interface ISmodetempleteService extends IGenericService<Smodetemplete,String>{
	/**
	 * 方法描述：根据记录会员邮件发送模板的编号找出记录会员邮件发送模板信息
	 * 
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public Smodetemplete getSmodetempleteBySmodeId(String smode_id);
}

