/*
  
 
 * Package:com.rbt.servie
 * FileName: IAttrvalueService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Attrvalue;

/**
 * @function 功能 记录分类属性的值Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Thu Aug 23 15:57:02 CST 2014
 */

public interface IAttrvalueService extends IGenericService<Attrvalue,String>{
	public void deleteByattrid(String id);
}

