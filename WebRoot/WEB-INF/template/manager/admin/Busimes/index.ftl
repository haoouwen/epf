<html>
  <head>
    <title>商家留言列表</title>   
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
  </head>
  <body>
<@s.form action="/admin_Busimes_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:商城管理 > 店铺管理 >  商家留言列表
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="sc"><a onclick="delInfo('busimes.trade_id','/admin_Busimes_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
         <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('busimes.trade_id')"/></td>
    
     	 <td width="10%"  align="center" class="top_td">商店名称</td>
    
     	 <td width="10%"  align="center" class="top_td">留言客户</td>
    
     	 <td width="10%"  align="center" class="top_td">留言内容</td>
    
     	 <td width="10%"  align="center" class="top_td">留言时间</td>
    
     	 <td width="10%"  align="center" class="top_td">回复人</td>
    
     	 <td width="10%"  align="center" class="top_td">回复内容</td>
    
     	 <td width="10%"  align="center" class="top_td">回复时间</td>
    
     	 <td width="10%"  align="center" class="top_td">状态</td>

         <td width="5%" align="center" class="top_td">操作</td>
  </tr>
  
  <#list busimesList as busimes>
  <tr>
    <td><input type="checkbox" name="busimes.trade_id" value="${busimes.trade_id?if_exists}"/></td>    
 	
    	<td align="center">${busimes.cust_name?if_exists}</td>
    
    	<td align="center">${busimes.user_name?if_exists}</td>
    
    	<td align="center">
    	 <#if busimes.msg_content?length lt 10 > 
    	     ${busimes.msg_content?if_exists}
    	     <#else>
    	     ${busimes.msg_content?if_exists[0..9]}
    	 </#if>
    	</td>
    
    	<td align="center">${busimes.msg_date?if_exists}</td>
    
    	<td align="center"> <#if busimes.get_user_id?if_exists==''>暂无回复<#else>${busimes.get_user_id?if_exists}</#if></td>
    
    	<td align="center">${busimes.re_content?if_exists}</td>
    
    	<td align="center">${busimes.re_date?if_exists}</td>
    
    	<td align="center"><#if busimes.is_enable?if_exists=='0'><font color='green'>有效</font><#else><font color='red'>无效</font></#if></td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Busimes_view.action','busimes.trade_id=${busimes.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
  </tr>
  </#list>
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>


<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
	   <tr>
		<td align="right">商店名称:</td> 
		<td><@s.textfield name="cust_name_s"  cssStyle="width:245px;"/></td>
	   </tr>
	   
	   <tr>
		<td align="right">客户名称:</td> 
		<td><@s.textfield name="user_name_s"  cssStyle="width:245px;"/></td>
	   </tr>
	   
	   <tr>
		<td align="right">留言时间段:</td>
		<td>
		<@s.textfield id="txtstartDate" name="start_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:0})}',readOnly:true})"  /> 
		至
		<@s.textfield id="txtendDate" name="end_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:0})}',readOnly:true})"  />
		
		</td>
	  </tr>	 
	  <tr>
		<td align="right">回复时间段:</td>
		<td>
		<@s.textfield id="txtstartDate" name="rstart_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
		至
		<@s.textfield id="txtendDate" name="rend_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
		               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
		
		</td>
	  </tr>	 
	  
	   <tr>
		<td align="right">是否有效:</td>
		<td><@s.select name="is_enable_s" list=r"#{'':'请选择','0':'有效','1':'无效'}"/> </td>
	   </tr>
		<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="searchSubmit();" />
			<input type="button" name="close" value="关闭" onclick="closeSearch();"/>
		</td>
	</tr>
	</table>
</div>

</@s.form>
</body>
<body>
<@s.form action="/admin_Busimes_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 店铺管理 >  商家留言列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>商店名称:</td>
			<td><@s.textfield name="cust_name_s"  cssStyle="width:245px;"/></td>
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
         <th width="3%" th><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('busimes.trade_id')"/></th>
    
     	 <th width="10%"  align="center" th>商店名称</th>
    
     	 <th width="10%"  align="center" th>留言客户</th>
    
     	 <th width="10%"  align="center" th>留言内容</th>
    
     	 <th width="10%"  align="center" th>留言时间</th>
    
     	 <th width="10%"  align="center" th>回复人</th>
    
     	 <th width="10%"  align="center" th>回复内容</th>
    
     	 <th width="10%"  align="center" th>回复时间</th>
    
     	 <th width="10%"  align="center" th>状态</th>

         <th width="5%" align="center" th>操作</th>
	  </tr>
	  
	 <#list busimesList as busimes>
	  <tr>
	    <td><input type="checkbox" name="busimes.trade_id" value="${busimes.trade_id?if_exists}"/></td>    
	 	
	    	<td align="center">${busimes.cust_name?if_exists}</td>
	    
	    	<td align="center">${busimes.user_name?if_exists}</td>
	    
	    	<td align="center">
	    	 <#if busimes.msg_content?length lt 10 > 
	    	     ${busimes.msg_content?if_exists}
	    	     <#else>
	    	     ${busimes.msg_content?if_exists[0..9]}
	    	 </#if>
	    	</td>
	    
	    	<td align="center">${busimes.msg_date?if_exists}</td>
	    
	    	<td align="center"> <#if busimes.get_user_id?if_exists==''>暂无回复<#else>${busimes.get_user_id?if_exists}</#if></td>
	    
	    	<td align="center">${busimes.re_content?if_exists}</td>
	    
	    	<td align="center">${busimes.re_date?if_exists}</td>
	    
	    	<td align="center"><#if busimes.is_enable?if_exists=='0'><font color='green'>有效</font><#else><font color='red'>无效</font></#if></td>
	          
	    <td align="center"><a onclick="linkToInfo('/admin_Busimes_view.action','busimes.trade_id=${busimes.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="delInfo('busimes.trade_id','/admin_Busimes_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
  <@s.hidden  name="user_name_s"/>
  <@s.hidden  name="start_time_s"/>
  <@s.hidden  name="end_time_s"/>
  <@s.hidden  name="rstart_time_s"/>
  <@s.hidden  name="rend_time_s"/>
  <@s.hidden  name="is_enable_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Busimes_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
	   <tr>
		<td class="searchDiv_td">商店名称:</td> 
		<td><@s.textfield name="cust_name_s"  cssStyle="width:245px;"/></td>
	   </tr>
	   
	   <tr>
		<td class="searchDiv_td">客户名称:</td> 
		<td><@s.textfield name="user_name_s"  cssStyle="width:245px;"/></td>
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
		<td class="searchDiv_td">是否有效:</td>
		<td><@s.select name="is_enable_s" list=r"#{'':'请选择','0':'有效','1':'无效'}"/> </td>
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

