/*
 * Package:com.timerTask
 * FileName: UpdateChannelJobAction.java 
 */
package com.rbt.timerTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.common.util.DateUtil;
import com.rbt.common.util.JsonUtil;
import com.rbt.function.CreateSpringContext;
import com.rbt.message.HttpSenderKtj;
import com.rbt.model.Goodsorder;
import com.rbt.model.Kjtrecall;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IKjtrecallService;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.BatchListFuc;
/**
 * @author : HZX
 * @date : Oct 9, 2014 2:25:59 PM
 * @Method Description : 定时更新订单状态
 */
public class KjtStateJob extends CreateSpringContext implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("=============order KjtStateJob=============");
		try {
				startUpdate();// 执行更新
		} catch (Exception e) {
			System.err.println("定时出现异常情况");
		}
		System.out.println("=============order KjtStateJob end=============");
	}
	@SuppressWarnings({ "unchecked", "unchecked", "static-access" })
	private static void startUpdate() throws Exception{
		HttpSenderKtj client=new HttpSenderKtj();
		JsonUtil jsonUtil=new JsonUtil();
		IKjtrecallService kjtrecallService = (IKjtrecallService) getContext().getBean("kjtrecallService");
		IGoodsorderService goodsorderService=(IGoodsorderService) getContext().getBean("goodsorderService");
		Map kjtMap =new HashMap();
		kjtMap.put("no_sostatus", "5");//完成就不用了
		int count=kjtrecallService.getCount(kjtMap);
		List clist=BatchListFuc.batchListKJT(count);
		System.err.println("count:"+count+",clist-size:"+clist.size());
		for(int i=0;i<clist.size();i++){
			  Map stMap=(Map)clist.get(i);
		      Map meMap = new HashMap();
		      meMap.put("start", 0);
		      meMap.put("no_sostatus", "5");//完成就不用了
		      meMap.put("start",  stMap.get("start"));
		      meMap.put("limit", stMap.get("limit"));
		      List list = kjtrecallService.getList(meMap);
		      String SOSysNo="",order_id="",SOStatus="";
		      Map<String,Object> map = new HashMap<String,Object>();
		      ArrayList orderList = new ArrayList(); //组装id 列表
				for(int j=0;j<list.size();j++){
					Map listMap=(HashMap)list.get(j);
					if(listMap.get("sosysno")!=null && !"".equals(listMap.get("sosysno").toString())){
						 SOSysNo=listMap.get("sosysno").toString();
						 orderList.add(SOSysNo);
						 System.out.print(SOSysNo+",");
					}
				}
				System.out.println("======KjtStateJob=======");
				map.put("SaleChannelSysNo", SysconfigFuc.getSysValue("cfg_channelid"));
				map.put("OrderIds", orderList);
				System.out.println("OrderIds:"+orderList+",SaleChannelSysNo:"+SysconfigFuc.getSysValue("cfg_channelid"));
				try {
					JSONObject jsObj = JSONObject.fromObject(map);  //      转化为json格式
					String jsonString=client.readContentFromPost(jsObj+"","order.SOTrace");//获取返回数据
					System.out.println("KjtStateJob return :"+jsonString);
					if(jsonUtil.toMap(jsonString)==null){
						 continue;
					 }
					String dateString=(String) jsonUtil.toMap(jsonString).get("Data");
					String TraceOrderList=(String) jsonUtil.toMap(dateString).get("TraceOrderList");
					 JSONArray jsonArray = JSONArray.fromObject(TraceOrderList);   
					 Object[] os = jsonArray.toArray();   //获取需要的数据进行操作
					 for (int k = 0; k < os.length; k++) {
						 SOSysNo=(String) jsonUtil.toMap(os[k]+"").get("SOID");
						 SOStatus=(String) jsonUtil.toMap(os[k]+"").get("SOStatus");
						 Kjtrecall kjtrecall=new Kjtrecall();
						 kjtrecall=kjtrecallService.get(SOSysNo);
						 kjtrecall.setSostatus(SOStatus);
						 kjtrecallService.update(kjtrecall);
						 System.out.println("======KjtStateJob kjtrecallService success======");
						 order_id=(String) jsonUtil.toMap(os[k]+"").get("MerchantOrderId"); 
						 Goodsorder gorder = new Goodsorder();//更新订单状态
						 gorder = goodsorderService.get(order_id);
						 if(gorder==null){
							 continue;
						 }
						 gorder.setKjtorder_state(SOStatus);
						 System.out.println("order_id:"+order_id+",SOStatus:"+SOStatus);
						 if(SOStatus.equals("45")){
							 System.out.println("======KjtStateJob start updateOrder  45======");
							 if(null!=gorder.getOrder_state() && "2".equals(gorder.getOrder_state())){
								 SOStatus="3";//已发货
								 gorder.setOrder_state(SOStatus);
							 }
							 gorder.setSosysno(SOSysNo);
							//设置通关操作时间,主要是定时器作为判断 系统自动确认收货的开始时间
							 gorder.setSend_time(DateUtil.getCurrentTime());
							 gorder.setIs_clearance("1");//通关成功
							 if("0".endsWith(gorder.getIs_sea())){
									gorder.setDeliver_state("2");
							  }

						 }
						 if(SOStatus.equals("6")||SOStatus.equals("65")||SOStatus.equals("7")){
							 System.out.println("======KjtStateJob start updateOrder  fail 6 , 65 ,7======");
							 gorder.setIs_clearance("2");//通关失败
						 }
						 goodsorderService.update(gorder);
						 System.out.println("======KjtStateJob updateOrder success======");
					}
				} catch (Exception e) {
					 continue;
				}
				
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		startUpdate();
	}
	
	
}
