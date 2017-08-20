<html>
  <head>
    <title>物流模板列表</title>   
  </head>


<body>
<@s.form action="/admin_Shiptemplate_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:商城管理 > 物流管理 > 国内运费模版</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>模板名称:</td>
			<td><@s.textfield name="ship_name_s"  cssStyle="width:245px;"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		      <!--  <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('shiptemplate.ship_id')"/></th>-->
    
     	 <th width="15%"  >模板名称</th>
    
     	 <th width="20%"  >开始地区</th>
    
     	 <th width="10%"  >计价方式</th>
    
     	 <th width="30%"  >支持快递</th>
    
     	 <th width="10%"  >会员名称</th>
    
    
   		 <th width="10%" >操作</th>
  </tr>
  
  <#list shiptemplateList as shiptemplate>
  <tr>
    <!--  <td><input type="checkbox" name="shiptemplate.ship_id" value="${shiptemplate.ship_id?if_exists}"/></td>    -->
 	   
    	<td align="center">${shiptemplate.ship_name?if_exists}</td>
    
    	<td align="center">${shiptemplate.area_attr?if_exists}</td>
    
    	<td align="center">
    	
	    	<#if shiptemplate.valuation_mode='1'>
				按件数
			<#elseif shiptemplate.valuation_mode='2'>
				按重量
			<#elseif shiptemplate.valuation_mode='3'>    
				按体积
			</#if>	
    	
    	</td>
    
    	<td align="center">
    		${shiptemplate.smode_attr?if_exists}    		
    	</td>
    
    	<td align="center">${shiptemplate.cust_name?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Shiptemplate_view.action','shiptemplate.ship_id=${shiptemplate.ship_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
  </tr>
  </#list>
	  </tr>
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
     <!--<input type="button" class="rbut" onclick="linkToInfo('/admin_Shiptemplate_add.action','');" value="添加模板">
     <input type="button" class="rbut" onclick="delInfo('shiptemplate.ship_id','/admin_Shiptemplate_delete.action')" value="删除">-->
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="custId" id="custId"/>
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>


