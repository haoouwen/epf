<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.common.Constants" %>
<% 
	String user_name = "",cust_id="";
	if(session.getAttribute("openid")!=null && session.getAttribute("nickName")!=null ){
		user_name = session.getAttribute("nickName").toString();
	}else if(session.getAttribute("weixinName")!=null){
		user_name = session.getAttribute("weixinName").toString();
	}else if(session.getAttribute(Constants.USER_NAME)!=null){
		user_name = session.getAttribute(Constants.USER_NAME).toString();
	}
	if(session.getAttribute(Constants.CUST_ID)!=null){
		cust_id = session.getAttribute(Constants.CUST_ID).toString();
	}
	if(!user_name.equals("")&&!cust_id.equals("")){
	}else{
	     %>
			document.write('<span>合作登录</span> <a href="/mall/memberuser!qqlogin.action" class="qqlogo" title="QQ" ></a> <a href="javascript:open_weixin()" class="weixinlogo" title="weixin"></a>');
		 <%
	}
%>