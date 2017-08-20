<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>预售付款-${cfg_webname?if_exists}</title>
<#include "/WEB-INF/template/bmall/default/jscssinclude.ftl">
<link href="/malltemplate/bmall/css/payProcess.css" rel="stylesheet" type="text/css"/>
<script src="/malltemplate/bmall/js/payment.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/jqueryTab.js" type="text/javascript"></script>
<script src="/malltemplate/bmall/js/payProcess.js" type="text/javascript"></script>
<link href="/include/common/css/common.css" rel="stylesheet" type="text/css" />

</head>

<body>
<@s.form id="goConfirmPay" action="/mall/directOrder!useNumPay.action" method="post">
<!--支付参数隐藏域开始-->
<@s.hidden id="order_id_str" name="order_id_str"/>
<@s.hidden id="total_price" name="total_price"/>
<@s.hidden id="sub_total_price" name="sub_total_price"/>
<@s.hidden id="checked_num" name="checked_num"/>
<@s.hidden id="use_num" name="use_num" value="${(memberfund.use_num)?if_exists}"/>
<@s.hidden id="use_num_pay" name="use_num_pay" value="${total_amount?if_exists}" />
<@s.hidden  name="pa_MP" id="pa_MP" value="${session_cust_id?if_exists}-${session_user_id?if_exists}-1_1" />
 <@s.hidden id="total_amount" name="total_amount" value="${total_amount?if_exists}"/>
 <@s.hidden id="session_cust_id" name="session_cust_id" value="${session_cust_id?if_exists}"/>
 <@s.hidden id="order_pay_tip" name="order_pay_tip"/>
 <@s.hidden name="fund_flag" value="1"/>
<!--支付参数隐藏域结束-->
<!--头部--> 
<#include "/WEB-INF/template/bmall/default/topest.ftl">
<!--logo，搜索-->
<div class="twLogoArea">
   <!--logo-->
   <div class="logo"><a href="/"><img src="${cfg_logourl}"  width="246px" height="64px" /></a></div>
   <!--支付步骤-->
   <div class="successProcess"></div>
</div>
<!--内容-->
<div class="sucOrder">

   <div class="sucTips">为保障您账号及资金的安全：请您尽快启用支付密码，验证手机，验证邮箱， <a  href="###" onclick="linkToInfo('/bmall_Msgcheck_checkmobile.action','memberuser.cust_id=${memberfund.cust_id}');">立即启动>></a></div> 
   
   <div class="payMain">
       <h2><#if order_pay_tip=="1">请您及时付款，以便订单尽快处理!<#else>订单提交成功，请您尽快付款!</#if></h2>
       <p class="orderp"><span>订单号：
        <#list orderPayList as orderpay>
        	<input type="checkbox" onclick="selOrder(this)" id="order_${(orderpay.order_id)?if_exists}" name="order_id" value="${(orderpay.order_id)?if_exists}" style="display:none"/>
           <i class="iBlue">${(orderpay.order_id)?if_exists}</i>
         </#list>
    
       
       </span><span>应付金额：<b>${total_amount?if_exists}元</b></span></p>
       <p class="tipsp">请您在提交订单后<span>
       <#if directorderdetail.pay_state==1>
          ${cfg_yushouendpaytime?if_exists} 天
       <#else>
         <#if (cfg_publictimeout?if_exists?number>60)>${cfg_publictimeout?if_exists?number/60}小时
       <#else>${cfg_publictimeout?if_exists}分钟
       </#if>
       </#if>
       </span>内完成支付,否则订单自动取消</p>
          
       <div class="selPayStyle" id="selPayStyleId">
         
         <div class="payh2">
            <ul>
              <li class="selli">余额支付</li>
              <li>在线支付</li>
            </ul>
         </div>
        
         <div class="tabDiv"> 
            
            <div class="payCont"> 
               <div class="payDiv">
                 <p><b>账户</b>(${(buyMember.cust_name)?if_exists})<span>可支付余额：<b>${(memberfund.use_num)?if_exists?number}</b>元</span></p>
                 <p>使用账户余额支付<b class="redb">${total_amount?if_exists}</b>元<span>(如果您余额不足，请您<a href="/bmall_Fundrecharge_add.action">点击充值</a>)</span></p>
                <#if memberfund.use_num!='0'>
                 <p>密码：
                 <input id="pay_password" type="password" name="pay_password" class="pswText" align="absmiddle" align="absmiddle">
                 <span><a href="/mall/member!executepwd.action">忘记密码？</a></span>
                  <#if can_buy!=null>
	                        <span><font color="red">${can_buy?if_exists}</font></span>
	              </#if>
                  <@s.fielderror><@s.param>pay_password</@s.param></@s.fielderror>
                 </p>
                 </#if>
                 
               </div>
                <#if memberfund.use_num!='0'>
          			 <p><input type="button" class="payBut" onclick="subConfirmPay();"/></p>
                 </#if>
             </div>
             
             <!---->
            <div class="payCont"> 
               <div class="payDiv">
                <ul>
                	<#list paymentList as plist>
		                <#if plist.pay_code!='goldpay'>
		           				<li><div><input type="radio" name="bank" checked="true" onclick="onlineSubChan();"><img src="${plist.pay_logo}" width="200px" height="55px" align="absmiddle"/ ></div></li>
		           		</#if>
		           </#list>
                 </ul><br class="clear"/>
               </div>
               <p><input type="button" class="payBut" onclick="yeepaySubmit();"></p>
             </div>    
         </div>    
      </div> 
             
   </div>
   
   <div class="orderTips">完成支付后，您可以：<a href="/bmall-buyer.action">查看订单状态</a><a href="/">继续购物</a><!--<a href="#">问题反馈</a>--></div> 
   
</div>  

<!--尾部-->
  </div>
<#include "/a/bmall/mallbottom.html">
 <div style="display:none;"  id="setPay"  >
	        <table  style="text-align: center;">
	          <tr>
	           	<td style="color:#EA9359;padding-top:10px;"  ><img  src="/malltemplate/bmall/images/pay_tip.png"/>付款完成前请不要关闭此窗口, 完成付款后请点击下面按钮</td>
	          </tr>
	           <tr style="text-align: center;">
	           	<td style="padding-top:30px;">
	           	<a href="/bmall_Goodsorder_buyorderlist.action"><img src="/malltemplate/bmall/images/pay_success.gif"/></a>
	           	<a href="/mall/article!attrdetail.action?article.cat_attr=8547217448"><img src="/malltemplate/bmall/images/pay_fald.gif"/></a></td>
	          </tr>
	       </table>
	<div>
	
</@s.form>
</body>
<script src="/malltemplate/bmall/js/jqueryPopupLayer.js" type="text/javascript"></script>
</html>