<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>EPF-020_商城提示</title>
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
.noGoods{width:100%;background:#f9f9f9;margin-top:10px;}
.noGoods .ngCont{width:800px;height:270px;margin:0 auto;border-top:#dddddd solid 1px;padding:40px 100px;position:relative;}
.noGoods .ngCont p{position:absolute;left:275px;bottom:75px;color:#3d3d3d;}
.noGoods .ngCont span{color:#f60000;}
.noGoods .ngCont a{color:#f60000;}
</style>
</head>

<body>

<!--顶部-->
 <!--顶部-->
<jsp:include page="/a/bmall/malltop.html"/> 
<!--内容-->
<div class="noGoods">
   <div class="ngCont">
      <img src="/include/common/images/ngBack.jpg"/>
      <p>该页面在<span id="sec">10</span>秒后自动跳转&nbsp;&nbsp;您可以点击<a href="/">[返回首页]</a> </p>
   </div>
</div>
<!--尾部-->
<div class="clear"></div>
<jsp:include page="/a/bmall/mallbottom.html"/> 

</body>

</html>
