/*
 * Package:com.rbt.servie
 * FileName: IFepbillService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Fepbill;

/**
 * @function 功能 代购汇账单Service层业务接口实现类
 * @author  创建人 HZX
 * @date  创建日期 Wed Sep 23 13:22:25 CST 2015
 */

public interface IFepbillService extends IGenericService<Fepbill,String>{
	/**
	 * 重新下载
	 * @throws Exception
	 */
	public boolean exprotExcel(List exList, HttpServletResponse response) throws Exception;
	
}

