/*
  
 
 * Package:com.rbt.servie.impl
 * FileName: InterhistoryService.java 
 */
package com.rbt.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IFundhistoryDao;
import com.rbt.dao.IInterhistoryDao;
import com.rbt.dao.IMemberfundDao;
import com.rbt.dao.IMemberinterDao;
import com.rbt.model.Fundhistory;
import com.rbt.model.Interhistory;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.service.IInterhistoryService;

/**
 * @function 功能 记录会员积分异动历史Service层业务接口实现
 * @author 创建人 CYC
 * @date 创建日期 Thu Jul 14 15:01:09 CST 2014
 */
@Service
public class InterhistoryService extends GenericService<Interhistory,String> implements IInterhistoryService {

	@Autowired
	private IMemberfundDao memberfundDao;
	@Autowired
	private IFundhistoryDao fundhistoryDao;
	@Autowired
	private IMemberinterDao memberinterDao;
	IInterhistoryDao interhistoryDao;

	@Autowired
	public InterhistoryService(IInterhistoryDao interhistoryDao) {
		super(interhistoryDao);
		this.interhistoryDao = interhistoryDao;
	}

	public int getInterhistorySumScore(Map map) {
		return this.interhistoryDao.getInterhistorySumScore(map);
	}

	public List getReleaseCustId(Map map) {
		return this.interhistoryDao.getReleaseCustId(map);
	}

	/**
	 * @author : LJQ
	 * @date : Apr 18, 2014 8:56:35 PM
	 * @Method Description :积分兑换资金
	 */
	public void optioninter(Memberfund memberfund, String use_num, String session_cust_id, String session_user_id, Memberinter memberinter, String rech_fund, int rechange_value) {
		Double fund_num_=0.00;
		Double use_num_=0.00;
		//修改余额表
		Integer get_gold=Integer.parseInt(rech_fund)/rechange_value;
		use_num_=Double.valueOf(memberfund.getUse_num())+get_gold;
		fund_num_=Double.valueOf(memberfund.getFund_num())+get_gold;
		memberfund.setFund_num(""+Double.valueOf(fund_num_));
		memberfund.setUse_num(""+Double.valueOf(use_num_));
		this.memberfundDao.update(memberfund);
		
		//插入余额异动表
		Fundhistory fundhistory=new Fundhistory();
		fundhistory.setCust_id(session_cust_id);
		fundhistory.setFund_in(Double.valueOf(get_gold));
		fundhistory.setFund_out(0.00);
		fundhistory.setBalance(Double.valueOf(use_num_));
		fundhistory.setUser_id(session_user_id);
		fundhistory.setReason("兑换"+get_gold+"余额成功");
		fundhistory.setRemark("");
		this.fundhistoryDao.insert(fundhistory);
		
		//修改积分表
		if(memberinter!=null){
		    //兑换的积分数加上剩余积分数
		    Double total_inter = Double.valueOf(memberinter.getFund_num())+Double.valueOf(rech_fund);
		    memberinter.setFund_num(total_inter.toString());
			this.memberinterDao.update(memberinter);
			//插入积分历史记录
			Interhistory interhistory=new Interhistory();
			interhistory.setCust_id(session_cust_id);
			interhistory.setInter_in("0");
			interhistory.setInter_out(rech_fund);
			interhistory.setThisinter(total_inter.toString());
			interhistory.setUser_id(session_user_id);
			interhistory.setReason("兑换"+rech_fund+"积分");
			interhistory.setRemark("");
			super.insert(interhistory);
		}
	}
    
	/**	
	 * @author : WXP
	 * @param :
	 * @date May 6, 2014 4:14:30 PM
	 * @Method Description :获取每日积分
	 */
	public String getIntegral(String cust_id,String continue_day,String max_integral,String daily_integral) throws IOException{
		//输出内容
		String outStr = "";
		//连续领取天数
		int days = 0;
		if(cust_id != null && !cust_id.equals("")){
			//获取连续领取天数
			days = getDays(cust_id);
			int inter_num = 0;//总积分
			int today_num = 0;//今日可获得积分
			//判断今日是否已经领取积分
			Map map = new HashMap();
			map.put("todays", "todays");
			map.put("cust_id", cust_id);
			map.put("reason", "每日领取");
			int count = interhistoryDao.getCount(map);
			if(count > 0){
				outStr = "b";
			}else{
				//更新积分表
				Memberinter memberinter = memberinterDao.get(cust_id);
				//计算今日可获得积分积分
				if(memberinter != null){
					inter_num = Integer.parseInt(memberinter.getFund_num());
					if(days >= Integer.parseInt(continue_day)){//连续领取天数足够达到最大积分
						today_num = Integer.parseInt(max_integral);
					}else{
						today_num =  Integer.parseInt(daily_integral) * (days + 1);
					}
					inter_num = inter_num + today_num;
					memberinter.setFund_num(String.valueOf(inter_num));
					memberinterDao.update(memberinter);
					
					//插入积分移动记录
					Interhistory ih = new Interhistory();
					ih.setCust_id(cust_id);
					ih.setInter_in(String.valueOf(today_num));
					ih.setInter_out("0");
					ih.setReason("每日领取");
					ih.setUser_id("105");
					ih.setThisinter(String.valueOf(inter_num));
					interhistoryDao.insert(ih);
					outStr = String.valueOf(today_num);
				}else{
					outStr = "c";
				}
				
			}
		}else{
			outStr = "a";
		}
		return outStr;
	}
	/**	
	 * @author : WXP
	 * @param :cust_id
	 * @date May 6, 2014 2:27:30 PM
	 * @Method Description :统计用户连续领取积分天数
	 */
	private int getDays(String cust_id){
		Map map = new HashMap();
		map.put("cust_id", cust_id);
		map.put("reason", "每日领取");
		//所有记录数
		int count = this.interhistoryDao.getCount(map);
		//连续领取积分天数
		int days = 0;
		for(int i = 0; i < count + 1; i++){
			Map dayMap = new HashMap();
			dayMap.put("daydiff", i);
			dayMap.put("cust_id", cust_id);
			map.put("reason", "每日领取");
			int dayCount = this.interhistoryDao.getCount(dayMap);
			if(dayCount > 0){
				days++;
			}else if(i == 0){
				continue;
			}else{
				break;
			}
		}
		return days;
	}
}

