/*
  
 
 * Package:com.rbt.servie
 * FileName: IPrintstyleService.java 
 */
package com.rbt.service;

import com.rbt.model.Printstyle;

/**
 * @function 功能 记录打印样式信息Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Wed Feb 27 15:56:03 CST 2014
 */

public interface IPrintstyleService extends IGenericService<Printstyle,String>{
	
	/**
	 * 通过快递单模板代码获取快递单模板实体
	 * @return
	 */
	public Printstyle getTemplate_code(String template_code);
	
}

