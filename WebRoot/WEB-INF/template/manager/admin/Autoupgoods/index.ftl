<html>
<head>
	<title>商品上架管理</title>
	<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
	<script type="text/javascript" src="/include/common/js/common.js"></script>
</head>
<body>
<@s.form action="/admin_Autoupgoods_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商品管理 > 商品管理 > 商品上架管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
	     <th width="4%"  align="center"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('autoupgoods.trade_id')"/></th>
     	
     	 <th width="31%"  align="center" >商品名称</th>
    
     	 <th width="15%"  align="center" >上架时间</th>
    
     	 <th width="15%"  align="center" >下架时间</th>
    
     	 <th width="15%"  align="center" >操作时间</th>
	  </tr>
	  
 <#list autoupgoodsList as autoupgoods>
  <tr>
    <td align="center"><input type="checkbox" name="autoupgoods.trade_id" value="${autoupgoods.trade_id?if_exists}"/></td>    
    
    	<td align="center">
    		<#if autoupgoods.goods_name?if_exists!=null && autoupgoods.goods_name?if_exists!=''>
				${autoupgoods.goods_name?if_exists}
			<#else>
			 	-
			</#if>
    	
    	</td>
    
    	<td align="center">
    	    <#if autoupgoods.up_time?if_exists!=null && autoupgoods.up_time?if_exists!=''>
				${autoupgoods.up_time?if_exists}
			<#else>
			 	-
			</#if>
    	
    	</td>
    
    	<td align="center">
    	   <#if autoupgoods.down_time?if_exists!=null && autoupgoods.down_time?if_exists!=''>
				${autoupgoods.down_time?if_exists}
			<#else>
			 	-
			</#if>
    	
    	</td>
    
    	<td align="center">
    	   <#if autoupgoods.in_date?if_exists!=null && autoupgoods.in_date?if_exists!=''>
				${autoupgoods.in_date?if_exists}
			<#else>
			 	-
			</#if>
    	
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
     <input type="button" class="rbut" onclick="delInfo('autoupgoods.trade_id','/admin_Autoupgoods_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
  <@s.hidden id="search_area_attr" name="area_attr_s"/>
  <@s.hidden  name="goods_name_s"/>
  <@s.hidden  name="start_time_s"/>
  <@s.hidden  name="end_time_s"/>
  <@s.hidden  name="rstart_time_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Autoupgoods_list.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">会员名称:</td> 
			<td><@s.textfield name="cust_name_s"  cssStyle="width:180px;"/></td>
	   </tr>
		<tr>
			<td class="searchDiv_td">商品名称:</td> 
			<td><@s.textfield name="goods_name_s"  cssStyle="width:180px;"/></td>
	   </tr>
	   <tr>
			<td class="searchDiv_td">上架时间:</td> 
			<td>
			<@s.textfield id="txtstartDate" name="start_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
			至
			<@s.textfield id="txtendDate" name="end_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
			
			</td>
	   </tr>
	   <tr>
			<td class="searchDiv_td">下架时间:</td> 
			<td>
			<@s.textfield id="startDate" name="rstart_time_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'endDate\\',{d:1})}',readOnly:true})"  /> 
			至
			<@s.textfield id="endDate" name="rend_time_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'startDate\\',{d:1})}',readOnly:true})"  />
			
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
		    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
		    <@s.hidden id="search_area_attr" name="area_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
			<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>


