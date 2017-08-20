/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: MembercreditService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.IMembercreditDao;
import com.rbt.model.Membercredit;
import com.rbt.service.IMembercreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 会员信誉Service层业务接口实现
 * @author 创建人 XBY
 * @date 创建日期 Tue Apr 22 19:59:28 CST 2014
 */
@Service
public class MembercreditService extends GenericService<Membercredit,String> implements IMembercreditService {
	
	IMembercreditDao membercreditDao;

	@Autowired
	public MembercreditService(IMembercreditDao membercreditDao) {
		super(membercreditDao);
		this.membercreditDao = membercreditDao;
	}
	
	/**
	 * 通过UserId获取会员信誉
	 * @param membercredit
	 * @return
	 */
	public Membercredit getByCustId(String cust_id){
		return (Membercredit)this.membercreditDao.getByCustId(cust_id);
	}
	
	/**
	 * 修改买家信誉
	 * @param membercredit
	 */
	public void updateBuyNum(Membercredit membercredit){
		this.membercreditDao.updateBuyNum(membercredit);
	}
	
}

