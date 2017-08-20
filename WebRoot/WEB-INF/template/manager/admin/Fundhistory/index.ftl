<html>
  <head>
    <title>查看余额流</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script language="javascript" type="text/javascript" >
    //提交action 设置
  	function setAction(obj){
			document.forms["indexForm"].action='/admin_Fundhistory_'+obj+'.action';
			document.forms["indexForm"].submit();
	}	
 //导出余额流
	function exprotExcel(){
		if(window.confirm("导出内容默认为当前搜索条件下数据，确定要导出余额流吗?")) {
			setAction('export')
		}
	}	
    </script>
  </head>
<body>
<@s.form action="/admin_Fundhistory_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：<#if enter?if_exists==''>财务统计 >财务管理 ><#elseif enter?if_exists=='0'> 会员管理 > 会员管理 > 会员列表 ><#elseif enter?if_exists=='1'> 会员管理 > 商家管理 > 企业会员列表 ></#if> 余额管理 > 查看余额流</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>会员名称:</td>
			<td><@s.textfield name="cust_name_s"  cssStyle="width:245px;"/></td>
			 <td class="tdpad">时间段:</td>
			<td>
				 <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  />  	
      
        至
        <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
			</td>
	       <td><input type="submit" class="rbut" value="查询" onclick="setAction('list')"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="20%"  >会员名称</th>
		    <th width="10%"  >收入</th>
		    <th width="10%"  >支出</th>
		    <th width="10%"  >余下余额</th>
		    <th width="10%"  >操作人</th>
		    <th width="10%"  >操作时间</th>
		    <th width="30%"  >事由</th>
	  </tr>
	  
	  <#list fundhistoryList as fundhistory>
	    <tr >
		    <td align="center">
				 <#if fundhistory.cust_name?if_exists!=''>
					    <#if fundhistory.cust_name?length lt 20>
					    ${fundhistory.cust_name?if_exists}
					    <#else>
					    ${fundhistory.cust_name[0..19]}...
					    </#if>
			    </#if>
		    </td>
		    <td align="center">
				${fundhistory.fund_in?if_exists}
		    </td>
		    <td align="center">
				${fundhistory.fund_out?if_exists}
		    </td>
		    <td align="center">
				${fundhistory.balance?if_exists}
		    </td>
		    <td align="center">
				<#if fundhistory.user_name==''>
				系统操作    
			    <#else>
			    ${fundhistory.user_name?if_exists}
			    </#if>
		    </td>
		    <td align="center">
				${fundhistory.in_date?if_exists}
		    </td>
		    <td align="center">
				${fundhistory.reason?if_exists}
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
     <input type="button" class="rbut" onclick="javascript:history.go(-1);" value="返回">
     <input type="button" class="rbut"onclick="exprotExcel();"  value="导出余额流">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
<@s.hidden name="cust_id_s" />
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>
