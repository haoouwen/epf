/*
  
 
 * Package:com.rbt.servie
 * FileName: IShiptemplateService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Shiptemplate;

/**
 * @function 功能 记录运费模板信息Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Thu May 24 15:00:05 CST 2014
 */

public interface IShiptemplateService extends IGenericService<Shiptemplate,String>{
	
	/**
	 * @author : LJQ
	 * @date : May 30, 2014 10:40:21 AM
	 * @Method Description :插入运费模板表和区域设置表
	 */
	public void insertShipMode(Shiptemplate t,List objList);
	
	/**
	 * @author : LJQ
	 * @date : Jun 6, 2014 1:46:22 PM
	 * @Method Description :更新运费模板表和区域设置表
	 */
	public void updateShipMode(Shiptemplate t,List objList);
	/**
	 * @author : LJQ
	 * @date : May 28, 2014 9:29:58 AM
	 * @Method Description :循环遍历
	 */
	public List getChinaListByidStr(String upAreaId);
}

