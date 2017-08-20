<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.rbt.function.*"%>
<%@page import="com.rbt.function.SysconfigFuc"%>	
<%@ page import="com.rbt.common.Constants" %>		
<%
	String cust_id = "";
	int days = 0;
	if(session.getAttribute(Constants.CUST_ID)!=null){
		cust_id = session.getAttribute(Constants.CUST_ID).toString();
	}
	String daily_integral = "",continue_day = "",max_integral = "";
	daily_integral = SysconfigFuc.getSysValue("cfg_daily_integral");
	continue_day = SysconfigFuc.getSysValue("cfg_continue_day");
	max_integral = SysconfigFuc.getSysValue("cfg_max_integral");

%>
	var html = "<p class='gainsm'>连续领取每天增加<%=daily_integral%>  连续<%=continue_day%>天以上每天<%=max_integral%>如中断领取，又会从<%=daily_integral%>开始</p>"
	+"<p class='gainbut'><a href='#' onclick='getIntegral()'><img src='/malltemplate/bmall/images/hqjfbut_03.gif'></a></p>";
	document.getElementById("getIntegral").innerHTML = html; 
				
	//每日领取积分
	function getIntegral(){
		$.ajax({  	 
	          type: "post",    	     
	          url: "/interhistory!getIntegral.action",    
	          datatype:"json",
	          async:false,
	          success: function(data){
	          	if(data == "a"){
	          		alert("请先登录!"); 
	          	}else if(data == "b"){
	          		alert("今日已领取!"); 
	          	}else if(data == "c"){
	          		alert("领取失败!"); 
	          	}else{
	          		alert("恭喜您!今日领取"+data+"积分"); 
	          	}
	          }
		 });
	}

