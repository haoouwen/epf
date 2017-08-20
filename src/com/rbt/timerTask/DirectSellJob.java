package com.rbt.timerTask;
import java.util.HashMap;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.service.IGoodsorderService;
/**
 * @author : HZX
 * @date : Jul 24, 2014 3:46:54 PM
 * @Method Description :下完订单30分钟内未付款取消订单
 */
public class DirectSellJob extends CreateSpringContext implements Job {
	//获取对象接口
	IGoodsorderService goodsorderService=(IGoodsorderService) getContext().getBean("goodsorderService");
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {   
			publicorderout();//普通定时检查订单是否支付，timeout分钟未支付取消订单并恢复
			recycleorderout();//自动删除订单回收的订单
		} catch (Exception e) {
			System.err.println("==============================订单付款状态设置出现异常情况==============================");
			e.printStackTrace();
		}
		
	}
	
	//普通定时检查订单是否支付，24小时未支付取消订单并恢复
	private void publicorderout(){
		System.err.println("==============================普通订单下完订单24小时内未付款取消订单开始==============================");
		String timeout = SysconfigFuc.getSysValue("cfg_publictimeout");
		Map map = new HashMap();
		map.put("order_type", "0,1");
		map.put("order_state", "1");
		map.put("ortimeout",timeout);
		goodsorderService.publicorderout(map);
		System.err.println("==============================普通订单下完订单24小时内未付款取消订单结束==============================");
	}
	
	//取消订单自动回收
	private void recycleorderout(){
		System.err.println("==============================取消订单自动回收订单开始==============================");
		String timeout = SysconfigFuc.getSysValue("cfg_recycle_limt_date");
		Map map = new HashMap();
		map.put("order_state", "0");
		map.put("ordayout",timeout);
		goodsorderService.recycleorderout(map);
		System.err.println("==============================取消订单自动回收订单结束==============================");
	}
	public static void main(String[]args){
		DirectSellJob ss=new DirectSellJob();
		ss.recycleorderout();
	}

}
