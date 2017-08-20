<html>
  <head>
    <title>记录日访问数列表</title>   
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
  </head>





<body>
<@s.form action="/admin_Visitnum_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:商品管理>网站转化率 >详细列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
	      <td>
			 日期：<@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:0})}',readOnly:true})"  /> 
		&nbsp;至&nbsp;
		 <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:0})}',readOnly:true})"  />
	<td align="center" colspan="2" style="border:0px;">
	</td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
         <tr style="background:url(images/top_tr.gif) repeat-x;">
     	 <th width="10%" >交易量:</th>
     	 <th width="10%"  >访问量:</th>
     	 <th width="10%"  >转化率:</th>
     	 
		  </tr>
		  <tr style="background:url(images/top_tr.gif) repeat-x;">
		     	<td width="10%"  align="center">
		     	<#if starttime_s?if_exists!=null && endtime_s?if_exists!="">
		         	${tradenum?if_exists}
		         <#else>
		         	-
		         </#if>
		     	</td>
		     	 <td width="10%"  align="center">
		     	 <#if starttime_s?if_exists!=null && endtime_s?if_exists!="">
		         	${visitsize?if_exists}
		         <#else>
		         	-
		         </#if>
		         	
		     	 </td>
		     	 <td width="10%"  align="center">
		     	 <#if starttime_s?if_exists!=null && endtime_s?if_exists!="">
		         	${tradesize?if_exists}
		         <#else>
		         	-
		         </#if>
		  </tr>
  
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
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>





