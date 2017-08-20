<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.common.Constants" %>
<% 
	String user_name = "",cust_type="",cust_id="",cust_logo="";
	if(session.getAttribute(Constants.USER_NAME)!=null){
		user_name = session.getAttribute(Constants.USER_NAME).toString();
	}
	if(session.getAttribute(Constants.CUST_TYPE)!=null){
		cust_type = session.getAttribute(Constants.CUST_TYPE).toString();
	}
	if(session.getAttribute(Constants.CUST_ID)!=null){
		cust_id = session.getAttribute(Constants.CUST_ID).toString();
	}
	if(session.getAttribute(Constants.CUST_LOGO)!=null){
		cust_logo = session.getAttribute(Constants.CUST_LOGO).toString();
	}
	String index_url = "",logout_url="",no_login_img="/malltemplate/bmall/images/defaultHead.gif";
	
	if(cust_type.equals(Constants.MEMBER_TYPE))
	{
		index_url = "/bmall-accountCenter.action";
		logout_url="/bmall_Memberuser_exit.action";
	}
	else if(cust_type.equals(Constants.ADMIN_TYPE))
	{
		index_url = "/adminindex.action";
		logout_url="/admin_Sysuser_logout.action";
	}
	if(cust_logo==null||cust_logo.equals("")){
	cust_logo=no_login_img;
	}
	
	if(!user_name.equals("")&&!cust_id.equals("")){
%>
 		document.write(' <div class="login"><div class="head"><img src="<%=cust_logo%>"></div><div class="loginCont"><p><b style="color:red;font-size:16px;"><%=user_name %></b></p><p><b>您好，欢迎来到EPF-020</b></p><p><a href="<%=logout_url%>"  style="margin-left:105px;">退出登录</a></p> </div></div>');
<%	
	}else{
%>
 document.write('<div class="login"><div class="head"><img src="<%=no_login_img%>"></div><div class="loginCont"><p><b>您好 ！</b></p> <p><b>欢迎来到EPF-020</b></p><p>您还未登陆哦</p><p><a href="/login.html">登陆</a><a href="/mall/member!joinus.action">注册</a></p></div></div>');
<%
	}
%>