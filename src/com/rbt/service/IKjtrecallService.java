/*
 * Package:com.rbt.servie
 * FileName: IKjtrecallService.java 
 */
package com.rbt.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.write.WriteException;

import com.rbt.model.Kjtrecall;

/**
 * @function 功能 跨境通回调表Service层业务接口实现类
 * @author  创建人 CYC
 * @date  创建日期 Fri Sep 18 16:21:49 CST 2015
 */

public interface IKjtrecallService extends IGenericService<Kjtrecall,String>{
	public boolean exprotExcel(List exList, HttpServletResponse response) throws IOException, WriteException;
	
	/**
	 * @Method Description :通过订单ID查找海关回传对象值
	 * @author: 胡惜坤
	 * @date : Feb 24, 2016 11:33:23 AM
	 * @param 
	 * @return return_type
	 */
	public Kjtrecall getByOrderId(String order_id);
	
	 public void updatekjtpur(Map map);
}

