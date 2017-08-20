/*
  
 
 * Package:com.rbt.dao
 * FileName: IGenericDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

/**
 * @function 功能 泛型dao层通用接口
 * @author  创建人 HXK
 * @date  创建日期 2014-11-25
 */

public interface IGenericDao<T,PK> {
	
	public void insert(T t);
	
	public void insertcust(T t);
	
	public void update(T t);
	
	public int delete(PK id);
	
	public List<Map<String,String>> getList(Map<String,String> map);
	
	public int getCount(Map<String,String> map);
	
	public T get(PK id);
	

	
	/**
	 * @Method Description : 对刚插入的数据获得它的主键
	 * @author : LJQ
	 * @date : Dec 16, 2014 4:37:14 PM
	 */
	public String insertGetPk(T t);
	
	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 11:02:35 AM
	 * @Method Description :批量排序的公共方法
	 */
	public void updateSort(final List list);
	
	/**
	 * @author : LJQ
	 * @date : May 3, 2014 2:09:45 PM
	 * @Method Description :批量修改状态
	 */
	public void updateBatchState(final List list);
	
	public void deleteByOrderIds(String id);
}
