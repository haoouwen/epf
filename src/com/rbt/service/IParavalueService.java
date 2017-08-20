/*
  
 
 * Package:com.rbt.servie
 * FileName: IParavalueService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Memberuser;
import com.rbt.model.Paravalue;

/**
 * @function 功能 参数值信息Service层业务接口实现类
 * @author  创建人 LSQ
 * @date  创建日期 Wed Jan 16 16:29:11 CST 2014
 */

public interface IParavalueService extends IGenericService<Paravalue,String>{
	
	public List getParaValList(Map map);
}

