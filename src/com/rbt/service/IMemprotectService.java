/*
  
 
 * Package:com.rbt.servie
 * FileName: IMemprotectService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memprotect;
import com.rbt.model.Shopconfig;

/**
 * @function 功能 记录会员密保信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Thu Feb 21 09:21:06 CST 2014
 */

public interface IMemprotectService extends IGenericService<Memprotect,String>{
	public Memprotect getByCustID(String id);
}

