/*
  
 
 * Package:com.timerTask
 * FileName: UpdateChannelJobAction.java 
 */
package com.rbt.timerTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.common.util.JsonUtil;
import com.rbt.function.CreateSpringContext;
import com.rbt.message.HttpSenderKtj;
import com.rbt.model.Fepbill;
import com.rbt.service.IFepbillService;
import com.rbt.service.IKjtrecallService;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.BatchListFuc;


/**
 * @author : HZX
 * @date : Oct 9, 2014 2:25:59 PM
 * @Method Description : 代购汇账单接收
 */
public class KjtFEPBillPostJob extends CreateSpringContext implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
				startUpdate();// 执行更新
		} catch (Exception e) {
			System.err.println("定时出现异常情况");
		}
	}

	
	@SuppressWarnings({ "unchecked", "static-access" })
	private static void startUpdate() throws Exception{
		HttpSenderKtj client=new HttpSenderKtj();
		JsonUtil jsonUtil=new JsonUtil();
		IKjtrecallService kjtrecallService = (IKjtrecallService) getContext().getBean("kjtrecallService");
		IFepbillService fepbillService=(IFepbillService) getContext().getBean("fepbillService");
		Map kjtMap =new HashMap();
		kjtMap.put("no_sostatus", "5");//完成就不用了
		kjtMap.put("ypaydate", "5");
		int count=kjtrecallService.getCount(kjtMap);
		List clist=BatchListFuc.batchListKJT(count);
		for(int i=0;i<clist.size();i++){
			  Map stMap=(Map)clist.get(i);
		      Map meMap = new HashMap();
		      meMap.put("no_sostatus", "5");//完成就不用了
		      meMap.put("ypaydate", "5");
		      meMap.put("start",  stMap.get("start"));
		      meMap.put("limit", stMap.get("limit"));
		      List list = kjtrecallService.getList(meMap);
		      String SOSysNo="";
		      Map<String,Object> map = new HashMap<String,Object>();
		      ArrayList orderList = new ArrayList(); //组装id 列表
				for(int j=0;j<list.size();j++){
					Map listMap=(HashMap)list.get(j);
					if(listMap.get("sosysno")!=null && !"".equals(listMap.get("sosysno").toString())){
						 SOSysNo=listMap.get("sosysno").toString();
						 orderList.add(SOSysNo);
					}
				}
				map.put("SaleChannelSysNo", SysconfigFuc.getSysValue("cfg_channelid"));
				map.put("OrderIds", orderList);
				try {
					JSONObject jsObj = JSONObject.fromObject(map);  //      转化为json格式
					String datajson = jsObj.toString();
					String jsonString=client.readContentFromPost(jsObj+"","Invoice.FEPBillPost");//获取返回数据
					if(jsonUtil.toMap(jsonString)==null){
						 continue;
					 }
					String dateString=(String) jsonUtil.toMap(jsonString).get("Data");
					String PurchasingTotalAmount=(String) jsonUtil.toMap(dateString).get("PurchasingTotalAmount");
					String FEPBillId=(String) jsonUtil.toMap(dateString).get("FEPBillId");
					String OrderIds=(String) jsonUtil.toMap(datajson).get("OrderIds");
					Fepbill fepbill =new Fepbill();
					fepbill.setOrderids(OrderIds);
					fepbill.setFepbill_id(FEPBillId);
					fepbill.setPurchasingtotalamount(PurchasingTotalAmount);
					fepbillService.insert(fepbill);
					//去除中括号
					String sosysno ="";
					if(OrderIds.indexOf("[")!=-1||OrderIds.indexOf("]")!=-1){
						sosysno= OrderIds.replace("[", "").replace("]", "");
					}else{
						sosysno=OrderIds;
					}
					//插入购汇单
					HashMap  kjtrecallmap = new HashMap();
					kjtrecallmap.put("purchasing", FEPBillId);
					kjtrecallmap.put("sosysno", sosysno);
					kjtrecallService.updatekjtpur(kjtrecallmap);
				} catch (Exception e) {
					 continue;
				}
				
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		startUpdate();
	}
	
	
}
