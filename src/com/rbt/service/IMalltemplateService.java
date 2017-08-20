/*
  
 
 * Package:com.rbt.servie
 * FileName: IMalltemplateService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Malltemplate;

/**
 * @function 功能 商城模板信息Service层业务接口实现类
 * @author  创建人 LSQ
 * @date  创建日期 Fri Dec 28 10:31:27 CST 2014
 */

public interface IMalltemplateService extends IGenericService<Malltemplate,String>{
	public void updateisenable();
}

