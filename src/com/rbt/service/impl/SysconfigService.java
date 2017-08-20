/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SysconfigService.java 
 */
package com.rbt.service.impl;

import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.common.util.DbUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.dao.ISysconfigDao;
import com.rbt.model.Sysconfig;
import com.rbt.service.ISysconfigService;

/**
 * @function 功能 系统变量设置Service层接口实现
 * @author 创建人 QJY
 * @date 创建日期 Jul 6, 2014 11:17:05 AM
 */
@Service
public class SysconfigService extends GenericService<Sysconfig, String>
		implements ISysconfigService {

	ISysconfigDao sysconfigDao;

	@Autowired
	public SysconfigService(ISysconfigDao sysconfigDao) {
		super(sysconfigDao);
		this.sysconfigDao = sysconfigDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.ISysconfigService#updateSysconfig(java.util.List)
	 */
	public void updateSysconfigBatch(List list) {
		this.sysconfigDao.updateSysconfigBatch(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.ISysconfigService#getWebname(java.lang.String)
	 */
	public Sysconfig getWebname(String var_value) {
		return this.sysconfigDao.getWebname(var_value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.ISysconfigService#getAll()
	 */
	public List getAll() {
		return this.sysconfigDao.getAll();
	}

	public void updateSys(Sysconfig sysconfig) {
		this.sysconfigDao.updateSys(sysconfig);
		
	}
	public void updateByvarname(HashMap map) {
		this.sysconfigDao.updateByvarname(map);
	}


	public void updateParma(Sysconfig sysconfig) {
		this.sysconfigDao.updateParma(sysconfig);
	}

	


}
