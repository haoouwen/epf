/*
  
 
 * Package:com.rbt.dao
 * FileName: IAuditmodelDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Auditmodel;

/**
 * @function 功能 审核模型信息dao层业务接口
 * @author  创建人HXK
 * @date  创建日期 Mon Aug 06 15:40:22 CST 2014
 */

public interface IAuditmodelDao extends IGenericDao<Auditmodel,String>{
	
	public List getModelList(Map map);
	public int getModelCount(Map map);
	/**
	 * 获取某一个用户需要审核的模块信息
	 * @param map
	 * @return
	 */
	public List getAuditList(Map map);
}

