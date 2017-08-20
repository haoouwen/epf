/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: ParavalueService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IParavalueDao;
import com.rbt.model.Memberuser;
import com.rbt.model.Paravalue;
import com.rbt.service.IParavalueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 参数值信息Service层业务接口实现
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 16 16:29:11 CST 2014
 */
@Service
public class ParavalueService extends GenericService<Paravalue,String> implements IParavalueService {
	
	IParavalueDao paravalueDao;

	@Autowired
	public ParavalueService(IParavalueDao paravalueDao) {
		super(paravalueDao);
		this.paravalueDao = paravalueDao;
	}
	public List getParaValList(Map map){
		return this.paravalueDao.getParaValList(map);
	}
}

