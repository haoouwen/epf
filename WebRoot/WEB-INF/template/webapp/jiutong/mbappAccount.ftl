<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—账户中心</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/wro/webapp_common.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/jiutong/css/mbMember.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>


<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>账户中心 <span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">

<!--商品分类-->
<div class="account">
    <ul>
      <li><a href="/webapp/memberuser!goUpPass.action">修改密码</a></li>
      <!--判断该用户手机是否验证-->
      <#if memberuser?if_exists && (memberuser.is_check_mobile)?if_exists=="0">
         <li><a href="/webapp/memberuser!webappModifyPhoneFirst.action">修改手机验证</a></li>
      <#else>
           <li><a href="/webapp/memberuser!webappCheckMobile.action">手机验证</a></li>
      </#if>

      <!--<li><a href="#">修改支付密码</a></li>-->
      <li><a href="/webapp/order!addressList.action">收货地址</a></li>
      
     <li><a href="/webapp/memberRecharge.html">在线充值</a></li>
      
      <li><a href="/webapp/membercardRecharge.html">充值卡充值</a></li>
       
      <li><a href="/webapp/comsumer!exCoupon.action">兑换优惠券</a></li>
      
      <li><a href="/webapp/redsumer!exRedbag.action">兑换红包</a></li>
      
      <li><a href="/webapp/memberuser!mbGoldCoins.action">我的余额<b>(￥${total_balance?if_exists})</b><span>充值记录</span></a></li>
      
      <li>我的积分:<b>${total_integral?if_exists}</b>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成长值:<b>${(member.growthvalue)?if_exists}</b></li>
      
      <li>会员等级<b><img src="${level_url?if_exists}"/></b></li>
      
      
    </ul>
</div>
<div class="exit"><a href="/webappexit.html">退出登录</a></div>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">

</body>
<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/mbMember.js" type="text/javascript"></script>
</html>
