/*
 * Package:com.rbt.servie.impl
 * FileName: MemberfundService.java 
 */
package com.rbt.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMemberfundDao;
import com.rbt.model.Memberfund;
import com.rbt.service.IMemberfundService;

/**
 * @function 功能 会员资金Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Tue Jul 12 09:26:58 CST 2014
 */
@Service
public class MemberfundService extends GenericService<Memberfund,String> implements IMemberfundService {

	IMemberfundDao memberfundDao;

	@Autowired
	public MemberfundService(IMemberfundDao memberfundDao) {
		super(memberfundDao);
		this.memberfundDao = memberfundDao;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberfundService#updateMemberfund(java.util.Map)
	 */
	public void updateMemberfund(Map map) {
		this.memberfundDao.updateMemberfund(map);
	}
	//修改会员资金
	public void insertfundoption(String cust_id,String session_user_id,String fund_num){
		this.memberfundDao.insertfundoption(cust_id, session_user_id, fund_num);
	}
    
	//运营商平台的总资金,包括总资金，可用总资金，冻结总资金
	public HashMap getTotalFund(){
		return this.memberfundDao.getTotalFund();
	}
	
	/**
	 * @author:HXM
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:cust_id：对应冻结/解冻资金账号，freeze_num:冻结/解冻金额，
	 * flag_int：标记冻结（0）/解冻（1）
	 * @Description:冻结和解冻操作（可用资金和冻结资金的操作）（单纯的对memberfunt进行资金操作不涉及记录表），返回可用资金
	 */
	public double freezeNum(String cust_id,double freeze_num,int flag_int){
		Memberfund memberfund=memberfundDao.get(cust_id);
		double i2;
		//冻结
		if(flag_int==0){
			i2=Double.valueOf(memberfund.getUse_num())-freeze_num;//可用资金
			double i1=Double.valueOf(memberfund.getFreeze_num())+freeze_num;//冻结
			if(i1<=0){i1=0.0;}
			if(i2<=0){i2=0.0;}
			memberfund.setFreeze_num(i1+"");
			memberfund.setUse_num(i2+"");
		}else{//解冻
			i2=Double.valueOf(memberfund.getUse_num())+freeze_num;
			double i1=Double.valueOf(memberfund.getFreeze_num())-freeze_num;
			if(i1<=0){i1=0.0;}
			if(i2<=0){i2=0.0;}
			memberfund.setFreeze_num(i1+"");
			memberfund.setUse_num(i2+"");
		}
		memberfundDao.update(memberfund);
		return i2;
	}
	
	/**
	 * @author:QJY
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:cust_id：对应冻结/解冻资金账号，freeze_num:冻结/解冻金额，
	 * flag_int：标记冻结（0）/解冻（1）
	 * @Description:冻结和解冻操作（可用资金和冻结资金的操作）（单纯的对memberfunt进行资金操作不涉及记录表），返回可用资金
	 */
	public double freezePlusNum(String cust_id,double freeze_num,int flag_int){
		Memberfund memberfund=memberfundDao.get(cust_id);
		//解冻
		if(flag_int==0){
			double i2=Double.valueOf(memberfund.getFund_num())-freeze_num;//剩余总资金
			double i1=Double.valueOf(memberfund.getFreeze_num())-freeze_num;//剩余冻结资金
			if(i1<=0){i1=0.0;}
			if(i2<=0){i2=0.0;}
			memberfund.setFund_num(i2+"");
			memberfund.setFreeze_num(i1+"");
			
		}else{//冻结
			double i2=Double.valueOf(memberfund.getUse_num())-freeze_num;
			double i1=Double.valueOf(memberfund.getFreeze_num())+freeze_num;
			if(i1<=0){i1=0.0;}
			if(i2<=0){i2=0.0;}
			memberfund.setFreeze_num(i1+"");
			memberfund.setUse_num(i2+"");
		}
		
		memberfundDao.update(memberfund);
		double i3 = Double.valueOf(memberfund.getUse_num());
		return i3;
	}
	
	/**
	 * @author:HXM
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:cust_id：对应出金和入金资金账号，outorin_num:出金/入金金额，
	 * flag_int：标记出金（0）/入金（1）
	 * @Description:出金和入金操作（可用资金和总资金的操作）（单纯的对memberfunt进行资金操作不涉及记录表），返回可用资金
	 */
	public double outAndInNum(String cust_id,double outorin_num,int flag_int){
		Memberfund memberfund=memberfundDao.get(cust_id);
		double i2=0.00;
		//出金
		if(flag_int==0){
			double i1=Double.valueOf(memberfund.getFund_num())-outorin_num;//总资金
			i2=Double.valueOf(memberfund.getUse_num())-outorin_num;//可用资金
			if(i1<=0){i1=0.0;}
			if(i2<=0){i2=0.0;}
			memberfund.setFund_num(i1+"");
			memberfund.setUse_num(i2+"");
		}else{//入金
			double i1=Double.valueOf(memberfund.getFund_num())+outorin_num;
			i2=Double.valueOf(memberfund.getUse_num())+outorin_num;
			if(i1<=0){i1=0.0;}
			if(i2<=0){i2=0.0;}
			memberfund.setFund_num(i1+"");
			memberfund.setUse_num(i2+"");
		}
		memberfundDao.update(memberfund);
		return i2;
	}
	
	/**
	 * @author:HXM
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:cust_id：添加对应的账号，addNum:添加的金额
	 * @Description:用于在线支付订单对运营商账号的操作，返回可用余额（总资金和冻结资金增加，可用资金不变）
	 */
	public double addNumByOnLion(String cust_id,double addNum){
		Memberfund memberfund=memberfundDao.get(cust_id);
		double i1=Double.valueOf(memberfund.getFund_num())+addNum;
		double i2=Double.valueOf(memberfund.getFreeze_num())+addNum;
		if(i1<=0){i1=0.0;}
		if(i2<=0){i2=0.0;}
		memberfund.setFund_num(i1+"");
		memberfund.setFreeze_num(i2+"");
		memberfundDao.update(memberfund);
		double i3=Double.valueOf(memberfund.getUse_num());//可用资金
		return i3;
	}

}

