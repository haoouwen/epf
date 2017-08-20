<html>
  <head>
    <title>预售商品审核</title>   
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
     <!--所属分类开始-->
	    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","direct");
      });
	</script>
	<!--所属分类结束-->
  </head>
<body>
<@s.form action="/admin_Directsell_auditList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：营销推广 > 预售商品 > 预售商品审核</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>预售标题:</td>
			<td><@s.textfield name="sale_title_s"  cssStyle="width:200px;"/></td>
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
	         <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('directsell.trade_id')"/></th>
	   		
	   	     <th width="5%"  align="center" >排序</th>
	   		
	         <th width="32%"  align="center" >预售标题</th>
	   		
	     	 <th width="12%"  align="center" >预售开始时间</th>
	    
	     	 <th width="12%"  align="center" >预售结束时间</th>
	    
	     	 <th width="12%"  align="center" >发布时间</th>
	     	 
	     	 <th width="13%"  align="center" >会员名称</th>
	    
	     	 <th width="7%"  align="center" >审核状态</th>
	    
	         <th width="7%" align="center" >操作</th>
	  </tr>
	  
		<#list directsellList as directsell>
		  <tr>
		    <td><input type="checkbox" name="directsell.trade_id" value="${directsell.trade_id?if_exists}"/></td>    
		 	
		    	<td align="center"><input name="isort_no" value="${directsell.sort?if_exists}" style="width:50px" onkeyup="checkNum(this)" maxLength="6"/></td>
		    	
		    	<td align="left">
		    		<a onclick="linkToInfo('/admin_Directsell_audit.action','directsell.trade_id=${directsell.trade_id?if_exists}');" title="${directsell.sale_title?if_exists}">
		        	  	 <#if directsell.sale_title?if_exists? length lt 36>
		                	 ${directsell.sale_title?if_exists}
		                <#else>
		                	 ${directsell.sale_title[0..35]?if_exists}..
		                </#if>
		           </a>     
		    	</td>
		    	
		    	<td align="center">${directsell.start_time?if_exists?string("yyyy-MM-dd HH:mm:ss")}</td>
		    
		    	<td align="center">${directsell.end_time?if_exists?string("yyyy-MM-dd HH:mm:ss")}</td>
		    
		    	<td align="center">${directsell.in_date?if_exists?string("yyyy-MM-dd HH:mm:ss")}</td>
		    	
		    	<td align="center">
		    		<#if directsell.cust_name?if_exists!=''&& directsell.cust_name?if_exists!=null>
		    			${directsell.cust_name?if_exists}
			    	<#else>
			    	-
			    	</#if>
		    	</td>
		    
		    	<td align="center">
					   <#list infoStateList as infoState>
						<#if directsell.info_state?if_exists==infoState.para_value>
							<#if infoState.para_value?if_exists='0'>
						       <font class="redcolor">${infoState.para_key?if_exists}</font>
						    <#elseif infoState.para_value?if_exists='2'>
						        <font class="bluecolor">${infoState.para_key?if_exists}</font>
							<#elseif infoState.para_value?if_exists='1'>
						        <font class='greencolor'>${infoState.para_key?if_exists}</font>
							<#elseif infoState.para_value?if_exists='3'>
							    <font class="redcolor">${infoState.para_key?if_exists}</font>
						    </#if>
						    <#break/>
					    </#if>
					</#list>
		    	</td>
		    
		    <td align="center"><a onclick="linkToInfo('/admin_Directsell_audit.action','directsell.trade_id=${directsell.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="updateSort('directsell.trade_id','isort_no','/admin_Directsell_auditUpdateSort.action');" value="排序">
     <input type="button" class="rbut" onclick="delInfo('directsell.trade_id','/admin_Directsell_auditDelete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="directsell_sortno_id" id="directsell_sortno_id"/>
  <@s.hidden name="cat_attr_s"/>
  <@s.hidden  name="st_put_date_s"/>
  <@s.hidden  name="en_put_date_s"/>
  <@s.hidden  name="st_en_date_s"/>
  <@s.hidden  name="en_en_date_s"/>
  <@s.hidden  name="cust_name_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Directsell_auditList.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
	 <tr>
			<td class="searchDiv_td">发布会员:</td> 
			<td><@s.textfield name="cust_name_s"  cssStyle="width:180px;"/></td>
	 </tr>
	 <tr>
			<td class="searchDiv_td">预售标题:</td> 
			<td><@s.textfield name="sale_title_s"  cssStyle="width:180px;"/></td>
	 </tr>
	 <tr>
			<td class="searchDiv_td">所属分类:</td> 
			<td>
			 <table>
	         		<tr>
	         			<td class="tdbottom">
	         				<div id="catDiv" style="margin-left:-5px;"></div>
	         			</td>
	              	</tr>
	         </table>
	        </td>
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
			<td class="searchDiv_td">开始时间:</td>
			<td>
			 <input type="text"  name="st_put_date_s" value="${st_put_date_s?if_exists}" class="Wdate upWdate" style="width:142px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'updown2\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
			&nbsp;至&nbsp;
	        <input type="text"  name="en_put_date_s"  value="${en_put_date_s?if_exists}" class="Wdate upWdate" style="width:142px;" id="updown2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown1\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
	         </td>
	 </tr>
	  <tr>
			<td class="searchDiv_td">结束时间:</td>
			<td>
			 <input type="text"  name="st_en_date_s" value="${st_en_date_s?if_exists}" class="Wdate upWdate" style="width:142px;" id="updown3" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'updown4\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
			&nbsp;至&nbsp;
	        <input type="text"  name="en_en_date_s"  value="${en_en_date_s?if_exists}" class="Wdate upWdate" style="width:142px;" id="updown4" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown3\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
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
			<@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>

