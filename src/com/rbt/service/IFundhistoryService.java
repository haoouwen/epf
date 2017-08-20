/*
  
 
 * Package:com.rbt.servie
 * FileName: IFundhistoryService.java 
 */
package com.rbt.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.rbt.model.Fundhistory;

/**
 * @function 功能 会员资金流Service层业务接口实现类
 * @author  创建人 CYC
 * @date  创建日期 Wed Jul 13 10:06:11 CST 2014
 */

public interface IFundhistoryService extends IGenericService<Fundhistory,String>{
	/**
	 * @Method Description :导出余额流
	 * @author : HZX
	 * @date : Mar 14, 2014 2:50:19 PM
	 */
	void exportExcel(List fundhistoryList, HttpServletResponse response) throws Exception;
}

