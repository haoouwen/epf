/*
  
 
 * Package:com.rbt.dao
 * FileName: ICommparaDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;
import com.rbt.model.Commpara;
/**
 * @function 功能 系统参数dao层业务接口
 * @author  创建人 HXK
 * @date  创建日期  July 6, 2014
 */
public interface ICommparaDao extends IGenericDao<Commpara,String> {
	
	
	/**
	 * @MethodDescribe 方法描述   对para_code类型进行分组用来创建动态的系统参数MAP
	 * @author  创建人  LJQ
	 * @date  创建日期  Aug 1, 2014 2:03:04 PM
	 */
     @SuppressWarnings("unchecked")
	public List getCommparaGroupList(Map map);
	
     /**
 	 * @MethodDescribe 方法描述    找出参数表中不在索引记录表中的数据
 	 * @author  创建人  LJQ
 	 * @date  创建日期  Aug 26, 2014 2:34:18 PM
 	 */
 	public List getCommparaIndexList(Map map);
 	/**
	 * @MethodDescribe 方法描述    找出前台相应的参数表数据
	 * @author  创建人  LJQ
	 * @date  创建日期  Aug 29, 2014 4:58:52 PM
	 */
 	public List getWebCommparaList(Map map);
 	
 	/**
	 * @author:HXM
	 * @date:May 30, 20149:35:42 AM
	 * @param:
	 * @Description:分组查询通过para_code 分组
	 */
	public List getListByGroup(Map map);
	/**
	 * @author:HXM
	 * @date:May 30, 201410:54:39 AM
	 * @param:
	 * @Description:查询获得分组条数
	 */
	public int getGroupCount(Map map);

}
