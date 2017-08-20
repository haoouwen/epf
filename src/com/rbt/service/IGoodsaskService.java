/*
  
 
 * Package:com.rbt.servie
 * FileName: IGoodsaskService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodsask;
import com.rbt.model.Member;

/**
 * @function 功能 记录商品咨询信息Service层业务接口实现类
 * @author  创建人 CYC
 * @date  创建日期 Fri Mar 23 11:24:44 CST 2014
 */

public interface IGoodsaskService extends IGenericService<Goodsask,String>{
	/**
	 * @author：XBY
	 * @date：Feb 11, 2014 4:17:40 PM
	 * @Method Description：替换
	 */
	public void replaceList(List goodsaskList,Member member);
}

