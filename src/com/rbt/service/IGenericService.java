/*
  
 
 * Package:com.rbt.servie
 * FileName: IGenericService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

/**
 * @function 功能 泛型业务通用接口
 * @author  创建人 HXK
 * @date  创建日期 2014-11-25
 */

public interface IGenericService<T,PK> {
	
	/**
	 * 新增记录
	 */
	public void insert(T t);
	/**
	 * 新增券记录
	 */
	public void insertcust(T t);
	
	/**
	 * 修改记录
	 */
	public void update(T t);
	
	/**
	 * 按主键删除
	 */
	public boolean delete(PK id);
	
	/**
	 * 查询记录
	 */
	public List<Map<String,String>> getList(Map<String,String> map);
	
	/**
	 * 查询记录数量
	 */
	public int getCount(Map<String,String> map);
	
	/**
     *按主键查询,取一条记录
     */
	public T get(PK id);
	
	/**
     *对刚插入的数据获得它的主键
     */
	public String insertGetPk(T t);
	
	/**
     *批量排序
     */
	public boolean updateSort(String field_id,String field_sort,String ids,String vals);

	/**
     *删除相应的模块同时删除相应的属性数据
     */
    public void deleteByModel(String id);
    /**
	 * @author : LJQ
	 * @date : Apr 18, 2014 10:18:30 AM
	 * @Method Description : 增加FCK内容
	 */
    public void insertFckContent(String cust_id,String table_name,String table_id,String randon_num);
    
    /**
	 * @author : LJQ
	 * @date : Apr 18, 2014 1:53:42 PM
	 * @Method Description :数据操作流
	 */
    public void insertAudit(String table_name,String info_id,String info_state,String no_reason,String session_cust_id,
    		String session_user_id,String session_user_name);

    /**
	 * @author : LJQ
	 * @date : May 3, 2014 2:29:26 PM
	 * @Method Description :批量更新状态
	 */
    public boolean updateBatchState(String ids,String state,String field_id,String field_state);
    /**
     *删除订单信息 通过订单ID
     */
    public void deleteByOrderIds(String id);
    
}
