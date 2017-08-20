<html>
  <head>
    <title>资金冻结列表</title> 
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
     <!--所属分类开始-->
	    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","group");
      });
	</script>
	<!--所属分类结束-->
  </head>
  <body>
<@s.form action="/admin_Groupgoods_applyList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：营销推广 > 团购商品 > 资金冻结列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>团购标题:</td>
			<td><@s.textfield name="group_title_s"  cssStyle="width:200px;"/></td>
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
		    <th width="22%"  >团购标题</th>
		    <th width="14%"  >会员名称</th>
		    <th width="13%"  >申请时间</th>
		    <th width="13%"  >冻结状态</th>
		    <th width="6%"  >审核状态</th>
		    <th width="5%"  >操作</th>
	  </tr>
	  
	   <#list groupgoodsList as groupgoods>
	    <tr >
		     <td align="center">
				<a onclick="linkToInfo('/admin_Groupgoods_view.action','groupgoods.trade_id=${groupgoods.trade_id?if_exists}');" title="${groupgoods.group_title?if_exists}">
	        	   <#if groupgoods.group_title?if_exists? length lt 36>
	                 ${groupgoods.group_title?if_exists}
	                <#else>
	                 ${groupgoods.group_title[0..35]?if_exists}..
	                </#if>
         	   </a> 
                <#if groupgoods.active_state?if_exists="1">
                	<font class="bluecolor">未开始</font>
                <#elseif groupgoods.active_state?if_exists="2">
                	<font color="green">进行中</font>
                <#elseif groupgoods.active_state?if_exists="3">
                	<font color="red">已结束</font>
                </#if>
		    </td>
		    <td align="center">
				 <#if groupgoods.cust_name?if_exists!=''&& groupgoods.cust_name?if_exists!=null>
    			${groupgoods.cust_name?if_exists}
		    	<#else>
		    	-
		    	</#if>	
		    </td>
		    <td align="center">
				 <#if groupgoods.apply_time?if_exists!=''&& groupgoods.apply_time?if_exists!=null>
    			${groupgoods.apply_time?if_exists}
		    	<#else>
		    	-
		    	</#if>	
		    </td>
		    <td align="center">
				<#if groupgoods.apply_state?if_exists=='1'>
				<font color='red'>被冻结</font>
				<#elseif groupgoods.apply_state?if_exists=='2'>
				<font color='blue'>申请解冻</font>
			   <#elseif groupgoods.apply_state?if_exists=='3'>
		        <font class="green">成功解冻</font>
			   <#else>
			     未申请解冻
			    </#if>
		    </td>
		    
		     <td align="center">
			     
			     <#list infoStateList as infoState>
    				<#if groupgoods.info_state?if_exists==infoState.para_value>
    					<#if infoState.para_value?if_exists='1'>
					        <font class='greencolor'>${infoState.para_key?if_exists}</font>
						<#elseif infoState.para_value?if_exists='3'>
						    <font class="redcolor">${infoState.para_key?if_exists}</font>
    					<#elseif infoState.para_value?if_exists='0'>
					       <font class="redcolor">${infoState.para_key?if_exists}</font>
					    <#elseif infoState.para_value?if_exists='2'>
					        <font class="bluecolor">${infoState.para_key?if_exists}</font>
					    </#if>
					    <#break/>
				    </#if>
    			 </#list>
		    </td>
		     <td align="center">
		     	<#if groupgoods.apply_state?if_exists=='2' && groupgoods.active_state?if_exists="3">
					<input type="button" class="rbut"  onclick="linkToInfo('/admin_Groupgoods_updateApply.action','groupgoods.trade_id=${groupgoods.trade_id?if_exists}&groupgoods.apply_state=3');" value="解除冻结">
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
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="cust_name_s"/>
  <@s.hidden  name="cat_attr_s"/>
  <@s.hidden  name="st_put_date_s"/>
  <@s.hidden  name="st_en_date_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Groupgoods_applyList.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td class="searchDiv_td">发布会员:</td>
			<td><@s.textfield name="cust_name_s"  /></td>
		</tr>
		<tr>
			<td class="searchDiv_td">团购标题:</td>
			<td><@s.textfield name="group_title_s"  /></td>
		</tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
				<input type="button" name="search" value="搜索" onclick="showSearchDiv('','cat_attr','','search_cat_attr','form_search_id');"/>&nbsp;
			<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		<!--搜索框隐藏域存放-->
		    <@s.hidden id="search_cat_attr" name="cat_attr_s"/>
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
