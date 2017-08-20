<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员中心</title>
<link href="/include/bmember/css/mIndex.css" rel="stylesheet" type="text/css" /> 
<script src="/include/bmember/js/goodsorder.js" type="text/jscript"></script>

</head>
<body>
<@s.form action="/bmall_Goodsorder_buyorderlist.action" method="post" id="indexForm">
  <!--右边-->
  <div class="wR810">
   <!---->
   <div class="descript">
      <table width="100%" cellpadding="0" cellspacing="0" class="destab">
      	<tr><td width="17%"><div>
      	<#if member.logo_path?if_exists>
			  <a target="_self" href="###"onclick="linkToInfo('/bmall_Member_photoview.action','member.cust_id=${member.cust_id}');"><img src="${member.logo_path?if_exists}"  width="110" height="110" alt="修改头像"></a>
		<#else>
			 <a target="_self" href="###"onclick="linkToInfo('/bmall_Member_photoview.action','memberuser.cust_id=${member.cust_id}');" ><img src="/include/bmember/images/defalutHead.gif" width="110" height="110" alt="上传头像"></a>
	    </#if>
      	</div></td>
            <td width="42%">
                <p><b><#if nickName != ""><img src="/malltemplate/bmall/images/qq_logo.png"/>${nickName?if_exists} <#elseif weixinName != ""><img src="/malltemplate/bmall/images/weixinlogin.png"/>${weixinName?if_exists}<#else>${(memberuser.user_name)?if_exists}</#if></b> 
                <#if (current_hour)?if_exists?number lt 11>上午好
                <#elseif current_hour?if_exists?number gt 10 && (current_hour)?if_exists?number lt 13>中午好
                <#elseif current_hour?if_exists?number gt 12 && (current_hour)?if_exists?number lt 18>下午好
                <#elseif current_hour?if_exists?number gt 17 && (current_hour)?if_exists?number lt 24>晚上好</#if>
                <span>(上次登陆：
                <#if session_last_login_time?if_exists?length lt 15>
			      无
			      <#else>
			      <#if session_last_login_time?if_exists?length gt 19>
			      ${session_last_login_time[0..18]?if_exists}
			      <#else>
			      ${session_last_login_time?if_exists}
			      </#if>
			      </#if>)
			      </span></p>
                <p>账号余额：<b><#if memberfund!=null>${memberfund.use_num?if_exists}<#else>0</#if></b>元 
                &nbsp;&nbsp;&nbsp;&nbsp;<span><a href="/bmall_Interhistory_bmalllist.action?parentMenuId=6644344633&selli=2751473824">我的积分</a>:<font color="red"><b><#if memberinter!=null >${(memberinter.fund_num)?if_exists}<#else>0</#if></b></font>分</span></p>
                <p>会员等级:<img src="${level_url?if_exists}" align="absmiddle" title="成长值为:${(member.growthvalue)?if_exists}"  />
                &nbsp;&nbsp;成长值:<font color="red"><b><#if (member.growthvalue)?if_exists!=null >${(member.growthvalue)?if_exists}<#else>0</#if></b></font>
                </p>
                <p>账号安全：<#if  is_check_email?if_exists=='0' && is_check_mobile?if_exists=='0'>
                     <i class="heightSafety"></i>
				   <#elseif is_check_email?if_exists=='0' || is_check_mobile?if_exists=='0'>
				    <i class="midSafety"></i>
				   <#else>
				    <i class="lowSafety"></i>
				  </#if></p>
				 
            </td>
            <td width="41%" class="destd">
               <p><span><a href="/bmall_Goodsorder_buyorderlist.action?parentMenuId=4637243721&selli=2442547481&order_state_s=1">等待付款</a>(<b>${buyerOrder?if_exists}</b>)</span>
               <span><a href="/bmall_Goodsorder_buyorderlist.action?parentMenuId=4637243721&selli=2442547481&order_state_s=3">等确认收货</a>(<b>${reOrder?if_exists}</b>)</span></p>
               <p><span><a href="/bmall_Goodsorder_buyorderlist.action?parentMenuId=4637243721&selli=2442547481&order_state_s=7">等待评价</a>(<b>${sevelCount?if_exists}</b>)</span>
               <span><a href="/bmall_Refundapp_buylist.action?parentMenuId=4637243721&selli=7215368137">退款/退货</a>  (<b>${refundapporder?if_exists}</b>)</span>
               </p>
              <p><span><a href="/bmall_Collect_list.action?parentMenuId=4637243721&selli=6577746616">我的收藏</a>(<b>${collCount?if_exists}</b>)</span>
               <span><a href="/bmall_Consultation_auditList.action?parentMenuId=4637243721&selli=8227263875">我的咨询</a>(<b>${consulCount?if_exists}</b>)</span></p>
              <p class="safetyP">
              <#if is_check_mobile?if_exists=='1' || is_check_mobile?if_exists==''>
              <a class="nPhone" href="###" onclick="linkToInfo('/bmall_Msgcheck_checkmobile.action','memberuser.cust_id=${memberuser.cust_id}');">手机未验证</a><#elseif  is_check_mobile?if_exists=='0'><a class="sPhone">手机已验证</a></#if>
              <#if is_check_email?if_exists=='1' || is_check_email?if_exists==''><a  class="nEmail" href="###" onclick="linkToInfo('/bmall_Msgcheck_checkemail.action','memberuser.cust_id=${memberuser.cust_id}');">邮箱未验证</a><#elseif is_check_email?if_exists=='0'>
              <a  class="sEmail">邮箱已验证</a></#if><#if pay_pass?if_exists==''><a class="nPaypsw" href="/bmall_Memberfund_isNotSetUp.action">未设置支付密码</a><#else><a class="sPaypsw">已设置支付密码</a></#if></p>
            </td>
        </tr>
      </table>
   </div>
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>最新订单</span><a  href="/bmall_Goodsorder_buyorderlist.action?parentMenuId=4637243721&selli=2442547481">查看全部订单</a></h2>
        
        <div class="orderDiv ordPad">
        
        	<!---->             
             <table width="100%" cellpadding="0" cellspacing="0" class="h2Tab">
               <tr><td width="37%">订单信息</td><td width="12%">收货人</td><td width="12%">应付金额</td><td width="12%">时间</td><td width="12%">状态</td><td width="15%">操作</td></tr>
             </table>
             
            <#list goodsorderList as goodsorder>
          <table width="100%" cellpadding="0" cellspacing="0" class="orderTab">
           <tr>
               <th colspan="6">
               	   <span>订单编号：
               	   <a onclick="linkToInfo('/bmall_Goodsorder_buyOrderView.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}&parentMenuId=4637243721&selli=2442547481');" >${(goodsorder.order_id)?if_exists}</a>
               	   </span>
               	     <#if (goodsorder.is_sea)?if_exists=='1'>
					 	<font color="#005ea7">直邮订单</font>
					<#elseif (goodsorder.is_sea)?if_exists=='0'>
						<font color="#005ea7">保税区订单</font>
					</#if>
               </th>
           </tr>
           
         <tr>
           		<td width="37%" class="imgTd">
           		<div>
       		    <#assign num=0>
       		    <#assign goods_id_str="">
       		    <#assign goods_name_str="">
       		    <#assign list_img_str="">
       		    <#assign sale_price_str="">
           		 <#if (goodsorder.order_type)?if_exists=='6'>
           		 <#else>
					<#list detailList as detail>
						  <#if ((detail.order_id)?if_exists)==((goodsorder.order_id)?if_exists)>
						  	 <#assign num=num+1>   
						  	 <#assign img_path="${( detail.temporary_goodsimg)?if_exists}">   
					         <#if num?if_exists=="1">
					         <#assign goods_id_str="${detail.goods_id?if_exists}">
					         <#assign goods_name_str="${detail.temporary_goodsname?if_exists}">
					         <#assign list_img_str="${detail.temporary_goodsimg?if_exists}">
					         <#assign sale_price_str="${detail.goods_price?if_exists}">
					   		 <#else>
					   		 <#assign goods_id_str="${goods_id_str}#${detail.goods_id?if_exists}">
					   		 <#assign goods_name_str="${goods_name_str}#${detail.temporary_goodsname?if_exists}">
					   		 <#assign list_img_str="${list_img_str}#${detail.temporary_goodsimg?if_exists}">
					   		 <#assign sale_price_str="${sale_price_str}#${detail.goods_price?if_exists}">
					   		 </#if>
					   		 <#assign goodsdetailurl="">
					   		 <#if (goodsorder.order_type)?if_exists=='1'>
	                          <#assign goodsdetailurl="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
							</#if>
				      	<a href="${goodsdetailurl?if_exists}" target="_blank">
				      	<img src="<#if img_path!=''>${img_path?if_exists}<#else>${(cfg_nopic)?if_exists}</#if>" class="f_left" align="absmiddle"></a>
				      		 
		      		 </#if>
		      		 </#list>	
           		 </#if>
	      		 
           		</div></td>
           		<td width="12%">${goodsorder.consignee?if_exists}</td>
           		<td width="12%">	
           		<p> 
           			￥<b>${goodsorder.tatal_amount?if_exists}</b> <br/>
           			(<#if goodsorder.ship_free!='0'>含运费${goodsorder.ship_free?if_exists}<#else>免运费</#if>)
           			</p>
           		</td>
                <td width="12%">
                	<p>${goodsorder.order_time?if_exists[0..10]}</p><p>${goodsorder.order_time?if_exists[10..18]}</p>
                	<p>
           			 <#assign zfpay="">
	                 <#list paymentList as plist>
	                     <#if goodsorder.pay_id==plist.pay_id>
				            	<#assign zfpay=plist.pay_name>
	                     </#if>
	                </#list>
	                <#if (zfpay)?if_exists>
	            	${(zfpay)?if_exists}
	            	<#else>
	            	</#if>
           			</p>
                </td>
                <td width="12%">
                	<p>
				       <#if goodsorder.order_para!=''>
				   		   <b>${(goodsorder.order_para)?if_exists}</b>
				   		   <#if goodsorder.is_sea=="1">
				   		   <#if goodsorder.order_state=="3" && goodsorder.is_clearance=="0"></br><b>待通关</b><#elseif goodsorder.order_state=="3" && goodsorder.is_clearance=="1"></br><b>通关成功</b><#elseif goodsorder.order_state=="3" && goodsorder.is_clearance=="2"></br><b>通关失败</b></#if>
				   		   </#if>
				       </#if>
				        <#if goodsorder.order_state=='2'&& goodsorder.deliver_state!="0">
				    	    </br><b>订单已审核</b>
				    	</#if>
                	</p>
                	<#if goodsorder.order_state?if_exists=='3' && goodsorder.is_clearance =="1">
				     <div class="logistics">
                          <i> <a  onclick="linkToInfo('/bmall_Goodsorder_buy_order_Logistics.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" >
																    查看物流</a></i>
                       </div>
				     </#if >
                </td>
                <td width="15%">
                	<p>
	                	<#if goodsorder.order_state=='1'&&(goodsorder.order_type)?if_exists!='6'>
					    	<a  target="_blank" onclick="linkToInfo('/mall/order!goPay.action','order_pay_tip=1&order_id_str=${goodsorder.order_id?if_exists}&use_num_pay=${(goodsorder.tatal_amount)?if_exists}&total_amount=${(goodsorder.tatal_amount)?if_exists}&all_total=${(goodsorder.tatal_amount)?if_exists}&sendway=${goodsorder.send_mode?if_exists}');" 
					    	class="paya">付款</a>
					        <br/>
						</#if>
							<a onclick="linkToInfo('/bmall_Goodsorder_buyOrderView.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');" >
						查看</a>
						<#if goodsorder.order_state=='0'>
							<span>|</span>
							<#if goodsorder.is_del=='1'>
							<a onclick="delB2cOneInfo('/bmall_Goodsorder_deleteOrder.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')" >
							删除</a>
							</#if>
						</#if>
						<#if goodsorder.order_state=='7'>
						   	<span>|</span>
						   <a  href="###" onclick="linkToInfo('/bmall_Goodseval_orderEvaluateView.action','order_id=${goodsorder.order_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')" >
						    评价晒单</a>
					     </#if> 
					<br/>
					<#if goodsorder.order_state=='1'|| goodsorder.order_state=='2'>
				        <#if goodsorder.deliver_state=="0">
					        <#if goodsorder.order_type =="6"&&goodsorder.order_state=='2'>
					        <#else>
					         <!-- 未付款 取消订单 -->
					         <#if goodsorder.is_clearance?if_exists==null || goodsorder.is_clearance?if_exists=="" || goodsorder.is_clearance?if_exists=="0">
						     <a onclick="cancelOrder('${goodsorder.order_id?if_exists}','${goodsorder.tatal_amount?if_exists}','${goods_id_str?if_exists}','${list_img_str?if_exists}','${goods_name_str?if_exists}')" >取消订单</a>
					         </#if>
					        </#if>
				        </#if>
				   </#if>
				   <#if goodsorder.is_sea=='1'&& goodsorder.order_state=='3' && goodsorder.is_clearance =="1">
						 <a  href="###"  class="qrbut" onclick="orderconfirmreceipt('${goodsorder.order_id?if_exists}','${goodsorder.tatal_amount?if_exists}','${goods_id_str?if_exists}','${list_img_str?if_exists}','${goods_name_str?if_exists}')">确认收货</a>
				    </#if> 
				    <#if goodsorder.is_sea=='0'&& goodsorder.order_state=='3'>
						 <a  href="###"  class="qrbut" onclick="orderconfirmreceipt('${goodsorder.order_id?if_exists}','${goodsorder.tatal_amount?if_exists}','${goods_id_str?if_exists}','${list_img_str?if_exists}','${goods_name_str?if_exists}')">确认收货</a>
				    </#if> 
                	</p>
                	<#if  goodsorder.order_state=='7' || goodsorder.order_state=='8'>
                	    <#if (goodsorder.order_type)?if_exists=='1'>
		                	<p><a  href="###" onclick="linkToInfo('/bmall_Refundapp_appRefundGoods.action','order_id=${goodsorder.order_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}')" >申请退款/退换货</a></p>
		                	</#if>
		                	<!--<#if  goodsorder.order_state=='0' ||goodsorder.order_state=='7' || goodsorder.order_state=='8' || goodsorder.order_state=='4'>
		                	<p><a href="###" class="addCart" onclick="addCart('${goods_id_str?if_exists}','${sale_price_str?if_exists}')"></a></p>
                		       </#if>
                		-->
                	</#if>
                </td>
            </tr>
          </table>
          </#list>
        </div>
         <div class="clear"></div>
   </div>
 </div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>
<@s.hidden name="buy_order_cancel" id="refund_buy_reason"/>
<@s.hidden name="goods_order_id" id="order_id"/>
<@s.hidden name="jsontotal" id="jsontotal_id"/>    
</@s.form>
<!--取消订单弹出层-->
<div class="popupDiv" id="cancelOrderId"></div>
<!--确认收货弹出层-->
<div class="popupDiv" id="confirmOrderId"></div>
</body>
</html>
