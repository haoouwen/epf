/*
 
 * Package:com.rbt.dao.impl
 * FileName: DirectsellDao.java 
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
import com.rbt.dao.IDirectsellDao;
import com.rbt.model.Directsell;

/**
 * @function 功能  预售商品dao层业务接口实现类
 * @author 创建人 HZX
 * @date 创建日期 Wed Jul 17 13:27:41 CST 2014
 */
@Repository
public class DirectsellDao extends GenericDao<Directsell,String> implements IDirectsellDao {
	
	public DirectsellDao() {
		super(Directsell.class);
	}
	
	/**
	 *@author :HXM
	 *@date Dec 10, 2014 9:37:44 AM
	 *@Method description:修改库存 
	 */
	public void updateStock(Map map){
		this.getSqlMapClientTemplate().update("directsell.updateStock", map);
	}
	
	/**
	 *@author :CYC
	 *@date Dec 10, 2014 9:37:44 AM
	 *@Method description:获取信息提醒列表 
	 */
	public List getdeliverpay(Map map){
		return this.getSqlMapClientTemplate().queryForList("directsell.getdeliverpayList",map);
	}
	
	/**
	 * QJY
	 */
	public void deletetorecycle(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("directsell.updateisdel", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
}

