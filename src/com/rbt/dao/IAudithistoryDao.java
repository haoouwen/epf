/*
  
 
 * Package:com.rbt.dao
 * FileName: IAudithistoryDao.java 
 */
package com.rbt.dao;
import java.util.List;
import java.util.Map;

import com.rbt.model.Audithistory;

/**
 * @function 功能 记录模块审核历史dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Tue Nov 15 10:35:16 CST 2014
 */

public interface IAudithistoryDao  extends IGenericDao<Audithistory,String>{
	
	public List getAuditList(Map map);
}

