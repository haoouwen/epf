/*
  
 
 * Package:com.rbt.dao
 * FileName: IIndex_historyDao.java 
 */
package com.rbt.dao;

import java.util.List;
import com.rbt.model.Index_history;

/**
 * @function 功能 记录已经索引过的信息dao层业务接口
 * @author  创建人LJQ
 * @date  创建日期 Fri Aug 12 10:12:10 CST 2014
 */

public interface IIndex_historyDao extends IGenericDao<Index_history,String>{
	
	/**
	 * @MethodDescribe 方法描述    用户的批量添加历史索引
	 * @author  创建人  LJQ
	 * @date  创建日期  Aug 12, 2014 1:47:47 PM
	 */
	public void insertIndex(final List list);
	
	/**
	 * @Method Description :  用户的批量删除历史
	 * @author : LJQ
	 * @date : Nov 28, 2014 12:50:40 PM
	 */
	public void deleteIndex_historyList(final List list);

}

