/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: VisitnumService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IVisitnumDao;
import com.rbt.model.Visitnum;
import com.rbt.service.IVisitnumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 记录日访问数Service层业务接口实现
 * @author 创建人 LHY
 * @date 创建日期 Thu Oct 11 09:56:36 CST 2014
 */
@Service
public class VisitnumService extends GenericService<Visitnum,String> implements IVisitnumService {
	 
	IVisitnumDao visitnumDao;

	@Autowired
	public VisitnumService(IVisitnumDao visitnumDao) {
		super(visitnumDao);
		this.visitnumDao = visitnumDao;
	}

	public int getSum(Map<String, String> map) {
		
		return this.visitnumDao.getSum(map);
	}
	
	/**
	 * 计算转化率
	 * @param tradenum
	 * @param visitsize
	 * @return
	 */
	public double calculate(int tradenum,int visitsize){
		if(visitsize!=0){
			double trade=(double)tradenum/visitsize;
			return trade;
		}
		return 0;
		
	}
	
	
}

