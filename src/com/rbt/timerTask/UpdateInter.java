package com.rbt.timerTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Fundhistory;
import com.rbt.model.Interhistory;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IInterhistoryService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.ISysconfigService;

public class UpdateInter  extends CreateSpringContext implements Job {

	IMemberinterService memberinterService = (IMemberinterService) getContext().getBean("memberinterService");
	IMemberfundService memberfundService = (IMemberfundService) getContext().getBean("memberfundService");
	IInterhistoryService interhistoryService = (IInterhistoryService) getContext().getBean("interhistoryService");
	IFundhistoryService fundhistoryService = (IFundhistoryService) getContext().getBean("fundhistoryService");
	ISysconfigService sysconfigService = (ISysconfigService) getContext().getBean("sysconfigService");
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			// 获取系统配置是否启用自动换取积分
			//String is_inter = SysconfigFuc.getSysValue("cfg_auto_exgold");
			// 根据系统配置表取值，如果cfg_auto_exgold的值为0：表示启用定时；1：不启用定时
			//if (is_inter.equals("0")) {
			//	updateInter();
			//}
			//updateInter();
		} catch (Exception e) {
			System.err.println("定时自动换取积分异常情况");
			e.printStackTrace();
		}
	}

	/**
	 * @author : LJQ
	 * @date : Sep 10, 2014 1:45:46 PM
	 * @Method Description :执行自动更新积分的方法
	 */
	private void updateInter(){
		//获取上一次回馈的基金池金额
		Double  cfg_fund = Double.parseDouble(SysconfigFuc.getSysValue("cfg_fund"));
		System.out.println(cfg_fund+"==========");
		// 获取积分的总和
		Map interMap = new HashMap();
		Double intercount = memberinterService.getSumCount(interMap);
		// 获取每个会员的积分
		List list = memberinterService.getList(interMap);
		System.out.println(list);
		for(int i=0;i<list.size();i++){
			Map listMap = (HashMap)list.get(i);
			if(listMap.get("fund_num")!=null && listMap.get("cust_id")!=null){
				Double fund_num = Double.parseDouble(listMap.get("fund_num").toString());
				String cust_id = listMap.get("cust_id").toString();
				//回馈基金池的金额*该会员的积分数/所有会员的总积分之和
				Double gold = cfg_fund*fund_num/intercount;
				// 获取兑换的余额
				int ex_gold = (int) (gold-0);
				//获取剩余的积分
				int sp_inter =(int) (fund_num -ex_gold);
				// 获取本次会员自动换积分产生的基金
				Double fund =gold - ex_gold;
				//更新会员的剩余积分
				Memberinter mi =new Memberinter();
				mi.setCust_id(cust_id);
				mi.setFund_num(String.valueOf(sp_inter));
				memberinterService.update(mi);
				// 会员积分的异动流
				Interhistory ih = new Interhistory();
				ih.setCust_id(cust_id);
				ih.setInter_in("0");
				ih.setInter_out(String.valueOf(ex_gold));
				ih.setUser_id(null);
				ih.setThisinter(String.valueOf(sp_inter));
				ih.setReason("系统兑换余额，减少"+ex_gold+"积分");
				interhistoryService.insert(ih);
				// 更新会员的余额
				Memberfund mf=memberfundService.get(cust_id);
				String mf_fund_num = String.valueOf(mf.getFund_num());
				String mf_use_num = String.valueOf(mf.getUse_num());
				mf.setFund_num((Double.parseDouble(mf_fund_num)+ex_gold)+"");
				mf.setUse_num((Double.parseDouble(mf_use_num)+ex_gold)+"");
			    memberfundService.update(mf);
			    //更新会员的余额异动表
			    Fundhistory ft =new Fundhistory();
			    ft.setCust_id(cust_id);
			    ft.setFund_in(Double.valueOf(ex_gold));
			    ft.setUser_id(null);
			    ft.setFund_out(0.00);
			    ft.setBalance(Double.parseDouble(mf_use_num)+ex_gold);
			    ft.setReason("系统自动积分换取余额，兑换"+ex_gold+"余额成功");
			    fundhistoryService.insert(ft);

			}
		}
		System.out.println("自动兑换完成!");
	}
	
	/**
	 * @author : LJQ
	 * @date : Dec 15, 2014 1:08:04 PM
	 * @Method Description : 计算回馈池的金额，以便第二次使用
	 */
	public void calculationComm(){
		//获取上一次回馈的基金池金额
		Double  cfg_fund = Double.parseDouble(SysconfigFuc.getSysValue("cfg_fund"));
		//获取回馈会员的佣金比例
		Double  cfg_commrate = Double.parseDouble(SysconfigFuc.getSysValue("cfg_commrate"));
		cfg_commrate = cfg_commrate*0.01;
		//定义回馈池的金额,上个月的佣金和，上个月的基金和
		Double back_fund =0.00,comm_sum=0.00,fund_sum=0.00;
		//获取上个月佣金和
		Map map = new HashMap();
		map.put("fund_type","0");
		//获取上个月基金和
		Map fund_map = new HashMap();
		fund_map.put("fund_type", "1");
		//计算回馈基金池金额
		back_fund = cfg_fund+(comm_sum*cfg_commrate)-fund_sum;
		//回馈基金池取整后更新参数
		int back_fund_int = (int) (back_fund - 0);
		HashMap fundMap = new HashMap();
		fundMap.put("var_value", back_fund_int);
		fundMap.put("var_name", "cfg_fund");
		sysconfigService.updateByvarname(fundMap);
		
		System.out.println(comm_sum+"============="+fund_sum+"============="+back_fund+"========"+back_fund_int);
		
	}
	
	
	public static void main(String[] args) {
		new UpdateInter().updateInter();
		new UpdateInter().calculationComm();
	}

	
	
	
	
	
}
