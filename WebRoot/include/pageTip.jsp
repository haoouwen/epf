<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.rbt.function.* "%>
<%
	//页面编码
	String page_code = "";
	if(request.getParameter("page_code")!=null){
		page_code = request.getParameter("page_code");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>系统提示-<%=PageTipFuc.getPageTitle(page_code) %></title>
<script src="/wro/index.js" type="text/javascript"></script>
<link href="/wro/index.css" rel="stylesheet" type="text/css"/>
<script>
    
    var timerc=11; //全局时间变量（秒数）
    minu();
    function minu(){ //减时函数
        if(timerc < 12){ //如果不到8秒钟
            --timerc; //时间变量自增1
            $("#sec").html(Number(parseInt(timerc)).toString()); //写入秒数
            setTimeout("minu()", 900); //设置1000毫秒以后执行一次本函数
        };
        if(timerc==0){
          window.location.href="/";
        }
    };
    
</script>
<style type="text/css">
 .ngCont span{color:#f60000;}
 .ngCont a{color:#f60000;}
</style>
</head>

<body>

<!--顶部-->
 <!--顶部-->
<jsp:include page="/a/bmall/malltop.html"/> 
<!--内容-->
<div class="noFind"><img src="/malltemplate/bmall/images/noFind.jpg"><b><%=PageTipFuc.getPageCon(page_code) %></b>
 <p class="ngCont">该页面在<span id="sec">10</span>秒后自动跳转&nbsp;&nbsp;您可以点击<a href="/">[返回首页]</a> </p></div>
<!--尾部-->
<div class="clear"></div>
<jsp:include page="/a/bmall/mallbottom.html"/> 

</body>

</html>


