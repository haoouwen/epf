/*
  
 
 * Package:com.timerTask
 * FileName: SendSubscribeJobAction.java 
 */
package com.rbt.timerTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.qq.connect.utils.json.JSONException;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.LogisticsFuc;
import com.rbt.function.MessageAltFuc;
import com.rbt.model.Goodsorder;
import com.rbt.model.Sendmode;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.ISendmodeService;
/**
 * @function 功能  自动评价
 * @author  创建人  HXK
 * @date  创建日期  2014-07-24
 */
public class UpdateExpressState extends CreateSpringContext implements Job {
	//注入bean
	IGoodsorderService goodsorderService=(IGoodsorderService) getContext().getBean("goodsorderService");
	ISendmodeService sendmodeService=(ISendmodeService) getContext().getBean("sendmodeService");
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		try {
			updateExprssOrder();
		} catch (Exception e) {
			System.err.println("更新订单物流状态异常");
		}
	}
	//获取待收货的订单，并更新状态
	@SuppressWarnings("unchecked")
	public void updateExprssOrder() throws IOException, JSONException{
		System.out.println("========================start express====================");
		Map map =new HashMap();
		map.put("order_state","3");
		//是否为内部员工下单 0：不是 1：是
		map.put("is_vip","0");
		//is_del="0";
		map.put("is_del","1");
		//-1 待查询 0 查询异常 1 暂无记录 2 在途中 3 派送中 4 已签收 5 用户拒签 6 疑难件 7 无效单 8 超时单 9 签收失败 10 退回 
		map.put("express_transport_state_no", "4");
		//取出待收货的订单
		List list=this.goodsorderService.getTake(map);
		Map oMap=new HashMap();
		if(list!=null&&list.size()>0){
			for (int i = 0; i <list.size(); i++) {
				oMap=(HashMap) list.get(i);
				if(oMap!=null&&oMap.get("order_id")!=null){
					//订单的id
					String orderId=(String) oMap.get("order_id");
					String order_state=(String) oMap.get("order_state");
					String send_mode=(String) oMap.get("send_mode");
					String send_num=(String) oMap.get("send_num");
					String kuai_company="";
					System.out.println("========================start express order_id="+orderId+"====================");
					//校验订单状态是否还处于已发货状态
					if(order_state!=null&&order_state.equals("3")){
						Sendmode sendmode = new Sendmode();
						sendmode = sendmodeService.get(send_mode);
						if (sendmode != null) {
							// 获取快递公司名称
							kuai_company = sendmode.getSmode_name().trim();
						}
						if (send_num!=null&&!"".equals(send_num)) {
							send_num = send_num.trim();
						}
						//获取物流状态
						String loginfo_state="";
						loginfo_state=LogisticsFuc.expressState(send_num, kuai_company);
						if(loginfo_state!=null&&!"".equals(loginfo_state)){
							Map temp=new HashMap();
							temp.put("order_id",orderId);
							temp.put("order_state",order_state);
							//更新订单状态
							temp.put("express_transport_state", loginfo_state);
							
							//发送短信通知，快件已经到达目的城市，在进行配送
							Goodsorder gorder = new Goodsorder();
							gorder = goodsorderService.get(orderId);
							//过滤已经发送短信的 3 派送中(目的地)
							if(gorder!=null&&loginfo_state.equals("3")&&!"1".equals(gorder.getSend_city_msg())){
								MessageAltFuc mesalt = new MessageAltFuc();
								mesalt.messageAutoSend("16", gorder.getBuy_cust_id(), gorder);
								//更新订单状态
								temp.put("send_city_msg", "1");
								System.out.println("========================start express order_id="+orderId+" sendmsg success ====================");
							}
							this.goodsorderService.update(temp);
						}
					}
				}
			}
		}
//配置quartz_job.xml 
//		<job>
//		<job-detail>
//			<name>updateExpressState</name>
//			<mydescription>处理订单物流状态且快递到达所在城市发送短信-每1小时检测一次</mydescription>
//			<group>group16</group>
//			<job-class>com.rbt.timerTask.UpdateExpressState</job-class>
//		</job-detail>
//		<trigger>
//			<cron>
//				<name>triggerUpdateExpress</name>
//				<group>group16</group>
//				<job-name>updateExpressState</job-name>
//				<job-group>group16</job-group>
//				<cron-expression>0 1 * * * ? </cron-expression>
//			</cron>
//		</trigger>
//	    </job>
	}
	
	public static void main(String[]args) throws IOException, JSONException{
		UpdateExpressState ot=new UpdateExpressState();
		ot.updateExprssOrder();
	}
	
	
}
