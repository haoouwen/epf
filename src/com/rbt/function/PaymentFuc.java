/*
  
 
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.model.Payment;
import com.rbt.service.IPaymentService;

/**
 * @function 功能  获取支付宝账户信息
 * @author  创建人 CYC
 * @date  创建日期  2014-11-10
 */
public class PaymentFuc extends CreateSpringContext{
	@SuppressWarnings("unchecked")
	public static List paymentlist;
	
	//根据支付方式的编码获账户的相关信息
	public static Payment getPayment(String pay_code){
		//修改用户账户信息
		IPaymentService paymentService = (IPaymentService)getContext().getBean("paymentService");
		String pay_id="";
		paymentlist=null;
		Payment payment=new Payment();
		HashMap map=new HashMap();
		map.put("pay_code", pay_code);
		paymentlist=paymentService.getList(map);
		if(paymentlist != null && paymentlist.size()>0){
			map=(HashMap)paymentlist.get(0);
			payment.setSeller_name(map.get("seller_name").toString());
			payment.setPay_account(map.get("pay_account").toString());
			payment.setPasswd(map.get("passwd").toString());
			payment.setPay_id(map.get("pay_id").toString());
			payment.setAppID(map.get("appID").toString());
			payment.setAppSecret(map.get("appSecret").toString());
		}
		return payment;
		//修改余额修改记录
	}
	
	//根据支付方式的编码获账户的相关信息
	public static String getPaymentID(String pay_code){
		//修改用户账户信息
		IPaymentService paymentService = (IPaymentService)getContext().getBean("paymentService");
		paymentlist=null;
		Payment payment=new Payment();
		HashMap map=new HashMap();
		map.put("pay_code", pay_code);
		paymentlist=paymentService.getList(map);
		if(paymentlist != null && paymentlist.size()>0){
			map=(HashMap)paymentlist.get(0);
			payment.setPay_id(map.get("pay_id").toString());
		}
		return payment.getPay_id();
	}
	
	//获取所有支付集合
	public static List getPayList(){
		//修改用户账户信息
		IPaymentService paymentService = (IPaymentService)getContext().getBean("paymentService");
		Map<String,String> pageMap = new HashMap<String,String>();
		//标记信息启用的信息
		pageMap.put("enabled", "0");
		//找出相应的系统参数类型的列表
		return paymentService.getList(pageMap);
	}
	
}
