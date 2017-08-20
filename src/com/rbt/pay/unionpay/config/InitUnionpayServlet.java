package com.rbt.pay.unionpay.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.rbt.pay.unionpay.util.SDKConfig;

/* *
 * 邱景岩
 *类名：UnionpayConfig
 *功能：银联在线支付属性问及那
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2015-09-28
 */


public class InitUnionpayServlet extends HttpServlet{
	
	
	public void init() throws ServletException {
		SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件
		//System.out.println("===================111111"+SDKConfig.getConfig().getSignCertPath());
		super.init();
	}
	
}
