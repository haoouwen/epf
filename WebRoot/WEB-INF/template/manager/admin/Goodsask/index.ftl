<html>
  <head>
    <title>商品咨询</title>   
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Goodsask_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 商品管理 > 商品咨询列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>商品名称:</td>
			<td><@s.textfield name="goods_name_s"  cssStyle="width:245px;"/></td>
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
		    <th width="3%" > <input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('goodsask.trade_id')"/></th>
		    <th width="25%"  >咨询商品</th>
		    <th width="12%"  >咨询类型</th>
		    <th width="12%"  >咨询人</th>
		    <th width="14%"  >咨询时间</th>
		    <th width="12%"  >回复人</th>
		    <th width="14%"  >回复时间</th>
		    <th width="5%"  >是否有效</th>
		    <th width="5%"  >操作</th>
	  </tr>
	  
	   <#list goodsaskList as goodsask>
	    <tr >
		    <td><input type="checkbox" name="goodsask.trade_id" value="${goodsask.trade_id?if_exists}"/></td>
		    <td align="center">
				${goodsask.goods_name?if_exists}
		    </td>
		     <td align="center">
				${goodsask.c_type?if_exists}
		    </td>
		     <td align="center">
				${goodsask.user_name?if_exists}
		    </td>
		     <td align="center">
				${goodsask.c_date?if_exists}
		    </td>
		     <td align="center">
				<#if goodsask.re_cust_id?if_exists==''>还没有回复！<#else>${goodsask.re_cust_id?if_exists}</#if>
		    </td>
		     <td align="center">
				${goodsask.re_date?if_exists}
		    </td>
		     <td align="center">
				<#if goodsask.is_enable?if_exists=='0'><font color="green">有效</font><#else><font color="red">无效</font></#if>
		    </td> <td align="center">
				<a onclick="linkToInfo('/admin_Goodsask_view.action','goodsask.trade_id=${goodsask.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
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
     <input type="button" class="rbut"onclick="delInfo('goodsask.trade_id','/admin_Goodsask_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
   <@s.hidden  name="c_type_s"/>
   <@s.hidden  name="is_enable_s"/>
   <@s.hidden  name="start_time_s"/>
   <@s.hidden  name="rend_time_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Goodsask_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">商品名称:</td>
			<td><@s.textfield name="goods_name_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">咨询类型:</td>
			<td><@s.select name="c_type_s" list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="请选择"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">是否有效:</td>
			<td><@s.select name="is_enable_s" list=r"#{'':'请选择','0':'有效','1':'无效'}"/></td>
		</tr>
		<tr>
			<td class="searchDiv_td">留言时间段:</td>
			<td>
				<@s.textfield id="txtstartDate" name="start_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
			   至
				<@s.textfield id="txtendDate" name="end_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
			</td>
		</tr>
		<tr>
			<td class="searchDiv_td">回复时间段:</td>
			<td>
				<@s.textfield id="txtstartDate" name="rstart_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
					               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
				   至
					<@s.textfield id="txtendDate" name="rend_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
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
