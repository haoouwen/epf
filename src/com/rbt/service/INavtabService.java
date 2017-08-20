/*
 
 * Package:com.rbt.servie
 * FileName: INavtabService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Navtab;

/**
 * @function 功能 导航标签信息Service层业务接口实现类
 * @author  创建人 ZMS
 * @date  创建日期 Wed Aug 12 20:56:05 CST 2015
 */

public interface INavtabService extends IGenericService<Navtab,String>{
	
	/**
	 * @author : ZMS
	 * @date : Feb 11, 2014 10:47:43 AM
	 * @Method Description :找出标签对应的商品数量
	 */
	List getNavtabList(List navtabList);
	
	public Navtab getByTaxNumber(String tax_number);
	
	
}

