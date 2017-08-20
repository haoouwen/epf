/*
 
 * Package:com.rbt.dao
 * FileName: ITaxrateDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Taxrate;

/**
 * @function 功能 税率信息dao层业务接口
 * @author  创建人ZMS
 * @date  创建日期 Tue Aug 18 16:12:24 CST 2015
 */

public interface ITaxrateDao extends IGenericDao<Taxrate,String>{
	
	/**
	 * @MethodDescribe 方法描述   获取特殊tax_id集合
	 * @date  创建日期  2015 
	 */
	public List getDeleteList(Map map);
	
	/**
	 * @MethodDescribe 方法描述 取出所有地区
	 * @date  创建日期  2015
	 */
    public List getAll();
}

