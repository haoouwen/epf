/*
  
 
 * Package:com.rbt.servie
 * FileName: IVisitnumService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Visitnum;

/**
 * @function 功能 记录日访问数Service层业务接口实现类
 * @author  创建人 LHY
 * @date  创建日期 Thu Oct 11 09:56:36 CST 2014
 */

public interface IVisitnumService extends IGenericService<Visitnum,String>{
	public int getSum(Map<String,String> map);
 
	public double calculate(int tradenum, int visitsize);
	
}

