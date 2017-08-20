<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.common.Constants" %>
<%@page import="com.rbt.function.MemberuserFuc"%>
<% 
	String user_name = "",cust_type="",cust_id="",menu_right="",msg_num="0";
	if(session.getAttribute("openid")!=null && session.getAttribute("nickName")!=null ){
		user_name = session.getAttribute("nickName").toString();
	}else if(session.getAttribute("weixinName")!=null){
		user_name = session.getAttribute("weixinName").toString();
	}else if(session.getAttribute(Constants.USER_NAME)!=null){
		user_name = session.getAttribute(Constants.USER_NAME).toString();
	}
	if(session.getAttribute(Constants.CUST_TYPE)!=null){
		cust_type = session.getAttribute(Constants.CUST_TYPE).toString();
	}
	if(session.getAttribute(Constants.CUST_ID)!=null){
		cust_id = session.getAttribute(Constants.CUST_ID).toString();
	}
	if(session.getAttribute("menu_right")!=null){
		menu_right = session.getAttribute("menu_right").toString();
	}
	String index_url = "",logout_url="";
	
	if(cust_type.equals(Constants.MEMBER_TYPE))
	{
		index_url=MemberuserFuc.logoinIndex(menu_right);
		logout_url="/bmall_Memberuser_exit.action";
	}
	else if(cust_type.equals(Constants.ADMIN_TYPE))
	{
		index_url = "/adminindex.action";
		logout_url="/admin_Sysuser_logout.action";
	}
	else if(cust_type.equals(Constants.AGENT_TYPE))
	{
		index_url = "/agentindex.action";
		logout_url="/agent_Asysuser_logout.action";
	}
	if(!user_name.equals("")&&!cust_id.equals("")&&session.getAttribute("nickName")!=null){
%>
	document.write('<img src="/malltemplate/bmall/images/qq_logo.png"/><a href="<%=index_url%>"><strong><%=user_name %></strong></a><a href="/bmall_Receivebox_list.action?parentMenuId=7464473768&amp;selli=1352543767" target="_self">&nbsp;<img src="/include/bmember/images/wdyj.gif" width="12px" />(<em class="msg_num_span"><%=msg_num %></em>)</a><a href="<%=logout_url%>">&nbsp;[退出登录]</a>');
<%
    }else if(!user_name.equals("")&&!cust_id.equals("")&&session.getAttribute("weixinName")!=null){
%>
	document.write('<img src="/malltemplate/bmall/images/weixinlogin.png"/><a href="<%=index_url%>"><strong><%=user_name %></strong></a><a href="/bmall_Receivebox_list.action?parentMenuId=7464473768&amp;selli=1352543767" target="_self">&nbsp;<img src="/include/bmember/images/wdyj.gif" width="12px" />(<em class="msg_num_span"><%=msg_num %></em>)</a><a href="<%=logout_url%>">&nbsp;[退出登录]</a>');
<%    		
	}else if(!user_name.equals("")&&!cust_id.equals("")){
%>
	document.write('<a href="<%=index_url%>"><strong><%=user_name %></strong></a><a href="/bmall_Receivebox_list.action?parentMenuId=7464473768&amp;selli=1352543767" target="_self">&nbsp;<img src="/include/bmember/images/wdyj.gif" width="12px" />(<em class="msg_num_span"><%=msg_num %></em>)</a><a href="<%=logout_url%>">&nbsp;&nbsp;[退出登录]</a>');
<%	
	}else{
	    String  reqUrl  =  request.getHeader("Referer");  
	    if(!reqUrl.contains("/login.html")){
	    %>
			document.write('<a href="/login.html?loc=<%=reqUrl %>">登录</a> <a class="zhuce" href="/mall/member!joinus.action">免费注册</a>');
		<%
	    }else {
	     %>
			document.write('<a href="/login.html">登录</a> <a href="/mall/member!joinus.action" class="zhuce">免费注册</a>');
		 <%
	    }
	}
%>