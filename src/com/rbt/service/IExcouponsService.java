/*
 * Package:com.rbt.servie
 * FileName: IExcouponsService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Excoupons;

/**
 * @function 功能 优惠券兑换表Service层业务接口实现类
 * @author  创建人 XBY
 * @date  创建日期 Fri Oct 09 13:10:41 CST 2015
 */

public interface IExcouponsService extends IGenericService<Excoupons,String>{
	
	
	/**
	 * 重新下载
	 * @throws Exception
	 */
	public boolean exprotcouponExcel(List excouponsList, HttpServletResponse response) throws Exception;
}

