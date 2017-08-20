/*
  
 
 * Package:com.rbt.servie
 * FileName: IRateService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Rate;

/**
 * @function 功能 汇率信息Service层业务接口实现类
 * @author  创建人 LSQ
 * @date  创建日期 Mon Jan 21 12:46:55 CST 2014
 */

public interface IRateService extends IGenericService<Rate,String>{
	public void updateendefault();
	/**
	 * 方法描述：取出全部
	 * 
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getAll();
}

