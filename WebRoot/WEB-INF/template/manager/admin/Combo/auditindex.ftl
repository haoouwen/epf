<html>
  <head>
    <title>套餐审核列表</title>   
  </head>
<body>
<@s.form action="/admin_Combo_auditList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：营销推广> 套餐商品 > 套餐审核列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>套餐名称:</td>
			<td><@s.textfield name="combo_name"  cssStyle="width:200px;"/></td>
			<td class="tdpad">审核状态:</td>
			<td>
			<select name="info_state_s" style="width:70px;">
				<option value="" selected>请选择
				<#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='0'>
				       <option value="${infoState.para_value?if_exists}" <#if info_state_s?if_exists==infoState.para_value?if_exists>selected</#if>>${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='2'>
				       <option value="${infoState.para_value?if_exists}" <#if info_state_s?if_exists==infoState.para_value?if_exists>selected</#if>>${infoState.para_key?if_exists}
				    </#if>
    			</#list>
			</select>
			</td>
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
	        <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('combo.trade_id')"/></th>
	   
	   		<th width="5%"  align="center" >排序</th>
	   		
	   		 <th width="24%"  align="center" >套餐名称</th>	
	   			
	    	 <th width="22%"  align="center" >会员名称</th>
	    
	     	 <th width="10%"  align="center" >套餐价格</th>
	    
	     	 <th width="15%"  align="center" >发布时间</th>
	    
	     	 <th width="10%"  align="center" >状态</th>
	    
	    	 <th width="5%" align="center" >操作</th>
	  </tr>
	  
		<#list comboList as combo>
		  <tr>
		    <td><input type="checkbox" name="combo.trade_id" value="${combo.trade_id?if_exists}"/></td>    
		 	
		 	     <td align="center"><input name="isort_no" value="${combo.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" maxLength="11"/></td>
		    	
		    	<td align="left">
		    		<a onclick="linkToInfo('/admin_Combo_view.action','combo.trade_id=${combo.trade_id?if_exists}');" title="${combo.combo_name?if_exists}">
		    	    	 <#if combo.combo_name?if_exists? length lt 36>
		                	${combo.combo_name?if_exists}
		                <#else>
		                	${combo.combo_name[0..35]?if_exists}
		                </#if>
		    		 </a>
		    	</td>
		    
		    	<td align="left">
		    		<#if combo.cust_name?if_exists!=''&& combo.cust_name?if_exists!=null>
		    			${combo.cust_name?if_exists}
			    	<#else>
			    	-
			    	</#if>
		    	</td>
		    
		    	<td align="center">${combo.combo_price?if_exists}</td>
		    	
		    	<td align="center">${combo.in_date?if_exists?string("yyyy-MM-dd HH:mm:ss")}</td>
		    
		    	<td align="center">
		    			
	    			<#list infoStateList as infoState>
	    				<#if combo.info_state?if_exists==infoState.para_value>
	    					<#if infoState.para_value?if_exists=='0'>
						        <font class="redcolor">${infoState.para_key?if_exists}</font>
						    <#elseif infoState.para_value?if_exists=='2'>
						        <font class="bluecolor">${infoState.para_key?if_exists}</font>
	    					<#elseif infoState.para_value?if_exists=='1'>
						        <font class='greencolor'>${infoState.para_key?if_exists}</font>
							<#elseif infoState.para_value?if_exists=='3'>
							    <font class="redcolor">${infoState.para_key?if_exists}</font>
						    </#if>
					    </#if>
	    			</#list>
						
		    	</td>
		          
		    <td align="center"><a onclick="linkToInfo('/admin_Combo_audit.action','combo.trade_id=${combo.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="updateSort('combo.trade_id','isort_no','/admin_Combo_auditUpdateSort.action');" value="排序">
     <input type="button" class="rbut" onclick="delInfo('combo.trade_id','/admin_Combo_auditDelete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>

<!--表单框隐藏域存放-->
  <@s.hidden name="combo_sortno_id" id="combo_sortno_id"/>
  <@s.hidden  name="cust_name_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Combo_auditList.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
        <tr>
			<td class="searchDiv_td">套餐名称:</td> 
			<td><@s.textfield name="combo_name"  cssStyle="width:245px;"/></td>
	   </tr>
	    <tr>
			<td class="searchDiv_td">发布会员:</td> 
			<td><@s.textfield name="cust_name_s"  cssStyle="width:180px;"/></td>
	   </tr>
	    <tr>
			<td class="searchDiv_td">审核状态:</td> 
			<td>
			<select name="info_state_s" style="width:70px;">
				<option value="" selected>请选择
				<#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='0'>
				       <option value="${infoState.para_value?if_exists}" <#if info_state_s?if_exists==infoState.para_value?if_exists>selected</#if>>${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='2'>
				       <option value="${infoState.para_value?if_exists}" <#if info_state_s?if_exists==infoState.para_value?if_exists>selected</#if>>${infoState.para_key?if_exists}
				    </#if>
    			</#list>
			</select>
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

