/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MemberinterService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMemberinterDao;
import com.rbt.model.Interhistory;
import com.rbt.model.Memberinter;
import com.rbt.service.IMemberinterService;

/**
 * @function 功能 记录会员积分数Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Thu Jul 14 14:30:38 CST 2014
 */
@Service
public class MemberinterService extends GenericService<Memberinter,String> implements IMemberinterService {

	IMemberinterDao memberinterDao;

	@Autowired
	public MemberinterService(IMemberinterDao memberinterDao) {
		super(memberinterDao);
		this.memberinterDao = memberinterDao;
	}
	
	//跟新积分
	public void updateinter(Interhistory interhistory,Memberinter memberinter){
		this.memberinterDao.updateinter(interhistory, memberinter);
	}

	public Double getSumCount(Map map) {
		return this.memberinterDao.getSumCount(map);
	}

}

