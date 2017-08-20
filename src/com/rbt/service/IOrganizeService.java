/*
  
 
 * Package:com.rbt.servie
 * FileName: IOrganizeService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Organize;

/**
 * @function 功能 记录组织部门Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Mon Nov 07 13:37:36 CST 2014
 */

public interface IOrganizeService extends IGenericService<Organize,String>{
	/**
	 * 获取所有一级代理的系统用户
	 * @param map
	 * @return
	 */
	public List getSysList(Map map);
	/**
	 * 获取管理sysuser的总数
	 * @param map
	 * @return
	 */
	public int getCounts(Map map);
	/**
	 * @author :LSQ
	 * @date : Feb 17, 2014 3:04:49 PM
	 * @Method Description :获取所有所属部门
	 */
	@SuppressWarnings("unchecked")
	public List getAll();
	
	 public List getDeleteList(Map map);
	 /**
	 * @author : HZX
	 * @date : Feb 12, 2014 11:25:32 AM
	 * @Method Description :递归删除
	 */
	public boolean dealDelete(String id);
	/**
	 * @author : HZX
	 * @param up_org_id 
	 * @date : Feb 12, 2014 12:55:46 PM
	 * @Method Description :获取要打印的html字符串
	 */
	public StringBuffer getHtmlStr(List list, String up_org_id);
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 1:04:20 PM
	 * @Method Description :获取要打印的查看页面html字符串
	 */
	public StringBuffer getViewHtmlStr(List list, String up_org_id);
	/**
	 * @author : HZX
	 * @date : Feb 12, 2014 1:15:37 PM
	 * @Method Description :删除当前部门，和所属子部门
	 */
	public void recuDelete(String id);
}

