<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}-收货地址</title>

<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/wro/webapp_common.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/jiutong/css/mbShopProcess.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>收货地址 <span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<!--收货地址-->
<div class="address">

    <div class="addrh2">收货地址</div>
    <!--ajax动态添加收货地址-->
    <div  id="addrContent"></div>
    
    <!---->
    <div class="addAddrBut" onclick="addnewaddress();">增加收货地址</div>
    
</div>

<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">


<!--增加地址-->
<div class="addAddress">

   <!---->
  <div class="addh2">增加收货地址</div>
  <!---->
  <div class="addTable">
    <table cellpadding="0" cellspacing="0">
     <tr><th>收货人：</th><td><input type="text" class="addText"id="consignee" name="consignee" maxLength="10"></td></tr>
     <tr><th>地区：</th><td><div id="areaDiv"></div> </td></tr>
     <tr><th>详细地址：</th><td><input type="text" class="addText" id="address"  name="address" maxLength="50"></td></tr>
     <tr><th>手机号码：</th><td><input type="text" class="addText" id="cell_phone" name="cell_phone" onkeyup="checkDigital(this)" maxLength="11"></td></tr>
     <tr><th>联系电话：</th><td><input type="text" class="addText" id="phone" name="phone"  maxLength="12"></td></tr>
     <tr><th>身份证：</th><td><input type="text" class="addText" id="identitycard" name="identitycard"  maxLength="18"></td></tr>
    </table>
  </div>   
  <!---->
  <div class="addBut" onclick="addAddr()">确定</div>
  
</div>
<div class="addrClose"><img src="/malltemplate/jiutong/images/filClose.gif"></div>


<@s.hidden id="addr_id" name="addr_id"/>
<input type="hidden" name="end_area_attr" id="end_area_attr" value="7"/>
</body>

<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/order.js" type="text/javascript"></script>
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
<script src="/malltemplate/jiutong/js/shopProcess.js" type="text/javascript"></script>
<script type="text/javascript">
	//初始化加载
	$(document).ready(function(){
		//所属地区的回选
		loadArea("${areaIdStr?if_exists}","");
	 });
</script>
</html>
