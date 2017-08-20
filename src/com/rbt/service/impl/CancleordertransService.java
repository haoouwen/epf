/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: CancleordertransService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.ICancleordertransDao;
import com.rbt.model.Cancleordertrans;
import com.rbt.service.ICancleordertransService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 取消申请进度Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Thu Sep 25 10:55:07 CST 2014
 */
@Service
public class CancleordertransService extends GenericService<Cancleordertrans,String> implements ICancleordertransService {
	
	ICancleordertransDao cancleordertransDao;

	@Autowired
	public CancleordertransService(ICancleordertransDao cancleordertransDao) {
		super(cancleordertransDao);
		this.cancleordertransDao = cancleordertransDao;
	}
	
}

