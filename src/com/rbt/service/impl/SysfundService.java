/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: SysfundService.java 
 */
package com.rbt.service.impl;

import com.rbt.dao.ISysfundDao;
import com.rbt.model.Sysfund;
import com.rbt.service.ISysfundService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @function 功能 平台总资金Service层业务接口实现
 * @author 创建人 HXM
 * @date 创建日期 Sun Jul 13 17:17:59 CST 2014
 */
@Service
public class SysfundService extends GenericService<Sysfund,String> implements ISysfundService {
	
	ISysfundDao sysfundDao;

	@Autowired
	public SysfundService(ISysfundDao sysfundDao) {
		super(sysfundDao);
		this.sysfundDao = sysfundDao;
	}
	/**
	 * @author:HXM
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:freeze_num:冻结/解冻金额， flag_int：标记冻结（0）/解冻（1）
	 * @Description:冻结和解冻操作（可用资金和冻结资金的操作）（单纯的对sysfund进行资金操作不涉及记录表），返回可用资金
	 */
	public double freezeNum(double freeze_num,int flag_int){
		Sysfund sysfund=sysfundDao.get("0");
		double i2=0.0;
		if(sysfund!=null){
			//冻结
			if(flag_int==0){
				i2=Double.valueOf(sysfund.getUse_num())-freeze_num;//可用资金
				double i1=Double.valueOf(sysfund.getFreeze_num())+freeze_num;//冻结
				sysfund.setFreeze_num(i1);
				sysfund.setUse_num(i2);
			}else{//解冻
				i2=Double.valueOf(sysfund.getUse_num())+freeze_num;
				double i1=Double.valueOf(sysfund.getFreeze_num())-freeze_num;
				sysfund.setFreeze_num(i1);
				sysfund.setUse_num(i2);
			}
		}
		sysfundDao.update(sysfund);
		return i2;
	}
	
	/**
	 * @author:HXM
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:outorin_num:出金/入金金额，  flag_int：标记出金（0）/入金（1）
	 * @Description:出金和入金操作（可用资金和总资金的操作）（单纯的对sysfund进行资金操作不涉及记录表），返回可用资金
	 */
	public double outAndInNum(double outorin_num,int flag_int){
		Sysfund sysfund=sysfundDao.get("0");
		double i2=0.0;
		if(sysfund!=null){
			//出金
			if(flag_int==0){
				double i1=Double.valueOf(sysfund.getFund_num())-outorin_num;//总资金
				i2=Double.valueOf(sysfund.getUse_num())-outorin_num;//可用资金 
				sysfund.setFund_num(i1);
				sysfund.setUse_num(i2);
			}else{//入金
				double i1=Double.valueOf(sysfund.getFund_num())+outorin_num;
				i2=Double.valueOf(sysfund.getUse_num())+outorin_num;
				sysfund.setFund_num(i1);
				sysfund.setUse_num(i2);
			}
		}
		sysfundDao.update(sysfund);
		return i2;
	}

	/**
	 * @author:HXM
	 * @date:Jul 8, 20141:50:35 PM
	 * @param:cust_id：添加对应的账号，addNum:添加的金额
	 * @Description:用于在线支付订单对运营商账号的操作，返回可用余额（总资金和冻结资金增加，可用资金不变）
	 */
	public double addNumByOnLion(double addNum){
		double i3=0.0,i1=0.0,i2=0.0;
		 Sysfund sysfund=sysfundDao.get("0");
		if(sysfund!=null){
			i1=Double.valueOf(sysfund.getFund_num())+addNum;
		    i2=Double.valueOf(sysfund.getFreeze_num())+addNum;
			sysfund.setFund_num(i1);
			sysfund.setFreeze_num(i2);
			sysfundDao.update(sysfund);
		    i3=Double.valueOf(sysfund.getUse_num());//可用资金
		}
		return i3;
	}
	
	/**
	 * @author:QJY
	 * @date:Jul 8, 2015 1:50:35 PM
	 * @param:reduceNum:减少的金额
	 * @Description:用于在线退款订单对平台账号的操作，返回可用余额（总资金和冻结资金减少，可用资金不变）
	 */
	public double reduceNumByrefund(double reduceNum){
		double i3=0.0,i1=0.0,i2=0.0;
		Sysfund sysfund=sysfundDao.get("0");
		if(sysfund!=null){
			i1=Double.valueOf(sysfund.getFund_num())-reduceNum;
			i2=Double.valueOf(sysfund.getFreeze_num())-reduceNum;
			sysfund.setFund_num(i1);
			sysfund.setFreeze_num(i2);
			sysfundDao.update(sysfund);
		    i3=Double.valueOf(sysfund.getUse_num());//可用资金
		}
		return i3;
	}

}

