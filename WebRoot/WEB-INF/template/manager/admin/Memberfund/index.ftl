<html>
  <head>
    <title>余额管理</title>
  </head>



<body>
<@s.form action="/admin_Memberfund_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:<#if enter?if_exists==''>财务统计 >财务管理 ><#elseif enter?if_exists=='0'> 会员管理 > 会员管理 > 会员列表 ><#elseif enter?if_exists=='1'> 会员管理 > 商家管理 > 商家会员列表 ></#if> 余额管理</div>
<!--当前位置结束-->
<!--条件查询-->
<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td >会员名称:</td>
			<td><@s.textfield name="memberfund_name_s" cssStyle="width:245px;" /></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="20%">会员名称</th>
		    <th width="15%">总余额</th>
		    <th width="15%">可用余额</th>
		    <th width="15%">冻结余额</th>
		    <th width="10%">余额调整</th>
		    <th width="10%">查看余额详情</th>
		    <th width="10%">会员余额充值</th>
  </tr>
  
  <#list memberfundList as memberfund>
  <tr>
     <td align="center">
     <#if memberfund.cust_name?if_exists!=''>
     <#if memberfund.cust_name?length lt 20>
     ${memberfund.cust_name?if_exists}
     <#else>
     ${memberfund.cust_name[0..19]}...
     </#if>
     </#if>
     </td>
    <td align="center">${memberfund.fund_num?if_exists}</td>
    <td align="center">${memberfund.use_num?if_exists}</td>
    <td align="center">${memberfund.freeze_num?if_exists}</td>
    <td align="center"><a onclick="linkToInfo('/admin_Memberfund_view.action','memberfund.cust_id=${memberfund.cust_id?if_exists}&enter=${enter?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
    <td align="center"><a onclick="linkToInfo('/admin_Fundhistory_list.action','cust_id_s=${memberfund.cust_id?if_exists}&two_pages_link=no&enter=${enter?if_exists}');"><img src="/include/common/images/view.gif" /></a></td>
    <td align="center"><a onclick="linkToInfo('/admin_Memberfund_add.action','enter=${enter?if_exists}&memberfund.cust_id=${memberfund.cust_id?if_exists}');"><img src="/include/admin/images/memadd.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Fundhistory_list.action','cust_id_s=${cust_id?if_exists}&enter=${enter?if_exists}');" value="查看余额流">
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
	<@s.hidden name="cust_id"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Memberfund_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">资讯标题:</td>
			<td><@s.textfield name="title_s"  /></td>
		</tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		-->
		<!--搜索框隐藏域存放
		    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		</@s.form>
	</div>		  
	--> 
<!--搜索区域结束-->
  </body>
</html>




