/*
  
 
 * Package:com.rbt.servie
 * FileName: IPagetipService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Pagetip;

/**
 * @function 功能 记录页面显示管理信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Tue Jan 29 13:10:46 CST 2014
 */

public interface IPagetipService extends IGenericService<Pagetip,String>{
	/**
	 * 方法描述：取出全部
	 * 
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getAll();
}

