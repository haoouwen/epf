<html>
<head>
	<title> 账户充值</title>
	<script src="/include/bmember/js/member.js" type="text/jscript"></script>
</head>
<body>

<@s.form action="/bmall_Fundrecharge_list.action" method="post" id="indexForm">
<@s.hidden id="recharge_platform" name="payment_code" value="${cfg_defaultpaymentPC?if_exists}"/> 
<@s.hidden name="is_recharge" value="0"/>
<@s.hidden  name="pa_MP" id="pa_MP" value="${session_cust_id?if_exists}-${session_user_id?if_exists}-0_2" />
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>账户余额/充值</span></h2>
        <!---->
        <div class="topup">
        	<span>余额：<b class="mred">￥${memberfund.use_num?if_exists}</b></span>
             <input type="button" class="rechargeBtn" value="在线充值"> <input type="button" class="rechargeCrad" value="充值卡充值">
        </div>
        <!---->
        <div class="topupDiv">
            <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
               <tr><th width="20%"><span>*</span>充值余额</th><td><input type="text" class="aifText" name="total_amount" maxLength="6" onkeyup="checkNum(this);" id="id_momey">元<span id="tipmomey" style="display: none;"><font color="red">(请输入充值金额)</font></span></td></tr>
               <tr><th><span>*</span>充值平台</th>
               <td>
		         <#list paymentList as plist>
                     <#if cfg_defaultpaymentPC==plist.pay_code>
                          ${(plist.pay_name)?if_exists}
                     </#if>
				 </#list>
               </td>
               </tr>
               <tr><th>-</th><td><input type="button" class="submitbut" value="提交" onclick="onlineSubComPay();"></td></tr>
            </table>
        </div>
        
        <div class="cardDiv">
            <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
               <tr><th width="20%"><span>*</span>充值卡号</th><td><input type="text" class="aifcardText" name="amount" maxLength="4" onkeyup="checkNum(this);" id="id_num">- <input type="text" class="aifcardText" name="amount2" maxLength="4" onkeyup="checkNum(this);" id="id_num2">-
               <input type="text" class="aifcardText" name="amount3" maxLength="4" onkeyup="checkNum(this);" id="id_num3">- <input type="text" class="aifcardText" name="amount4" maxLength="4" onkeyup="checkNum(this);" id="id_num4">
               <span id="tipcard" style="display: none;"><font color="red">(请输入16位卡号)</font></span></td></tr>
               <tr><th>-</th><td><input type="button" class="submitbut" value="提交" onclick="cardSubComPay();"></td></tr>
            </table>
        </div>
        <!---->         
        <div class="usedTDiv">
           <div class="rTopDiv">充值记录：<select name="pay_date" onchange="submit()" id="pay_date"><option value="">--请选择--</option><option value="1" <#if pay_date?if_exists=="1">selected</#if>>三个月内的记录</option></select></div>
           <table width="100%" cellpadding="0" cellspacing="0" class="usedTab">
           
              <tr>
                <th width="10%"><input type="checkbox" id="checkall" onclick="selectAllBoxs('checkall','fundrecharge.trade_id')">全选</th>
              	<th width="20%">充值订单号</th>
              	<th width="10%">充值金额</th>
              	<th width="15%">充值平台</th>
                <th width="10%">状态</th>
                <th width="15%">时间</th>
              </tr>
              
             <#list fundrechargeList as fundrecharge>  
              <tr>
                <td width="5%">
                <#if fundrecharge.order_state?if_exists!='0'>
                <input type="checkbox" name="fundrecharge.trade_id"  value="${fundrecharge.trade_id?if_exists}">
                </#if>
                </td>
                <td>${fundrecharge.order_id?if_exists}</td>
                <td>￥${fundrecharge.fund_num?if_exists}</td>
                <td>${fundrecharge.payplat?if_exists}</td>
                <td class="mgray">
				    <#if fundrecharge.order_state?if_exists=='0'>
				    <font color='red'>未审核</font></a>
				    </#if>
				    <#if fundrecharge.order_state?if_exists=='1'>
				    <font color='blue'>已审核</font></a>
				    </#if>
				    <#if fundrecharge.order_state?if_exists=='2'>
				    <font color='green'>作废</font></a>
				    </#if>
                </td>
                <td>${fundrecharge.pay_date?if_exists}</td>
              </tr>
              </#list>	
           </table>
           <!---->
            <div class="selUsed" style="padding-left:5px;padding-top:10px;">
               <input type="button" class="graybut" value="删除" onclick="delInfo('fundrecharge.trade_id','/bmall_Fundrecharge_delete.action')">
           </div>
        </div>
        <!---->
        <!--翻页控件-->
        <div class="listbottom">${pageBar?if_exists}</div>
          
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="selli"/>
<@s.hidden name="cardskey" id="cardskey"/>
<@s.hidden name="parentMenuId"/>
<@s.hidden  name="pa_MP" id="pa_MP" value="${session_cust_id?if_exists}-${session_user_id?if_exists}-0_2" />
</@s.form>
<div style="display:none;"  id="setPay"  >
    <table  style="text-align: center;">
      <tr>
       	<td style="color:#EA9359;padding-top:10px;"  ><img  src="/malltemplate/bmall/images/pay_tip.png"/>充值完成前请不要关闭此窗口, 完成付款后请点击下面按钮</td>
      </tr>
       <tr style="text-align: center;">
       	<td style="padding-top:15px;">
       	<a href="/bmall_Fundrecharge_list.action?parentMenuId=8434241622&selli=2187722655"><img src="/malltemplate/bmall/images/pay_success.gif"/></a>
       	<a href="/mall/article!attrdetail.action?article.cat_attr=8547217448"><img src="/malltemplate/bmall/images/pay_fald.gif"/></a></td>
      </tr>
   </table>
</div>
</body>
</html>

