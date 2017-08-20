/*
  
 
 * Package:com.rbt.service
 * FileName: ICommparaService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Aboutus;
import com.rbt.model.Commpara;

/**
 * @function 功能  系统参数业务层接口
 * @author  创建人 HXK
 * @date  创建日期  July 6, 2014
 */
public interface ICommparaService extends IGenericService<Commpara,String> {

    /**
	 * @MethodDescribe 方法描述    对para_code类型进行分组用来创建动态的系统参数MAP
	 * @author  创建人  LJQ
	 * @date  创建日期  Aug 1, 2014 2:05:42 PM
	 */
     @SuppressWarnings("unchecked")
	public List getCommparaGroupList(Map map);
     /**
	 * @MethodDescribe 方法描述    建立参数表索引数据
	 * @author  创建人  LJQ
	 * @date  创建日期  Aug 26, 2014 2:53:13 PM
	 */
 	public List getCommparaIndexList(Map map);
 	/**
	 * @MethodDescribe 方法描述    前台相应的参数值
	 * @author  创建人  LJQ
	 * @date  创建日期  Aug 29, 2014 4:56:14 PM
	 */
 	public List getWebCommparaList(Map map);
	
	
	/**
	 * @author : LJQ
	 * @date : Apr 15, 2014 7:07:14 PM
	 * @Method Description :根据参数编码取出参数列表
	 */
	public List getCommparaList(String para_code);
	
	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过para_code 分组
	 */
	public List getListByGroup(Map map);
	
	/**
	 * @author:HXM
	 * @date:May 30, 201410:58:48 AM
	 * @param:
	 * @Description:获得分组以后的条数
	 */
	public int getGroupCount(Map map);
}
