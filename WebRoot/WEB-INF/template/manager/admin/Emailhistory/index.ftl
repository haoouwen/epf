<html>
  <head>
    <title>电子邮件管理</title>   
  </head>
<body>
<@s.form action="/admin_Emailhistory_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：会员管理 > 会员相关 > 电子邮件管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>操作人:</td>
			<td><@s.textfield name="user_name_s"  cssStyle="width:245px;"/></td>
			 <td class="tdpad">收件人邮箱:</td>
			<td>
				<@s.textfield name="get_email_s"  cssStyle="width:245px;"/>
			</td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
	        <th width="5%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('emailhistory.trade_id')"/></th>
	    
	     	 <th width="40%"  align="center" >邮件标题</th>
	    
	     	 <th width="20%"  align="center" >收件人邮箱</th>
	    
	     	 <th width="20%"  align="center" >发送时间</th>
	    
	     	 <th width="15%"  align="center" >操作人</th>	  
     </tr>
	  
			 <#list emailhistoryList as emailhistory>
			  <tr>
			    <td><input type="checkbox" name="emailhistory.trade_id" value="${emailhistory.trade_id?if_exists}"/></td>    
			    
			    	<td align="center">${emailhistory.title?if_exists}</td>
			    
			    	<td align="center">${emailhistory.get_email?if_exists}</td>
			    
			    	<td align="center">${emailhistory.send_date?string("yyyy-MM-dd HH:mm:ss")}</td>
			    
			    	<td align="center">
			    	    <#if emailhistory.user_name?if_exists!=null && emailhistory.user_name?if_exists!=''>
				    		 ${emailhistory.user_name?if_exists}
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Emailhistory_add.action','');" value="发送邮件">
     <input type="button" class="rbut" onclick="delInfo('emailhistory.trade_id','/admin_Emailhistory_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>

