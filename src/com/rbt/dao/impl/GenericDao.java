/*
 
 * Package:com.rbt.dao.impl
 * FileName: GenericDao.java 
 */
package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IGenericDao;

/**
 * @function 功能 泛型dao层通用接口实现类
 * @author  创建人 HXK
 * @date  创建日期 2014-11-25
 */

@Repository
public class GenericDao<T,PK> extends BaseDao implements IGenericDao<T,PK> {

	private Class<T> persistentClass;

	/**
	 * Constructor that takes in a class to see which type of entity to persist.
	 * Use this constructor when subclassing.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericDao(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 10:59:20 AM
	 * @Method Description :获取模块名称
	 */
	public String getModelName(){
		return persistentClass.getSimpleName().toLowerCase();
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 10:58:57 AM
	 * @Method Description :删除信息
	 */
	public int delete(PK id) {
		return this.getSqlMapClientTemplate().delete(getModelName()+".delete", id);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 10:58:48 AM
	 * @Method Description :获取对象
	 */
	public T get(PK id) {
		return (T)this.getSqlMapClientTemplate().queryForObject(getModelName()+".getByPk", id);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 10:58:41 AM
	 * @Method Description :获取条数
	 */
	public int getCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				getModelName()+".getCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 10:58:34 AM
	 * @Method Description :读取列表
	 */
	public List getList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(getModelName()+".getList",map);
	}
	
	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 10:58:27 AM
	 * @Method Description :新增方法
	 */
	public void insert(T t) {
		this.getSqlMapClientTemplate().insert(getModelName()+".insert", t);

	}
	/**
	 * @author : LJQ
	 * @date : Apr 19, 2016 10:58:27 AM
	 * @Method Description :新增加券方法
	 */
	public void insertcust(T t) {
		this.getSqlMapClientTemplate().insert(getModelName()+".insertcust", t);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 10:58:19 AM
	 * @Method Description :更新方法
	 */
	public void update(T t) {
		this.getSqlMapClientTemplate().update(getModelName()+".update", t);
	}

	/**
	 * @Method Description : 对刚插入的数据获得它的主键
	 * @author : LJQ
	 * @date : Dec 16, 2014 4:37:14 PM
	 */
	public String insertGetPk(T t){
		  return (String) this.getSqlMapClientTemplate().insert(getModelName()+".insertGetPk",t);
	}	
	
	/**
	 * @author : LJQ
	 * @date : Apr 19, 2014 10:55:48 AM
	 * @Method Description :批量排序的公共方法
	 */
	public void updateSort(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update(getModelName()+".updateSort", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}	
	
	
	/**
	 * @author : LJQ
	 * @date : May 3, 2014 2:09:45 PM
	 * @Method Description :批量修改状态
	 */
	public void updateBatchState(final List list){
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update(getModelName()+".updateState", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	public void deleteByOrderIds(String id) {
		 this.getSqlMapClientTemplate().delete(getModelName()+".deleteByOrderId", id);
	}
}
