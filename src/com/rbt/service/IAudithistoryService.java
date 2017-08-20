/*
  
 
 * Package:com.rbt.servie
 * FileName: IAudithistoryService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Audithistory;

/**
 * @function 功能 记录模块审核历史Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Tue Nov 15 10:35:16 CST 2014
 */

public interface IAudithistoryService extends IGenericService<Audithistory,String>{

	public List getAuditList(Map map);
}

