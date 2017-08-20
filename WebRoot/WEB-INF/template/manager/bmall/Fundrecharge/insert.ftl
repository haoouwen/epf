<html>
<head>
<title>账号充值</title>
<script type="text/javascript">
    //取支付平台的编码
    $(function(){
		    var payPlatform = $("#platform").val();
		    $("#paymentPlatform").val(payPlatform);
	});
	//调整在线支付页面
	function onlineSubComPay(){
	    var momey_num=$("#id_momey").val();
	    if(momey_num!=null&&momey_num!=""){
		    $("#setPay").tipshow({p_width:"350", p_height:"110", pop_title:"充值提示"});
		   	$("#gopay").submit();
	    }else{
	      $("#tipmomey").show();
	    }
	    
	}
 </script>
</head>
<body>
	
<@s.form action="/mall-pay-fundSend.html" id="gopay"  method="post"  name="formshopcongif" validate="true"  target="_blank">
<!--右边-->
<div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>账号充值</span></h2>
        <!----> 
            <div class="cancelDiv">
	            <!---->
	            <div class="opeDiv padDiv">
	              <table width="100%" cellpadding="0" cellspacing="0" class="detTab">
	              	<tr><th width="15%">充值余额<font color="red">*</font></th>
	              	<td width="85%">
	              		 <@s.textfield name="total_amount" id="p3_Amt"  maxLength="6" onkeyup="checkNum(this);" />元
		          		  <@s.fielderror><@s.param>fundrecharge.fund_num</@s.param></@s.fielderror>
		            <span id="tipmomey" style="display: none;color:red;">*(请输入充值金额)</span>
	              	</td></tr>
	                <tr><th width="15%">支付平台<font color="red">*</font></th>
	              	<td width="85%">
		               <@s.select name="fundrecharge.payplat" list="paymentList"   listValue="pay_name" listKey="pay_code" />  
	            	   <@s.fielderror><@s.param>fundrecharge.payplat</@s.param></@s.fielderror>      
	              	</td></tr>
	                <tr><th>.</th>
	                <td>
	              		 <@s.token/>
	             		<@s.submit value="提  交" cssClass="submitbut"/>
			     		<input type="button" name="returnList" class="submitbut" value="返回列表" onclick="linkToInfo('/bmall_Fundrecharge_list.action','');"/>
	                </td></tr>
	              </table>
	            </div>
          </div>
   </div>
</div>
  <div class="clear"></div>



<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
<@s.hidden  name="pa_MP" id="pa_MP" value="${session_cust_id?if_exists}-${session_user_id?if_exists}_2" />
<@s.hidden id="paymentPlatform"  name="paymentPlatform"   />
 </@s.form>
 
   <div style="display:none;"  id="setPay"  >
        <table  style="text-align: center;">
          <tr>
           	<td style="color:#EA9359;padding-top:10px;"  ><img  src="/malltemplate/bmall/images/pay_tip.png"/>充值完成前请不要关闭此窗口, 完成付款后请点击下面按钮</td>
          </tr>
           <tr style="text-align: center;">
           	<td style="padding-top:15px;">
           	<a href="/bmall_Fundrecharge_list.action"><img src="/malltemplate/bmall/images/pay_success.gif"/></a>
           	<a href="/mall/article!attrdetail.action?article.cat_attr=8547217448"><img src="/malltemplate/bmall/images/pay_fald.gif"/></a></td>
          </tr>
       </table>
<div>
</body>
</html>

