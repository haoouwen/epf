/*
 
 * Package:com.rbt.dao.Impl
 * FileName: LogsDao.java
 */
package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;
import com.rbt.dao.ILogsDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Logs;
import org.springframework.stereotype.Repository;

/**
 * @function 功能 系统日志功能实现层
 * @author 创建人 LJQ
 * @date 创建日期 Jul 5, 2014 9:35:19 AM
 */

@Repository
public class LogsDao extends GenericDao<Logs,String> implements ILogsDao {

	public LogsDao() {
		super(Logs.class);
	}


	public void deleteAlllogs() {
		this.getSqlMapClientTemplate().delete("logs.deleteAll");
	}
}
