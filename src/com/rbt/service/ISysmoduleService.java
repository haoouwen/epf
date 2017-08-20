/*
  
 
 * Package:com.rbt.servie
 * FileName: ISysmoduleService.java 
 */
package com.rbt.service;

import java.util.List;
import com.rbt.model.Sysmodule;

/**
 * @function 功能 系统模块表Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Fri Jan 13 12:48:48 CST 2014
 */

public interface ISysmoduleService extends IGenericService<Sysmodule,String>{
	

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 9:13:46 PM
	 * @Method Description : 获取模块数据
	 */
	public List getModList();
}

