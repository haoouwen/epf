/*
  
 
 * Package:com.rbt.servie
 * FileName: IInterruleService.java 
 */
package com.rbt.service;

import java.util.List;

import com.rbt.model.Interrule;

/**
 * @function 功能 积分规则表Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Thu Nov 10 14:26:30 CST 2014
 */

public interface IInterruleService extends IGenericService<Interrule,String>{
	public void updateInterruleList(final List list);
	
	/**
	 * 方法描述：修改积分规则表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List updateScore(String rule_id,String rule_num);
}

