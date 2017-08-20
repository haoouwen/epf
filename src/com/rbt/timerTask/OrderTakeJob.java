package com.rbt.timerTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IGoodsorderService;
/**
 * 获取所有为收货的的goodsOrder
 * @author LHY
 *
 */
public class OrderTakeJob extends CreateSpringContext implements Job{
	//注入bean
	IGoodsorderService goodsorderService=(IGoodsorderService) getContext().getBean("goodsorderService");
	IGoodsevalService goodsevalService=(IGoodsevalService) getContext().getBean("goodsevalService");
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		getOrderTake();
		getAss();
	}
	//获取未收货的订单，并更新状态
	@SuppressWarnings("unchecked")
	public void getOrderTake(){
		Map map =new HashMap();
		map.put("order_state","3");
		//直邮
		map.put("is_sea", "1");
		String dayNum=SysconfigFuc.getSysValue("cfg_orderTime");
		if(dayNum!=null&&!dayNum.equals("")){
			map.put("send_time",dayNum);
		}
		goodsorderService.updateAutoConfirmReceipt(map);
		//保税
		map.put("is_sea", "0");
	    dayNum=SysconfigFuc.getSysValue("cfg_seaorderTime");
		if(dayNum!=null&&!dayNum.equals("")){
			map.put("send_time",dayNum);
		}
		goodsorderService.updateAutoConfirmReceipt(map);		

	}
	//获取未未评价的订单，并更新状态
	@SuppressWarnings("unchecked")
	public void getAss(){
		Map map =new HashMap();
		map.put("order_state","7");
		String dayNum=SysconfigFuc.getSysValue("cfg_assessTime");
		if(dayNum!=null&&!dayNum.equals("")){
			map.put("sureTime",dayNum);
		}
		List list=this.goodsorderService.getTake(map);
		System.out.println(list.size()+"============");
		this.goodsorderService.autoASS(list);
	}
	public static void main(String[]args){
		OrderTakeJob ot=new OrderTakeJob();
		ot.getOrderTake();
	}

}
