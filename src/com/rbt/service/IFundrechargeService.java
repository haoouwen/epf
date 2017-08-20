/*
  
 
 * Package:com.rbt.servie
 * FileName: IFundrechargeService.java 
 */
package com.rbt.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import jxl.write.WriteException;

import com.rbt.model.Fundrecharge;

/**
 * @function 功能 会员资金充值记录Service层业务接口实现类
 * @author  创建人 CYC
 * @date  创建日期 Tue Jul 12 13:10:48 CST 2014
 */

public interface IFundrechargeService extends IGenericService<Fundrecharge,String>{
	public Fundrecharge getByOrderId(String order_id)throws Exception;
	public Fundrecharge getByTrxid(String trxid)throws Exception;
	/**
	 * 充值记录导出
	 * @throws IOException 
	 * @throws WriteException 
	 */
	public boolean fundchargeExport( HttpServletResponse response) throws IOException, WriteException;
}

