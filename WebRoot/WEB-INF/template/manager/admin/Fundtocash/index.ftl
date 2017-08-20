<html>
  <head>
    <title>会员余额提现记录</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
<body>
<@s.form action="/admin_Fundtocash_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置： 财务统计 > 财务管理 > 会员余额提现记录</div>
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
		    <th width="3%" > <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('fundtocash.trade_id')"/></th>
		    <th width="15%"  >会员名称</th>
		    <th width="10%"  >提现余额</th>
		    <th width="12%"  >收款方式</th>
		    <th width="17%"  >收款账号</th>
		    <th width="11%"  >账号姓名</th>
		    <th width="12%"  >状态</th>
		    <th width="15%"  >申请时间</th>
		    <th width="15%"  >操作</th>
	  </tr>
	  
	  <#list fundtocashList as fundtocash>
		  <tr>
		    <td><input type="checkbox" name="fundtocash.trade_id" value="${fundtocash.trade_id?if_exists}"/></td>
		    <td align="center">
		    <#if fundtocash.cust_name?if_exists!=''>
		    <#if fundtocash.cust_name?length lt 20>
		    <a onclick="linkToInfo('/admin_Fundtocash_view.action','fundtocash.trade_id=${fundtocash.trade_id?if_exists}');">${fundtocash.cust_name?if_exists}</a>
		    <#else>
		    <a onclick="linkToInfo('/admin_Fundtocash_view.action','fundtocash.trade_id=${fundtocash.trade_id?if_exists}');">${fundtocash.cust_name[0..19]}</a>
		    </#if>
		    </#if>
		    </td>
		    <td align="center">${fundtocash.fund_num?if_exists}</td>
		    <td align="center">
		    <#list commparaList as comlist>
	           <#if fundtocash.getcash_type==comlist.para_value>
				       ${comlist.para_key}
	            </#if>
	        </#list>
		    </td>
		    <td align="center">${fundtocash.account?if_exists}</td>
		    <td align="center">${fundtocash.account_name?if_exists}</td>
		    <td align="center">
		    <#if fundtocash.order_state?if_exists=='1'><a onclick="linkToInfo('/admin_Fundtocash_list.action','order_state=${fundtocash.order_state?if_exists}');"><font color='blue'>已审核</font></a></#if>
		 	<#if fundtocash.order_state?if_exists=='3'><a onclick="linkToInfo('/admin_Fundtocash_list.action','order_state=${fundtocash.order_state?if_exists}');"><font color='green'>已处理</font></a></#if>
		    <#if fundtocash.order_state?if_exists=='4'><a onclick="linkToInfo('/admin_Fundtocash_list.action','order_state=${fundtocash.order_state?if_exists}');"><font color='blue'>作废</font></a></#if>
		    </td>
		    <td align="center">${fundtocash.in_date?if_exists}</td>
		    <td align="center"><a onclick="linkToInfo('/admin_Fundtocash_view.action','fundtocash.trade_id=${fundtocash.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut"onclick="delInfo('fundtocash.trade_id','/admin_Fundtocash_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="getcash_type_s"/>
  <@s.hidden  name="order_state"/>
  <@s.hidden  name="starttime_s"/>
  <@s.hidden  name="endtime_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Fundtocash_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">会员名称:</td>
			<td><@s.textfield name="cuts_name_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">收款方式:</td>
			<td><@s.select name="getcash_type_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">状态:</td>
			<td><@s.select name="order_state" list=r"#{'1':'已审核','3':'已处理','4':'作废'}" headerKey="" headerValue="请选择"/>  </td>
		</tr>
		<tr>
			<td class="searchDiv_td">时间段:</td>
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
