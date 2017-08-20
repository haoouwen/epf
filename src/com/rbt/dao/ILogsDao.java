/*
  
 
 * Package:com.rbt.dao
 * FileName: ILogsDao.java
 */
package com.rbt.dao;

import com.rbt.model.Logs;

/**
 * @function 功能 系统日志功能实现接口层
 * @author 创建人 LJQ
 * @date 创建日期 Jul 5, 2014 9:35:51 AM
 */
public interface ILogsDao extends IGenericDao<Logs,String>{

	/**
	 * @Method Description :清空logs记录表
	 * @author : LJQ
	 * @date : Nov 9, 2014 1:30:03 PM
	 */
	public void deleteAlllogs();
}
