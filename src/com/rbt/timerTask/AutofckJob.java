package com.rbt.timerTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.rbt.common.util.DbUtil;
import com.rbt.function.BatchListFuc;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.service.IAutofckService;
/**
 * @Method Description :定时删除autofck 7天前 的数据
 * @author : HZX
 * @date : Apr 8, 2014 10:31:52 AM
 */
public class AutofckJob extends CreateSpringContext implements Job {
	//获取对象
    IAutofckService autofckService=(IAutofckService)getContext().getBean("autofckService");
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {   
				String istosend = "";// 获取系统配置是否启用更新的值
				istosend = SysconfigFuc.getSysValue("cfg_Isdelautofck");
				// 根据系统配置表取值，如果cfg_Isdelautofck的值为0：表示启用；1：不启用
				if (istosend.equals("0")) {
					delAutofck();// 执行删除7天前数据方法
				}
				
		} catch (Exception e) {
			System.err.println("定时删除autofck 7天前 的数据出现异常情况");
			e.printStackTrace();
		}
		
	}
	private void delAutofck(){
		//获取所有符合条件的Autofck数据条数，并分批处理
		 Map cMap = new HashMap();
		 cMap.put("delin_date","1");
		 int count=autofckService.getCount(cMap);
		 List clist=BatchListFuc.batchList(count);
			for(int i=0;i<clist.size();i++){
				Map stMap=(Map)clist.get(i);
				Map auMap=new HashMap();
				auMap.put("delin_date","1");
				auMap.put("start", 0);
			    auMap.put("limit",SysconfigFuc.getSysValue("cfg_define_row"));
			  //获取所有符合条件的Autofck数据
			    List au_idList = autofckService.getList(auMap);
				if(au_idList!=null||au_idList.size()>0){
					Map map=new HashMap();
					for (int j = 0; j <au_idList.size(); j++) {
						//取出autofck 的id
						map=(Map)au_idList.get(j);
						String id=map.get("id").toString();
						System.out.println("=============================delete:"+id);
						this.autofckService.delete(id);
					}
				}
			}
		
	} 
	public static void main(String[]args){
		new AutofckJob().delAutofck();
	}

}
