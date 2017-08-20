/*
  
 
 * Package:com.rbt.servie
 * FileName: ISelfsepcnameService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Selfspecname;

/**
 * @function 功能 自定义规格名称Service层业务接口实现类
 * @author  创建人 LJQ
 * @date  创建日期 Wed Jan 30 15:32:16 CST 2014
 */

public interface ISelfspecnameService extends IGenericService<Selfspecname,String>{
	public void deleteByGoodsid(String goods_id);
}

