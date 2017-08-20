<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>订单付款-${cfg_webname?if_exists}</title>
<script src="/wro/bmall_pay.js" type="text/javascript"></script>
<link href="/wro/bmall_pay.css" rel="stylesheet" type="text/css"/>
<link href="/malltemplate/index/css/mall_public.css" rel="stylesheet" type="text/css" />
</head>
<body>
<@s.form id="goConfirmPay" action="/mall/order!useNumPay.action" method="post">
<@s.hidden id="order_id_str" name="order_id_str"/>
<@s.hidden id="total_price" name="total_price" value="${total_amount?if_exists}"/>
<@s.hidden id="sub_total_price" name="sub_total_price"/>
<@s.hidden id="checked_num" name="checked_num"/>
<@s.hidden id="use_num" name="use_num" value="${(memberfund.use_num)?if_exists}"/>
<@s.hidden id="use_num_pay" name="use_num_pay" />
<@s.hidden id="total_price" name="v_amount" value="${total_amount?if_exists}"/>
 <@s.hidden id="total_amount" name="total_amount" value="${total_amount?if_exists}"/>
<@s.hidden  name="remark1"  value="${order_id_str?if_exists}" />
<@s.hidden  name="remark2"  value="${session_cust_id?if_exists},${session_user_id?if_exists},1" />
<@s.hidden  name="pa_MP" id="pa_MP" value="${session_cust_id?if_exists}-${session_user_id?if_exists}-0_1" />
<@s.hidden  name="is_virtual"/>
<@s.hidden id="session_cust_id" name="session_cust_id" value="${session_cust_id?if_exists}"/>
<@s.hidden id="order_type" name="order_type"/>
<@s.hidden name="is_recharge" value="1"/>
<@s.hidden id="defaultbank" name="defaultbank"/>
<@s.hidden id="payTypeFlag" name="payTypeFlag" />
<!--头部--> 
<#include "/WEB-INF/template/bmall/default/topest.ftl">
<!--logo，搜索-->
<div class="logoSearDiv">
   <!--logo-->
   <div class="logo"><a href="/"><img src="${cfg_logourl}" /></a></div>
   <!--支付步骤-->
   <div class="successProcess"></div>
