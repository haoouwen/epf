<html>
  <head>
    <title>查看积分流</title>
  </head>
  <body>
<@s.form action="/admin_Interhistory_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：<#if enter?if_exists==''>财务统计 >财务管理 ><#elseif enter?if_exists=='0'> 会员管理 > 会员管理 > 会员列表 ><#elseif enter?if_exists=='1'> 会员管理 > 商家管理 > 企业会员列表 ></#if> 会员积分管理表 > 查看积分流 </div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>积分:</td>
			<td><@s.textfield name="integer_num"/>-<@s.textfield name="last_integer_num"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="15%"  >会员名称</th>
		    <th width="10%"  >添加</th>
		    <th width="10%"  >减少</th>
		    <th width="10%"  >余下积分数</th>
		    <th width="10%"  >操作人</th>
		    <th width="10%"  >操作时间</th>
		    <th width="35%"  >事由</th>
	  </tr>
	  
	 <#list interhistoryList as interhistory>
	    <tr >
		    <td align="center">
				<#if interhistory.cust_name?if_exists!=''>
			    <#if interhistory.cust_name?length lt 16>
			    ${interhistory.cust_name?if_exists}
			    <#else>
			    ${interhistory.cust_name[0..15]}...
			    </#if>
			    </#if>
		    </td>
		    <td align="center">
				${interhistory.inter_in?if_exists}
		    </td>
		    <td align="center">
				${interhistory.inter_out?if_exists}
		    </td>
		    <td align="center">
				${interhistory.thisinter?if_exists}
		    </td>
		    <td align="center">
				<#if interhistory.user_name==''>
			    系统操作
			    <#else>
			    ${interhistory.user_name?if_exists}
			    </#if>
		    </td>
		    <td align="center">
				${interhistory.in_date?if_exists}
		    </td>
		    <td align="center">
				 ${interhistory.reason?if_exists}
		    </td>
		    <td align="center">
				<@s.hidden name="cust_id_s" value="${interhistory.cust_id}"/>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Memberinter_list.action','custId=${cust_id_s?if_exists}&enter=${enter?if_exists}');" value="返回">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_News_list.action" method="post"  id="form_search_id">
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
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
