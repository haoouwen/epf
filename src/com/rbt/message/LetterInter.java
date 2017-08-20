/*
  
 
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.message;

import java.util.HashMap;
import java.util.List;

import com.rbt.function.CreateSpringContext;
import com.rbt.model.Advpos;
import com.rbt.model.Member;
import com.rbt.model.Receivebox;
import com.rbt.model.Sendbox;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IAdvposService;
import com.rbt.service.IMemberService;
import com.rbt.service.IReceiveboxService;
import com.rbt.service.ISendboxService;

/**
 * @author : LJQ
 * @date : Mar 11, 2014 10:19:26 AM
 * @Method Description : 发送站内信的方法
 */
public class LetterInter extends CreateSpringContext{

	//根据广告位标识找出该广告位下的广告
	@SuppressWarnings("unchecked")
	public static void sendLetter(String send_cust_id,String get_cust_id,String title,String content){
		ISendboxService sendboxService = (ISendboxService)getContext().getBean("sendboxService");
		IReceiveboxService receiveboxService = (IReceiveboxService)getContext().getBean("receiveboxService");
		IMemberService memberService = (IMemberService)getContext().getBean("memberService");
		Member member = memberService.get(get_cust_id);
		String recevie_name ="";
		if(member!=null){
			recevie_name = member.getCust_name();
		}
		//发件箱数据的处理
		Sendbox sendbox = new Sendbox();
		sendbox.setSend_cust_id(send_cust_id);
		sendbox.setTitle(title);
		sendbox.setContent(content);
		sendbox.setIs_send_del("1");
		sendbox.setRecevie_name(recevie_name);
		String send_id = sendboxService.insertGetPk(sendbox);
		//收件箱数据的赋值
		Receivebox receivebox = new Receivebox();
		receivebox.setGet_cust_id(get_cust_id);
		receivebox.setIs_get_del("1");
		receivebox.setIs_read("1");
		receivebox.setSend_id(send_id);
		receiveboxService.insert(receivebox);
	}
}