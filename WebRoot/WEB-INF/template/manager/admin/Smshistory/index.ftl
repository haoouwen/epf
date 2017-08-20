<html>
  <head>
    <title>手机短信管理</title>   
  </head>

<body>
<@s.form action="/admin_Smshistory_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:会员管理 > 会员相关 > 手机短信管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td >操作人:</td> 
			<td><@s.textfield name="user_name_s"/></td>
			<td class="tdpad">短信标题:</td> 
			<td><@s.textfield name="celltitle_s"/></td>
			<td class="tdpad">接收人手机号码:</td> 
			<td><@s.textfield name="cellphones_s" /></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
	  <tr>
		     <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('smshistory.trade_id')"/></th>
	   
	     	 <th width="15%"  >短信标题</th>
	    
	     	 <th width="60%"  >接收人手机号码</th>
	    
	     	 <th width="12%"  >发送时间</th>
	    
	     	 <th width="8%"  >操作人</th>
	     	 
	  </tr>
  
	  <#list smshistoryList as smshistory>
	  <tr>
	    <td><input type="checkbox" name="smshistory.trade_id" value="${smshistory.trade_id?if_exists}"/></td>    
	    
	    	<td align="center">${smshistory.celltitle?if_exists}</td>
	    
	    	<td align="center">
	    	<#if smshistory.cellphones?length lt 50>
	    	
	    	${(smshistory.cellphones)?if_exists}

	    	<#else> 
	    	
	    	<a href="javascript:void(0);" title="${(smshistory.cellphones)?if_exists}" style="text-decoration:none;">
	    	
	    	${smshistory.cellphones[0..49]}...
	    	
	    	</a>
	    	</#if>  
	    	
	        </td>
	    
	    	<td align="center">${smshistory.send_date?string("yyyy-MM-dd HH:mm:ss")}</td>
	    
	    	<td align="center">
	    	<#if smshistory.user_name?if_exists!=null && smshistory.user_name?if_exists!=''>
				    		 ${smshistory.user_name?if_exists}
			<#elseif smshistory.sys_name?if_exists!=null && smshistory.sys_name?if_exists!=''>
				    		 ${smshistory.sys_name?if_exists}
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Smshistory_add.action','');" value="发送手机短信">
     <input type="button" class="rbut" onclick="delInfo('smshistory.trade_id','/admin_Smshistory_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>

