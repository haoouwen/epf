package com.rbt.timerTask;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.MessageAltFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Goodsorder;
import com.rbt.service.IDirectorderdetailService;
import com.rbt.service.IGoodsorderService;
/**	
 * @author :  HXK
 * @param :mes_id：预售尾款过期定时操作 order_id：订单ID
 * @date Mar 11, 2014 2:35:04 PM
 * @Method Description :支付尾款过期，将预售订单取消，同时将定金支付给卖家-每隔1小时检查一次
 */
public class DirectEndPay extends CreateSpringContext implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			directoption();
		} catch (Exception e) {
			System.err.println("=======================超时支付尾款处理操作出现异常===================");
			e.printStackTrace();
		}
	}
	
	/**	
	 * @author :  CYC
	 * @param :mes_id：消息提醒模版 order_id：订单ID
	 * @date Mar 11, 2014 2:35:04 PM
	 * @Method Description :前台订单提醒-发送给买家
	 */
	public void mestipByBuyer(String mes_id,Goodsorder gorder) throws UnsupportedEncodingException{
			MessageAltFuc mesalt=new MessageAltFuc();
			mesalt.messageAutoSend(mes_id,gorder.getBuy_cust_id(),gorder);
	}
	/**
	 * @Method Description :处理支付尾款到期的操作
	 * @author: HXK
	 * @date : Dec 10, 2014 9:45:46 AM
	 * @param 
	 * @return return_type
	 */
	public void directoption() throws Exception{
		//注入bean
		IDirectorderdetailService directorderdetailService=(IDirectorderdetailService) getContext().getBean("directorderdetailService");
		IGoodsorderService goodsorderService=(IGoodsorderService) getContext().getBean("goodsorderService");
		//预售订单支付尾款到期处理
		//获取系统前三天日期
		System.out.println("========================预售订单支付尾款到期处理开始=================================");
		Integer cfg_yushouendpaytime=3;
		if(SysconfigFuc.getSysValue("cfg_yushouendpaytime")!=null&&!"".equals(SysconfigFuc.getSysValue("cfg_yushouendpaytime"))){
			//获取系统设置多少尾款结束
			cfg_yushouendpaytime=Integer.parseInt(SysconfigFuc.getSysValue("cfg_yushouendpaytime"));
		};//预售尾款结束时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);   
		calendar.add(Calendar.DATE, -cfg_yushouendpaytime);
		//获取三天前的日期
		long threedate = calendar.getTime().getTime();
		Map mapopt = new HashMap();
		mapopt.put("pay_state", "1");
		List directsorderdetailist = directorderdetailService.getList(mapopt);
		for(int i=0;i<directsorderdetailist.size();i++) {
			HashMap mapvalue = new HashMap();
			mapvalue = (HashMap)directsorderdetailist.get(i);
			String endpay_time="";//获取开始支付尾款时间
			endpay_time = mapvalue.get("endpay_time").toString();
			//获取订单详情标识
			String trade_id = "";
			trade_id = mapvalue.get("trade_id").toString();
			//获取订单号标识
			String order_id = "";
			order_id = mapvalue.get("order_id").toString(); 
			//获取定金金额
			Double earnest = 0.0,totalearnest=0.0;
			Integer num=0;
			earnest = Double.parseDouble(mapvalue.get("earnest").toString());
		    num = Integer.parseInt(mapvalue.get("order_num").toString());
			totalearnest = earnest*num;
			String buy_cust_id ="";
			long timeStart=sdf.parse(endpay_time).getTime();
			if(timeStart < threedate){
				//定金支付给卖家
				Goodsorder goodsorder = goodsorderService.get(order_id);
				if(goodsorder!=null){
					buy_cust_id = goodsorder.getBuy_cust_id();
					directorderdetailService.sellerFundManage(order_id,totalearnest,buy_cust_id,"1",trade_id);
				}
			}
		}
		System.out.println("==================================预售订单支付尾款到期处理结束==============================");
	}
	//测试方法
	public static void main(String args[]) throws Exception{
		DirectEndPay dd = new DirectEndPay();
		dd.directoption();
	}
}
