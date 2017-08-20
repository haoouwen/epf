<html>
  <head>
    <title>支付方式管理</title>
     <script src="/include/admin/js/payment.js" type="text/javascript"></script>
  </head>
<body>
<@s.form action="/admin_Payment_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:系统管理 > 系统管理 > 支付方式管理</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
		<th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('payment.pay_id')"/></th>
	    <th width="20%">支付接口编码</th>
	    <th width="15%">支付方式</th>
	    <th width="45%">描述</th>
	    <th width="10%">是否启用</th>
	    <th width="7%">操作</th>
	  </tr>
	  
	 <#list paymentList as payment>
	  <tr>
	    <td><input type="checkbox" name="payment.pay_id" value="${payment.pay_id?if_exists}"/></td>
	    <td align="center">${payment.pay_code?if_exists}</td>
	    <td align="center">
	    <#if payment.pay_name?if_exists!=''>
	    <#if payment.pay_name?length lt 8>
	    <a href="/admin_Payment_view.action?payment.pay_id=${payment.pay_id?if_exists}">${payment.pay_name?if_exists}</a>
	    <#else>
	    <a href="/admin_Payment_view.action?payment.pay_id=${payment.pay_id?if_exists}">${payment.pay_name[0..7]}</a>
	    </#if>
	    </#if></td>
	    <td align="center">
	    
	    <#if payment.pay_desc?if_exists!=''>
	    <#if payment.pay_desc?length lt 30>
	    ${payment.pay_desc?if_exists}
	    <#else>
	    ${payment.pay_desc[0..29]}
	    </#if>
	    </#if>
	    </td>
	    <td align="center"><#if payment.enabled?if_exists=='0'><a onclick="linkToInfo('/admin_Payment_list.action','enabled_s=${payment.enabled?if_exists}');"><font color='green'>是</font></a>
	    <#else><a onclick="linkToInfo('/admin_Payment_list.action','enabled_s=${payment.enabled?if_exists}');"><font color='red'>否</font></a></#if></td>
	    <td align="center"><a onclick="linkToInfo('/admin_Payment_view.action','payment.pay_id=${payment.pay_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
	  </tr>
	  </#list>
	  
	</table>
  </div>
<!--后台table结束-->
<!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
   <div class="clear"/>
<!--翻页结束-->
<!--按钮操作存放-->
   <div class="bsbut">
  	 <input type="button" class="rbut" onclick="linkToInfo('/admin_Payment_add.action','');" value="添加">
     <input type="button" class="rbut" onclick="updateBatchState('0','payment.pay_id','/admin_Payment_updateOn.action','启用');" value="启用">
     <input type="button" class="rbut" onclick="updateBatchState('1','payment.pay_id','/admin_Payment_updateDown.action','禁用');" value="禁用">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="payment.enabled" id="admin_payment_state"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
<!--搜索区域结束-->
  </body>
</html>

