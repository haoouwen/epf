<%
 /**
 *
 *作者：QJY 
 *功能：中国银联页面跳转同步通知页面
 *版本：3.2
 *日期：2015-09-29
 *说明：
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@ page import="com.rbt.pay.unionpay.util.SDKConstants"%>
<%@ page import="com.rbt.pay.unionpay.util.SDKUtil"%>
<%@page import="com.rbt.pay.unionpay.util.UnionpaySubmit"%>
<%@ page import="com.rbt.function.SysconfigFuc"%>
<%@ page import="com.rbt.function.GoodsOrderFuc"%>
<%@ page import="com.rbt.function.PaymentFuc"%>
<%
	    //System.out.println("FrontRcvResponse前台接收报文返回开始");

		request.setCharacterEncoding("UTF-8");
		String encoding = request.getParameter(SDKConstants.param_encoding);
		Map<String, String> respParam = UnionpaySubmit.getAllRequestParam(request);

		Map<String, String> valideData = null;
		//StringBuffer page = new StringBuffer();
		if (null != respParam && !respParam.isEmpty()) {
			Iterator<Entry<String, String>> it = respParam.entrySet()
					.iterator();
			valideData = new HashMap<String, String>(respParam.size());
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = (String) e.getKey();
				String value = (String) e.getValue();
				value = new String(value.getBytes("UTF-8"), encoding);
				valideData.put(key, value);
			}
		}
		String order_id = "",total_amount="",queryId="",pay_id="",reqReserved="",callbackTip="",cfg_mobiledomain="";
		if (!SDKUtil.validate(valideData, encoding)) {//校验签名
		    //TODO:
			System.out.println("验证签名结果[失败].");
		} else {
		    order_id = valideData.get("orderId"); //其他字段也可用类似方式获取
		    total_amount= valideData.get("txnAmt");//以分为单位
		    total_amount = String.valueOf(Double.valueOf(total_amount)/100);//转化成元为单位
		    
		    queryId = valideData.get("queryId"); //查询交易流水号
	        pay_id = PaymentFuc.getPaymentID("unionpay"); //支付类型：银联在线
	        cfg_mobiledomain = SysconfigFuc.getSysValue("cfg_mobiledomain"); 
		    reqReserved = valideData.get("reqReserved");//
		    
		    String remark2s[]=reqReserved.split("\\_");
			String pay_type=remark2s[1].toString();
		    if(pay_type.equals("1")){//订单付款
		      if(GoodsOrderFuc.verifyOrderState(order_id)){
			      GoodsOrderFuc.useOnlinePay(order_id,total_amount,queryId,pay_id,remark2s[0].toString());
			  }
		    }
		    StringBuffer message = new StringBuffer();
            message.append("订单号为：" + order_id + " 的订单支付成功，"); 
            message.append("付款金额为" + total_amount + "元。<br/>");
	        callbackTip  = message.toString();

		}
		
		//System.out.println("前台接收报文返回结束");
%>
<!doctype html>

<html>
  <head>

		<title>银联在线支付跳转同步通知页面</title>
		<meta name="author" content="久通宏达科贸（北京）有限公司">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="Expires" content="-1">           
		<meta http-equiv="Cache-Control" content="no-cache">           
		<meta http-equiv="Pragma" content="no-cache">
		<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"/>
        <link href="/malltemplate/jiutong/css/mbRegister.css" rel="stylesheet" type="text/css"></link>
  </head>
  
  <body>
  <div class="top">
  
  支付成功页面 
  
  </div>

<!--完成-->
<div class="registerDiv">
    <div class="sucRegister">
       <p><b><%=callbackTip %></b></p>
       <p>订单号：</p>
       <p><b><%=order_id %></b></p>
       <p>金额：<b><%=total_amount %></b></p>
      <p><a href="/mindex.html">返回首页</a>&nbsp;&nbsp;<a href="<%=cfg_mobiledomain %>/webapp/goodsorder!webappOrderDetail.action?goodsorder.order_id=<%=order_id %>&order_state=2">查看订单</a></p>
    </div>
</div>

<jsp:include page="/a/webapp/mbFooter.html"></jsp:include>
<jsp:include page="/a/webapp/mbCommon.html"></jsp:include>

</body>

</html>