<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbt.common.Constants" %>
<% 
	String user_name = "",cust_type="",cust_id="",menu_right="";
	if(session.getAttribute(Constants.USER_NAME)!=null){
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
		index_url="/webappmembercenter.html";
		logout_url="/webappexit.html";
	}
	else if(cust_type.equals(Constants.ADMIN_TYPE))
	{
		index_url = "/adminindex.action";
		logout_url="/admin_Sysuser_logout.action";
	}
	
	if(!user_name.equals("")&&!cust_id.equals("")){
%>
	document.write('<a href="<%=index_url%>"><%=user_name %></a><a href="<%=logout_url%>" onclick="exit();">&nbsp;退出</a>');
<%	
	}else{
	    String  reqUrl  =  request.getHeader("Referer");  
	    %>
			document.write('<a href="/webappregister.html">注册</a>&nbsp;|&nbsp;<a href="/webapplogin.html?loc=<%=reqUrl %>">登录</a>');
		<%
	}
%>