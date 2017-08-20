/*
  
 
 * Package:com.rbt.dao
 * FileName: IPrintstyleDao.java 
 */
package com.rbt.dao;

import com.rbt.model.Printstyle;

/**
 * @function 功能 记录打印样式信息dao层业务接口
 * @author  创建人HZX
 * @date  创建日期 Wed Feb 27 15:56:03 CST 2014
 */

public interface IPrintstyleDao extends IGenericDao<Printstyle,String>{
	
	/**
	 * 通过快递单模板代码获取快递单模板实体
	 * @return
	 */
	public Printstyle getTemplate_code(String template_code);
	
}