</div>
<!--内容-->
<div class="sucOrder">
   <#if is_check_email?if_exists=='1' || is_check_email?if_exists=='' || is_check_mobile?if_exists=='' || is_check_mobile?if_exists=='1' || pay_pass?if_exists==''><div class="sucTips">为保障您账号及资金的安全：请您尽快启用<#if pay_pass?if_exists==''>支付密码，</#if><#if is_check_mobile?if_exists=='' || is_check_mobile?if_exists=='1'>验证手机，</#if><#if is_check_email?if_exists=='1' || is_check_email?if_exists==''>验证邮箱，</#if> <a  href="/bmall-accountCenter.action?parentMenuId=6644344633&selli=2251641346">立即启动>></a></div></#if> 
   <div class="payMain">
        <h2><#if order_pay_tip=="1">请您及时付款，以便订单尽快处理!<#else>订单提交成功，请您尽快付款!</#if></h2>
       <p class="orderp"><span>订单号：
        	<input type="checkbox" onclick="selOrder(this)" id="order_${(order_id_str)?if_exists}" name="order_id" value="${(order_id_str)?if_exists}" style="display:none"/>
           <i class="iBlue">${(order_id_str)?if_exists}</i>
       </span><span>应付金额：<b>${total_amount?if_exists}元</b></span></p>
       <p class="tipsp">请您在提交订单后<span>
        <#if (cfg_publictimeout?if_exists?number>60)>${cfg_publictimeout?if_exists?number/60}小时
       <#else>${cfg_publictimeout?if_exists}分钟
       </#if>
       </span>内完成支付,否则订单自动取消</p>
       <p class="tipsp"><span>商城提示: 活动红包将在交易成功后发至您的账户<span></p>
       <div class="selPayStyle" id="selPayStyleId">
         
         <div class="payh2">
            <ul>
              <#list paymentList as plist>
		      <#if plist.pay_code=='alipay'>
                <li>支付宝</li>
              </#if>
               </#list>
              <#list paymentList as plist>
              <#if plist.pay_code=='unionpay'>
                <li>银联在线(支持信用卡)</li>
              </#if>
               </#list>
              <#list paymentList as plist>
               <#if plist.pay_code=='wxpay' >
                <li>微信支付</li>
                </#if>
		       </#list>
                <li>网上银行</li>
                <li id="usePayAcount">帐号余额</li>
            </ul>
         </div>
        
         <div class="tabDiv"> 
         
            <!-- 支付宝 银联在线(支持信用卡)-->
           <#list paymentList as plist>
		      <#if plist.pay_code=='alipay'>
		            <div class="payCont"> 
			               <div class="payDiv">
				                <ul>
				           			 <li>
					           			 <div>
					           			 	<img src="${plist.pay_logo}" width="150px" height="50px" align="absmiddle" />
					           			 </div>
				           			 </li>
				                </ul>
			                 <br class="clear"/>
			               </div>
			               <p><input type="button" class="payBut" onclick="paySubmit('${plist.pay_code}');"></p>
			    	  </div> 
	    	  </#if>
	    	  </#list>
	    	 <#list paymentList as plist>
	    	   <#if plist.pay_code=='unionpay'>
		            <div class="payCont"> 
			               <div class="payDiv">
			                <ul>
		           			 <li><div>
		           			 <img src="${plist.pay_logo}" width="150px" height="50px" align="absmiddle" /></div></li>
			                </ul>
			                 <br class="clear"/>
			               </div>
			               <p><input type="button" class="payBut" onclick="paySubmit('${plist.pay_code}');"></p>
			    	  </div> 
	    	  </#if>
	    	 </#list>
	    	 <#list paymentList as plist>
	    	   <#if plist.pay_code=='wxpay'>
		            <div class="payCont"> 
			               <div class="payDiv">
			                <ul>
		           			 <li><div>
		           			 <img src="${plist.pay_logo}" width="150px" height="50px" align="absmiddle" /></div></li>
			                </ul>
			                 <br class="clear"/>
			               </div>
			               <p><input type="button" class="payBut" onclick="paySubmit('${plist.pay_code}');"></p>
			    	  </div> 
	    	  </#if>
		    </#list>
	    	 <!-- 网上银行-->
	         <div class="payCont"> 
	               <div class="payDiv">
	                <ul>
	                   <#list bankList as banklist>
			           		<li><div><input type="radio" name="bankCode" <#if banklist_index==0>checked</#if> value="${banklist.para_key}"/><img src="${banklist.img_path}"  align="absmiddle" id="bankImg"/></div></li>
			           </#list>
			          
	                 </ul>
	                 <br class="clear"/>
	               </div>
	               <p><input type="button" class="payBut" onclick="bankPaySubmit();"></p>      
	    	  </div>   
	    	 <!-- 账户余额-->
            <div class="payCont" id="usepayAccountDiv"> 
               <div class="payDiv">
                 <p><b>账户</b>(${(buyMember.cust_name)?if_exists})<span>可支付余额：<b><#if (memberfund.use_num)?if_exists!='0'>${(memberfund.use_num)?if_exists}<#else>0</#if></b>元</span></p>
                 <p>使用账户余额支付<b class="redb">${total_amount?if_exists}</b>元<span><#-->(如果您余额不足，请您<a href="/bmall_Fundrecharge_list.action?parentMenuId=6644344633&selli=5241183873">点击充值</a>)--></span></p>
                <#if (memberfund.use_num)?if_exists!='0'>
                 <p>支付密码：
                 <input type="password" id="pay_password" name="pay_password" class="pswText" align="absmiddle"align="absmiddle">
                 <span><a href="/bmall_Memberfund_goPass.action?parentMenuId=6644344633&selli=2251641346">忘记支付密码？</a></span>
                  <span id="pay_passwordtip"><@s.fielderror><@s.param>pay_password</@s.param></@s.fielderror></span>
                 </p>
                 </#if>
               </div>
                <#if (memberfund.use_num)?if_exists!='0'>
          			 <p><input type="button" class="payBut" onclick="subConfirmPay();"/></p>
                 </#if>
             </div>

   	 <@s.hidden name="spike_id" />
   </div>
   </div>
   <div class="orderTips">完成支付后，您可以：<a href="/bmall-buyer.action">查看订单状态</a><a href="/" >继续购物</a><!--<a href="#">问题反馈</a>--></div> 
   
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
	           	<a href="/bmall_Goodsorder_buyorderlist.action"><img src="/malltemplate/bmall/images/pay_fald.gif"/></a></td>
	          </tr>
	       </table>
	</div>
	
</@s.form>

</body>
</html>
