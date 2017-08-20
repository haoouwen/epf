/*
  
 
 * Package:com.rbt.servie
 * FileName: ICommutemplateService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Commutemplate;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 记录会员发送模板信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Sat Dec 29 13:20:17 CST 2014
 */

public interface ICommutemplateService extends IGenericService<Commutemplate,String>{
	public Commutemplate getByTempcode(String temp_code);
}

