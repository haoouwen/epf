<html>
  <head>
    <title>汇率信息列表</title>   
  </head>
<body>
<@s.form action="/admin_Rate_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:系统管理 > 系统管理 > 汇率列表</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td align="right">汇率名称:</td> 
			<td><@s.textfield name="rate_name_s" cssStyle="width:200px;"/></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
      
		     <th width="3%"  ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('rate.rate_id')"/></th>
	    
	     	 <th width="20%" >汇率名称</th>
	     	 
	     	 <th width="15%" >单位</th>
	    
	     	 <th width="15%" >符号</th>
	    
	     	 <th width="15%" >是否启用</th>
	    
	     	 <th width="15%" >汇率</th>
	    
	     	 <th width="10%" >是否为默认</th>
	    
	    	 <th width="7%"  >修改</th>
	    	 
	  </tr>
  
	  <#list rateList as rate>
	  <tr>
	    <td><input type="checkbox" name="rate.rate_id" value="${rate.rate_id?if_exists}"/></td>    
	 	
	    	<td align="center">${rate.rate_name?if_exists}</td>
	    	<td align="center">${rate.rate_unit?if_exists}</td>
	    
	    	<td align="center">${rate.rate_mark?if_exists}</td>
	    
	    	<td align="center">
		    	<#if rate.enables?if_exists="0">
		    	 	<font class="greencolor">是</font>
		    	 <#else>
		        	 <font class="redcolor">否</font>
		    	 </#if>
	    	</td>
	          
	    
	    	<td align="center">${rate.exchangerate?if_exists}</td>
	    	
	    
	    	<td align="center">
		    	<#if rate.endefault?if_exists="0">
		    	 	<font class="greencolor">是</font>
		    	 <#else>
		         	<font class="redcolor">否</font>
		    	 </#if>
	    	</td>
	          
	    <td align="center"><a onclick="linkToInfo('/admin_Rate_view.action','rate.rate_id=${rate.rate_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Rate_add.action','');" value="添加汇率">
     <input type="button" class="rbut" onclick="delInfo('rate.rate_id','/admin_Rate_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="rate.rate_id" value="${rate.rate_id?if_exists}"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
<!--搜索区域结束-->
  </body>
</html>

