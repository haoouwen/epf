/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: DirectladderService.java 
 */
package com.rbt.service.impl;


import com.rbt.dao.IDirectladderDao;
import com.rbt.model.Directladder;
import com.rbt.service.IDirectladderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 预售商品阶梯价格Service层业务接口实现
 * @author 创建人 HZX
 * @date 创建日期 Wed Jul 17 13:28:18 CST 2014
 */
@Service
public class DirectladderService extends GenericService<Directladder,String> implements IDirectladderService {
	
	IDirectladderDao directladderDao;

	@Autowired
	public DirectladderService(IDirectladderDao directladderDao) {
		super(directladderDao);
		this.directladderDao = directladderDao;
	}
	public Directladder getByDirectID(String direct_id){
		return  (Directladder) this.directladderDao.getByDirectID(direct_id);
	}
	public void deleteDirectID(String id) {
		this.directladderDao.deleteDirectID(id);
		
	}
}

