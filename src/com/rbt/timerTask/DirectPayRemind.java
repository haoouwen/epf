package com.rbt.timerTask;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.MessageAltFuc;
import com.rbt.model.Goodsorder;
import com.rbt.service.IDirectsellService;
import com.rbt.service.IGoodsorderService;
/**	
 * @author :  CYC
 * @param :mes_id：预售尾款定时操作 order_id：订单ID
 * @date Mar 11, 2014 2:35:04 PM
 * @Method Description :前台订单提醒-发送给买家-每天10检查一次
 */
public class DirectPayRemind extends CreateSpringContext implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			directoption();
		} catch (Exception e) {
			System.err.println("尾款定时操作出现异常");
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
	
	public void directoption() throws Exception{
		//注入bean
		IDirectsellService directsellService=(IDirectsellService) getContext().getBean("directsellService");
		IGoodsorderService goodsorderService=(IGoodsorderService) getContext().getBean("goodsorderService");
		System.out.println("===============================预付款提醒开始===============================");
		Map infomap = new HashMap();
		//6：预售短信、邮箱、站内信提醒
		infomap.put("order_type", "6");
		infomap.put("tail_time","1");
		infomap.put("info_status", "0");
		List<Map<String,String>> directselllist = directsellService.getdeliverpay(infomap);
		for (Map<String,String> maps : directselllist) {
			String order_id="";//获取订单号
			order_id = maps.get("order_id").toString();
			Goodsorder goodsorder= goodsorderService.get(order_id);
			//发送信息提醒
			mestipByBuyer("14",goodsorder);
			//跟新已经发送信息提醒的订单状态
			goodsorder.setInfo_status("1");
			goodsorderService.update(goodsorder);
		}	
		System.out.println("==================================预付款提醒结束================================");
	}
	
	//测试方法
	public static void main(String args[]) throws Exception{
		DirectPayRemind dd = new DirectPayRemind();
		dd.directoption();
	}
	
}
