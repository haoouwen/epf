/*
 * Package:com.rbt.servie
 * FileName: IExredbagService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Exredbag;

/**
 * @function 功能 红包兑换表Service层业务接口实现类
 * @author  创建人 XBY
 * @date  创建日期 Fri Oct 09 13:33:32 CST 2015
 */

public interface IExredbagService extends IGenericService<Exredbag,String>{
	/**
	 * 重新下载
	 * @throws Exception
	 */
	public boolean exprotredbagExcel(List exredbagList, HttpServletResponse response) throws Exception;
}

