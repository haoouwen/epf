/*
  
 
 * Package:com.rbt.servie
 * FileName: ISendshipmodeService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Sendmode;
import com.rbt.model.Sendshipmode;

/**
 * @function 功能 快递方式Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Tue May 06 11:17:02 CST 2014
 */

public interface ISendshipmodeService extends IGenericService<Sendshipmode,String>{
	public Sendshipmode getByEnname(String en_name);
}

