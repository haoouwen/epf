/*
 
 * Package:com.rbt.dao.impl
 * FileName: MemberchannelDao.java 
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
import com.rbt.dao.IMemberchannelDao;
import com.rbt.model.Memberchannel;

/**
 * @function 功能  记录会员企业站栏目信息dao层业务接口实现类
 * @author 创建人 CYC
 * @date 创建日期 Fri Aug 26 16:21:41 CST 2014
 */
@Repository
public class MemberchannelDao extends GenericDao<Memberchannel,String> implements IMemberchannelDao {


	public MemberchannelDao() {
		super(Memberchannel.class);
	}

	/**
	 * 方法描述：批量修改是否显示
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("memberchannel.updateisdis", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
	/**
	 * 方法描述：批量导航排序
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updatechannel(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("memberchannel.updatechannel", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}
}

