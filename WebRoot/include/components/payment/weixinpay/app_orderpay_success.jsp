<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.function.GoodsOrderFuc"%>
<%@ page import="com.rbt.function.SysconfigFuc"%>
<%@page import="com.rbt.model.Goodsorder"%>
<%
       
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		
		String cfg_mobiledomain = SysconfigFuc.getSysValue("cfg_mobiledomain");
	    String out_trade_no= request.getParameter("out_trade_no");
	    String total_amount = "";
	    if(out_trade_no!=null && out_trade_no!=""){
	       Goodsorder goodsorder = GoodsOrderFuc.getGoodsOrder(out_trade_no);
	       total_amount = String.valueOf(goodsorder.getTatal_amount());
	    }
%>

<!doctype html>

<html>
  <head>

		<title>微信app支付页面跳转同步通知页面</title>
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
  
  微信支付
  
  </div>

<!--完成-->
<div class="registerDiv">
    <div class="sucRegister">
       <p><b>付款成功</b></p>
       <p>订单号：</p>
       <p><b><%=out_trade_no %></b></p>
       <p>金额：<b><%=total_amount %></b></p>
      <p><a href="<%=cfg_mobiledomain %>/mindex.html">返回首页</a>&nbsp;&nbsp;<a href="<%=cfg_mobiledomain %>/webapp/goodsorder!webappOrderDetail.action?goodsorder.order_id=<%=out_trade_no %>&order_state=2">查看订单</a></p>
    </div>
</div>

<!--尾部-->
<jsp:include page="/a/webapp/mbFooter.html"></jsp:include>
<jsp:include page="/a/webapp/mbCommon.html"></jsp:include>

</body>
</html>