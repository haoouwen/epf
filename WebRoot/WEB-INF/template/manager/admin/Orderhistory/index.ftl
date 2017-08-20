<html>
  <head>
    <title>订单历史记录</title>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
  </head>
  
<body>
<@s.form action="/admin_Orderhistory_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">  当前位置:网站管理 > 订单管理 > 订单历史记录</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>资讯标题:</td>
			<td class="searchDiv_td">订单号:</td>
			<td><@s.textfield name="order_id_s" cssStyle="width:200px"/></td>
			<td class="tdpad">操作人:</td>
			<td><@s.textfield name="cust_name_s" cssStyle="width:200px"/></td>
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
		<th width="30%">订单号</th>
	    <th width="30%">操作人</th>
	    <th width="30%">操作记录</th>
	    <th width="10%">操作时间</th>
	  </tr>
	  
	  <#list orderhistoryList as orderhistory>
	  <tr>
	    <td align="center">${orderhistory.order_id?if_exists}</td>
	    <td align="center">${orderhistory.cust_name?if_exists}</td>
	    <td align="center">${orderhistory.action_name?if_exists}</td>  
	    <td align="center">${orderhistory.in_date?if_exists}</td>
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
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="starttime_s"/>
  <@s.hidden name="endtime_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>

<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Orderhistory_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		
			<tr>
				<td class="searchDiv_td">订单号:</td>
				<td><@s.textfield name="order_id_s"/></td>
			</tr>
			<tr>
				<td class="searchDiv_td">操作人:</td>
				<td><@s.textfield name="cust_name_s"/></td>
			</tr>
		    <tr>
		    <td class="searchDiv_td">下单时间段:</td>
		        <td>  	
		        <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
				               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
				&nbsp;至&nbsp;
		        <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
				               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		        </td>
		    </tr>
			<tr>
				<td align="center" colspan="2" style="border:0px;">
					<input type="button" name="search" value="搜索" onclick="searchSubmit();"/>&nbsp;
					<input type="button" name="close" value="关闭" onclick="closeSearch();"/>
				</td>
			</tr>
			
			<tr>
				<td align="center" colspan="2" style="border:0px;">
					<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
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

