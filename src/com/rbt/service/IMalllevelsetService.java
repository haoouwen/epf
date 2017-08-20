/*
  
 
 * Package:com.rbt.servie
 * FileName: IMalllevelsetService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.rbt.model.Malllevelset;

/**
 * @function 功能 记录商城会员等级信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Fri Dec 28 14:46:47 CST 2014
 */

public interface IMalllevelsetService extends IGenericService<Malllevelset,String>{
	public List getMemLevelList(String mem_type);
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 5:14:31 PM
	 * @Method Description :获取商城B2C菜单
	 */
	public List roleTree(HttpSession session);
}

