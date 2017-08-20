/*
 
 * Package:com.rbt.dao.impl
 * FileName: ChannelDao.java 
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
import com.rbt.dao.IChannelDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Channel;

/**
 * @function 功能  记录网站栏目信息dao层业务接口实现类
 * @author 创建人 HXK
 * @date 创建日期 Mon Aug 15 10:57:10 CST 2014
 */
@Repository
public class ChannelDao extends GenericDao<Channel,String> implements IChannelDao {

	public ChannelDao() {
		super(Channel.class);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.IChannelDao#getChannelByType(java.lang.String)
	 */
	public Channel getChannelByType(String type) {
		return (Channel) this.getSqlMapClientTemplate().queryForObject(
				"channel.getByType", type);
	}
	
	//拼接删除串
	public List getDeleteList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("channel.getDeleteList",map);
	}

}

