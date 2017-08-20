package com.rbt.timerTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.rbt.function.BatchListFuc;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.service.IMsgcheckService;
/**
 * @Method Description :定时删除msgcheck 前一天mark_id 为空的数据
 * @author : HZX
 * @date : Apr 8, 2014 10:31:52 AM
 */
public class MsgcheckJob extends CreateSpringContext implements Job {
	//获取对象
    IMsgcheckService msgcheckService=(IMsgcheckService)getContext().getBean("msgcheckService");
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {   
				String istosend = "";// 获取系统配置是否启用更新的值
				istosend = SysconfigFuc.getSysValue("cfg_IsdelMsgcheck");
				// 根据系统配置表取值，如果cfg_IsdelMsgcheck的值为0：表示启用；1：不启用
				if (istosend.equals("0")) {
					delMsgcheck();// 执行删除前一天数据方法
				}
				
		} catch (Exception e) {
			System.err.println("定时删除msgcheck 前一天mark_id 为空的数据出现异常情况");
			e.printStackTrace();
		}
		
	}
	private void delMsgcheck(){
		//获取所有符合条件的msgcheck数据条数，并分批处理
		 Map cMap = new HashMap();
		 cMap.put("delmark_id","null");
		 cMap.put("delsend_time","1");
		 int count=msgcheckService.getCount(cMap);
		 List clist=BatchListFuc.batchList(count);
			for(int i=0;i<clist.size();i++){
				Map stMap=(Map)clist.get(i);
				Map msgMap=new HashMap();
				msgMap.put("delmark_id","null");
				msgMap.put("delsend_time","1");
				msgMap.put("start", 0);
			    msgMap.put("limit", SysconfigFuc.getSysValue("cfg_define_row"));
			    //获取所有符合条件的msgcheck数据
			    List msg_idList = msgcheckService.getList(msgMap);
				if(msg_idList!=null||msg_idList.size()>0){
					Map map=new HashMap();
					for (int j = 0; j <msg_idList.size(); j++) {
						//取出msgcheck 的id
						map=(Map)msg_idList.get(j);
						String id=map.get("id").toString();
						System.out.println(clist.size()+"======="+i+"====="+stMap.get("start").toString()+"========"+stMap.get("limit".toString())+"========delete:"+id);
						this.msgcheckService.deleteById(id);
					}
				}
			}
		
	} 
	public static void main(String[]args){
		new MsgcheckJob().delMsgcheck();
	}

}
