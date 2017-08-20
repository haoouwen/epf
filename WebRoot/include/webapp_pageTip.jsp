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
<link href="/wro/webapp_index.css" rel="stylesheet" type="text/css"/>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
<script src="/wro/webapp_index.js" type="text/javascript"></script>
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
          window.location.href="/mindex.html";
        }
    };
    
</script>
<style type="text/css">
 .ngCont span{color:#f60000;}
 .ngCont a{color:#f60000;}
</style>
</head>

<body>

<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>信息提示<span class="topCls"></span>
</div>
<!--内容-->
<div class="noFind"><img src="/malltemplate/bmall/images/noFind.jpg"><b>商品已下架或者不存在</b>
 <p class="ngCont">该页面在<span id="sec">10</span>秒后自动跳转&nbsp;&nbsp;您可以点击<a href="http://m.epff.cc/mindex.html">[返回首页]</a> </p></div>
<!--尾部-->
<div class="clear"></div>
 
<jsp:include page="/a/webapp/mbCommon.html"/>

</body>

</html>


