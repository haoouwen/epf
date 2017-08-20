<html>
  <head>
    <title>会员资金管理</title>
  </head>


<body>
<@s.form action="/admin_Memberinter_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:<#if enter?if_exists==''>财务统计 >财务管理 ><#elseif enter?if_exists=='0'> 会员管理 > 会员管理 > 会员列表 ><#elseif enter?if_exists=='1'> 会员管理 > 商家管理 > 商家会员列表 ></#if> 会员积分管理表 </div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td >会员名称:</td>
			<td><@s.textfield name="cust_name_s"/></td>
			<td class="tdpad">积分:</td>
			<td><@s.textfield name="fund_num_s"/>-<@s.textfield name="last_fund_num_s"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="40%" >会员名称</th>
		    <th width="10%"  >会员类型</th>
		    <th width="20%">积分</th>
		    <th width="20%"  >积分修改</th>
		    <th width="20%"  >查看积分变化情况</th>
		  </tr>
		  
		  <#list memberinterList as memberinter>
		  <tr>
		    <td align="center">
		    
		    
		    <#if memberinter.cust_name?if_exists != ''>
		    
			    <#if memberinter.cust_name?length lt 33>
			    ${memberinter.cust_name?if_exists}
			    <#else>
			    ${memberinter.cust_name[0..32]}...
			    </#if>
		    
		    </#if>
		    </td>
		    <td align="center"><#if memberinter.mem_type=='0'><font color='red'>卖家</font><#else><font color='green'>买家</font></#if></td>
		    <td align="center">${memberinter.fund_num?if_exists}</td>
		    <td align="center"><a onclick="linkToInfo('/admin_Memberinter_view.action','cust_id=${memberinter.cust_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
		    <td align="center"><a onclick="linkToInfo('/admin_Interhistory_list.action','cust_id_s=${memberinter.cust_id?if_exists}&two_pages_link=no&enter=${enter?if_exists}');"><img src="/include/common/images/view.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Interhistory_list.action','cust_id_s=${custId?if_exists}&enter=${enter?if_exists}');" value="查看积分流">
     <#if enter?if_exists=='0'>
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Member_list.action','');" value="返回列表">
     <#elseif enter?if_exists=='1'>
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Corpomember_list.action','');" value="返回列表">
     </#if>
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


  </body>
</html>




