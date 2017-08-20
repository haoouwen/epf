/*
  
 
 * Package:com.timerTask
 * FileName: UpdateChannelJobAction.java 
 */
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
import com.rbt.function.ToolsFuc;
import com.rbt.message.RtxmsgInter;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IMsgcheckService;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.BatchListFuc;

;

/**
 * @author : LJQ
 * @date : Oct 9, 2014 2:25:59 PM
 * @Method Description : 每天晚上十二点开始检测当天是否为会员生日
 */
public class birthdayJob extends CreateSpringContext implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			String istosend = "";// 获取系统配置是否启用更新的值
			istosend = SysconfigFuc.getSysValue("cfg_Issendbirthemail");
			// 根据系统配置表取值，如果cfg_qtzUpdateChannel的值为0：表示启用；1：不启用
			if (istosend.equals("0")) {
				sendBless();// 执行发送生日祝福方法
			}
		} catch (Exception e) {
			System.err.println("定时发送生日祝福出现异常情况");
			e.printStackTrace();
		}
	}

	/**
	 * @author : LJQ
	 * @throws UnsupportedEncodingException 
	 * @date : Oct 9, 2014 2:31:35 PM
	 * @Method Description : 查找当天生日的会员列表
	 */	
	private static void sendBless() throws UnsupportedEncodingException{
		RtxmsgInter client=new RtxmsgInter();
		//获取短信模板内容
		
		//IEmailtemplateService emailtemplateService = (IEmailtemplateService) getContext().getBean("emailtemplateService");
		//Emailtemplate ep = emailtemplateService.getEmailtemplateByTempcode("birthday");
		//String birthdayTemplate=ep.getTemp_con();
		String birthdayTemplate="";
		//发送短信待做
		IMemberuserService memberuserService = (IMemberuserService) getContext().getBean("memberuserService");
		Map userMap =new HashMap();
		userMap.put("sendbirthday","1");
		int count=memberuserService.getCount(userMap);
		List clist=BatchListFuc.batchList(count);
		for(int i=0;i<clist.size();i++){
			Map stMap=(Map)clist.get(i);
		      Map meMap = new HashMap();
		      meMap.put("sendbirthday","1");
		      meMap.put("start", 0);
		      meMap.put("limit", SysconfigFuc.getSysValue("cfg_define_row"));
		      List list = memberuserService.getList(meMap);
				//循环发送信息
				for(int j=0;j<list.size();j++){
					Map listMap=(HashMap)list.get(j);
					if(listMap.get("cust_id")!=null && !"".equals(listMap.get("cust_id").toString())){
						String cust_id=listMap.get("cust_id").toString();
						    //信息提醒
							MessageAltFuc mesalt=new MessageAltFuc();
							mesalt.messageAutoSend("11",cust_id);
						}
				}
		}
	}

	
	/**
	 * @author : LJQ
	 * @date : Oct 11, 2014 10:15:07 AM
	 * @Method Description : 删除记录短信验证码表信息
	 */
	public static void delMsgCheck(){
		IMsgcheckService msgcheckService = (IMsgcheckService) getContext().getBean("msgcheckService");
		msgcheckService.delete("");
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		sendBless();
		delMsgCheck();
	}
	
	
}
