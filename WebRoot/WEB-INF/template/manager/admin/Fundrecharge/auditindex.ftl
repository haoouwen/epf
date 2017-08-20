<html>
  <head>
    <title>会员余额充值审核表</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  <body>
<@s.form action="/admin_Fundrecharge_auditlist.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：财务统计 >财务管理 > 会员余额充值审核表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>会员名称:</td>
			<td><@s.textfield name="cuts_name_s"  cssStyle="width:245px;"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	       <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="3%" > <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('fundrecharge.trade_id')"/></th>
		    <th width="15%"  >订单号</th>
		     <th width="12%"  >银行交易单号1</th>
		    <th width="6%"  >会员名称</th>
		    <th width="10%"  >充值余额</th>
		    <th width="6%"  >支付平台</th>
		    <th width="10%"  >状态</th>
		    <th width="12%"  >充值时间</th>
		    <th width="12%"  >操作人</th>
		    <th width="5%"  >操作</th>
	  </tr>
	  
	   <#list fundrechargeList as fundrecharge>
	    <tr >
		    <td><input type="checkbox" name="fundrecharge.trade_id" value="${fundrecharge.trade_id?if_exists}"/></td>
		    <td align="center">
				${fundrecharge.order_id?if_exists}
		    </td>
		    <td align="center">
		     <#if (fundrecharge.bank_order_id)!="" || (fundrecharge.bank_order_id)!=null>
				 ${fundrecharge.bank_order_id?if_exists}
		     <#else>
		         -
		     </#if>
		    </td>
		     <td align="center">
				<#if fundrecharge.cust_name?if_exists!=''>
				    <#if fundrecharge.cust_name?length lt 20>
				    ${fundrecharge.cust_name?if_exists}
				    <#else>
				    ${fundrecharge.cust_name[0..19]}...
				    </#if>
			    </#if>
		    </td>
		     <td align="center">
				${fundrecharge.fund_num?if_exists}
		    </td>
		     <td align="center">
				${fundrecharge.payplat?if_exists}
		    </td>
		     <td align="center">
				<#if fundrecharge.order_state?if_exists=='0'><a onclick="linkToInfo('/admin_Fundrecharge_list.action','order_state_s=${fundrecharge.order_state?if_exists}');"><font color='red'>未审核</font></a></#if>
		    </td>
		     <td align="center">
				${fundrecharge.pay_date?if_exists}
		    </td>
		     <td align="center">
				${fundrecharge.user_name?if_exists}
		    </td>
		    <td align="center">
				<a onclick="linkToInfo('/admin_Fundrecharge_view.action','fundrecharge.trade_id=${fundrecharge.trade_id?if_exists}');"><img src="/include/common/images/audit.png" /></a>
		    </td>
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
      <input type="button" class="rbut"onclick="delInfo('fundrecharge.trade_id','/admin_Fundrecharge_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="payplat_s"/>
  <@s.hidden  name="order_state_s"/>
  <@s.hidden  name="starttime_s"/>
  <@s.hidden  name="endtime_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Fundrecharge_auditlist.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">会员名称:</td>
			<td><@s.textfield name="cuts_name_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">支付平台:</td>
			<td><@s.select name="payplat_s"  list="paymentList" listValue="pay_name" listKey="pay_code" headerKey="0" headerValue="请选择"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">支付时间:</td>
			<td> 
			<@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
	        至
	         <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		  </td>
		</tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('','','','','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
