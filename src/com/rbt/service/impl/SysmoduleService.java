/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SysmoduleService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.dao.ISysmoduleDao;
import com.rbt.model.Sysmodule;
import com.rbt.service.ISysmoduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 系统模块表Service层业务接口实现
 * @author 创建人 LJQ
 * @date 创建日期 Fri Jan 13 12:48:48 CST 2014
 */
@Service
public class SysmoduleService extends GenericService<Sysmodule,String> implements ISysmoduleService {
	
	ISysmoduleDao sysmoduleDao;

	@Autowired
	public SysmoduleService(ISysmoduleDao sysmoduleDao) {
		super(sysmoduleDao);
		this.sysmoduleDao = sysmoduleDao;
	}

	/**
	 * @author : LJQ
	 * @date : Apr 16, 2014 9:13:46 PM
	 * @Method Description : 获取模块数据
	 */
	public List getModList(){
		Map pageMap = new HashMap();
		pageMap.put("state_code", "0");
		List list = this.sysmoduleDao.getList(pageMap);
		return list;
	}
}

