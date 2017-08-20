/*
  
 
 * Package:com.rbt.servie
 * FileName: IAuditmodelService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Auditmodel;

/**
 * @function 功能 审核模型信息Service层业务接口实现类
 * @author  创建人 HXK
 * @date  创建日期 Mon Aug 06 15:40:22 CST 2014
 */

public interface IAuditmodelService extends IGenericService<Auditmodel,String>{
	public List getModelList(Map map);
	public int getModelCount(Map map);
	/**
	 * 获取某一个用户需要审核的模块信息
	 * @param map
	 * @return
	 */
	public List getAuditList(Map map);
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 1:02:20 PM
	 * @Method Description：新增方法
	 */
	public void inserinfo(String sel_mem_str,List sel_member_list,Auditmodel auditmodel);
	/**
	 * 方法描述：插入模型的时候，判断该审核模型是否已经存在数据中！，如果存在只能修改，不能新增重复的审核模块
	 * 
	 * @return
	 * @throws Exception
	 */
    public boolean existsAuditModelType(String modeltype);
    /**
	 * 方法描述：构造LIST用于信息的回选
	 * @return
	 * @throws Exception
	 */
	public void sel_member_list(String user_ids,List sel_member_list);
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 1:17:12 PM
	 * @Method Description：删除方法
	 */
	public boolean deleteinfo(Auditmodel auditmodel);
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 1:27:47 PM
	 * @Method Description：构造一个List
	 */
	public List auditList(List auditmodelList,List auditList,List audittypeList);
	/**
	 * @author：XBY
	 * @date：Feb 10, 2014 1:39:29 PM
	 * @Method Description：修改判断
	 */
	public void checkView(List aList,String mem_count,String sel_mem_str,List sel_member_list);
}

