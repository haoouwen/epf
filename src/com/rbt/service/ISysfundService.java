/*
  
 
 * Package:com.rbt.servie
 * FileName: ISysfundService.java 
 */
package com.rbt.service;

import com.rbt.model.Sysfund;

/**
 * @function 功能 平台总资金Service层业务接口实现类
 * @author  创建人 HXM
 * @date  创建日期 Sun Jul 13 17:17:59 CST 2014
 */

public interface ISysfundService extends IGenericService<Sysfund,String>{
	/**
	 * @author:HXM
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:freeze_num:冻结/解冻金额， flag_int：标记冻结（0）/解冻（1）
	 * @Description:出金和入金操作（可用资金和总资金的操作）（单纯的对memberfunt进行资金操作不涉及记录表），返回可用资金
	 */
	public double freezeNum(double freeze_num,int flag_int);
	
	/**
	 * @author:HXM
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:outorin_num:出金/入金金额， flag_int：标记出金（0）/入金（1）
	 * @Description:出金和入金操作（单纯的对memberfunt进行资金操作不涉及记录表），返回可用资金
	 */
	public double outAndInNum(double outorin_num,int flag_int);
	/**
	 * @author:HXM
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:addNum:添加的金额
	 * @Description:用于在线支付订单对平台账号的操作，返回可用余额（总资金和冻结资金增加，可用资金不变）
	 */
	public double addNumByOnLion(double addNum);
	/**
	 * @author:QJY
	 * @date:Jul 8, 2015 1:50:35 PM
	 * @param:reduceNum:减少的金额
	 * @Description:用于在线退款订单对平台账号的操作，返回可用余额（总资金和冻结资金减少，可用资金不变）
	 */
	public double reduceNumByrefund(double reduceNum);
}

