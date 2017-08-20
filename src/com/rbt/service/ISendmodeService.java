/*
  
 
 * Package:com.rbt.servie
 * FileName: ISendmodeService.java 
 */
package com.rbt.service;

import com.rbt.model.Sendmode;

/**
 * @function 功能 配送方式表Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Fri Mar 23 09:55:49 CST 2014
 */

public interface ISendmodeService extends IGenericService<Sendmode,String>{
	public Sendmode getByEnname(String en_name);
}

