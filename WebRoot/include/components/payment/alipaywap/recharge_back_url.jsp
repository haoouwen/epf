<%
/* *
 修改者：QJY
 功能：支付宝页面跳转同步通知页面
 版本：3.2
 日期：2011-03-17
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 该页面可在本机电脑测试
 可放入HTML等美化页面的代码、商户业务逻辑程序代码
 TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
 TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
 //********************************
 * */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.rbt.pay.alipaywap.util.*"%>
<%@ page import="com.rbt.function.PaymentFuc"%>
<%@ page import="com.rbt.function.FundrechargeFuc"%>
<%@ page import="com.rbt.function.SysconfigFuc"%>
<!doctype html>

<%
	//获取支付宝GET过来反馈信息
	Map<String,String> params = new HashMap<String,String>();
	Map requestParams = request.getParameterMap();
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
		params.put(name, valueStr);
	}

	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
	//商户订单号

	String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

	//支付宝交易号
	String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

	//交易状态
	String result = new String(request.getParameter("result").getBytes("ISO-8859-1"),"UTF-8");

	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
	
	//支付类型：支付宝手机支付
	String pay_id = PaymentFuc.getPaymentID("alipaywap");
	
	String callbackTip = "";
	
	//计算得出通知验证结果
	boolean verify_result = AlipayNotify.verifyReturn(params);
	
	if(verify_result){//验证成功
		//////////////////////////////////////////////////////////////////////////////////////////
		//请在这里加上商户的业务逻辑程序代码

		//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
		
			//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
		        //FundrechargeFuc.mobileRecharge(pay_id,out_trade_no,trade_no);
			    

		//该页面可做页面美工编辑
		//out.println("支付成功<br />");
		//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
        callbackTip = "充值成功";
		//////////////////////////////////////////////////////////////////////////////////////////
	}else{
		//该页面可做页面美工编辑
		//out.println("验证失败");
		callbackTip = "充值失败";
	}
%>

<html>
  <head>

		<title>支付宝页面跳转同步通知页面</title>
		<meta name="author" content="EPF-020信息技术有限公司">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="Expires" content="-1">           
		<meta http-equiv="Cache-Control" content="no-cache">           
		<meta http-equiv="Pragma" content="no-cache">
		<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"></link>
        <link href="/malltemplate/jiutong/css/mbRegister.css" rel="stylesheet" type="text/css"></link>
  </head>
  
  <body>
<div class="top">
      充值结果
</div>

<!--完成-->
<div class="registerDiv">
    <div class="sucRegister">
       <p><b><%=callbackTip %></b></p>
       <p>充值订单号:<%=out_trade_no %></p>
       <!--<p>充值金额：<b></b></p>  -->
      <p><a href="/mindex.html">首页</a>&nbsp;&nbsp;<a href="/webapp/memberuser!mbGoldCoins.action">查看充值</a></p>
    </div>
</div>

</body>

</html>