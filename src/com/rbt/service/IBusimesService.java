/*
  
 
 * Package:com.rbt.servie
 * FileName: IBusimesService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Busimes;
import com.rbt.model.Member;

/**
 * @function 功能 记录商家留言信息Service层业务接口实现类
 * @author  创建人 CYC
 * @date  创建日期 Fri Mar 30 12:29:33 CST 2014
 */

public interface IBusimesService extends IGenericService<Busimes,String>{
	/**
	 * @author：XBY
	 * @date：Feb 12, 2014 10:06:49 AM
	 * @Method Description：把回复者ID替换回复者商铺名称
	 */
	public List replaceList(List busimesList,Member member);
}

