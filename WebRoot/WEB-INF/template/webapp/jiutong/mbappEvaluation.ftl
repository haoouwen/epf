<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${cfg_webname?if_exists}—我要评价</title>

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
<@s.form action="/webapp/goods!auditList.action" method="post" id="indexForm">

<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>我要评价 <span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<!--订单不为空-->
<div class="hasOrder">
    <#assign num=0>
    <#assign goods_id="">
    <#assign list_img="">
    <#assign goods_name="">
    <#assign goodsdetailurl="">
    <#list goodsevalList as goodseval>
     <#assign num=num+1>
   <#assign trade_id=stack.findValue("@com.rbt.function.GoodsevalFuc@getTradeId('${(goodseval.goods_id)?if_exists}','${goodseval.order_id?if_exists}')")>    
   <#if trade_id?if_exists=="">
  <header class="hoDiv">
    <table>
      <tr>
        <td><div class="imgDiv">
         <#if goodseval.list_img!=''>
  			<a href="/webapp/goodsdetail/${goodseval.goods_id?if_exists}.html" target="_blank"><img src="${(goodseval.list_img)?if_exists}"  ></a>
  		 <#else>
  			<a href="/webapp/goodsdetail/${goodseval.goods_id?if_exists}.html" target="_blank"><img src="${(cfg_nopic)?if_exists}"  ></a>
  		 </#if>
        </div></td>
        <td>
        
           <p><a href="/webapp/goodsdetail/${goodseval.goods_id?if_exists}.html">
           <#if goodseval.goods_name?if_exists!="">${goodseval.goods_name?if_exists}<#else>-</#if>
           </a></p>
           <p>实付款：<b>￥${goodseval.goods_price?if_exists}</b></p>
           <p>数量：${goodseval.order_num?if_exists}</p>
        </td>
      </tr>
    </table>
    <div class="evaluation">
       <table width="100%" cellpadding="0" cellspacing="0">
         <tr><th>评分：</th><td>
         <input type="radio" name="eval${num?if_exists}" value="1">好评
         <input type="radio" name="eval${num?if_exists}" value="0">中评
         <input type="radio" name="eval${num?if_exists}" value="-1">差评
         </td></tr>
         <tr><th>描述：</th><td>
         <textarea class="evArea" id="comment${num?if_exists}" onkeyup="checkLength(this,200);"></textarea>
         </td></tr>
       </table>
       <p class="evP"><a href="###" class="evBut" onclick="submintEval('${num?if_exists}','${goodseval.goods_id?if_exists}','${goodseval.order_id?if_exists}')">发表评论</a></p>
    </div>
    </#if>
  </header>
   </#list>  
  
</div>
 <@s.hidden name="order_id_s" />
</@s.form>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
</body>
<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/mbMember.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/goodseval.js" type="text/javascript"></script>
</html>
