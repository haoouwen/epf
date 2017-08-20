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
 * 定时删除发件箱的数据
 * @author LHY
 *
 */
public class DelCancelOrderBox extends CreateSpringContext implements Job {
	//获取对象接口
	IGoodsorderService goodsorderService=(IGoodsorderService) getContext().getBean("goodsorderService");
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {   
			deleterecycleorder();//自动物理删除取消订单
		} catch (Exception e) {
			System.err.println("==============================物理删除 取消订单 设置出现异常情况==============================");
			e.printStackTrace();
		}
		
	}
	//物理删除取单订单
	private void deleterecycleorder(){
		System.err.println("==============================物理删除 取消订单开始==============================");
		//获取系统设置 物理删除取消订单的月数
		String timeout = SysconfigFuc.getSysValue("cfg_delete_cancelorder");
		Map map = new HashMap();
		map.put("order_state", "0");
		map.put("ordermonths",timeout);
		goodsorderService.recycleByOrderList(map);
		System.err.println("==============================物理删除 取消订单结束==============================");
	}
	public static void main(String[]args){
		DelCancelOrderBox ss=new DelCancelOrderBox();
		ss.deleterecycleorder();
	}

}
