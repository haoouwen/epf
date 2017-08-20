<html>
  <head>
    <title>信息提醒管理</title>   
      <script type="text/javascript" src="/include/admin/js/messagealert.js"></script>
  </head>
<body>
<@s.form action="/admin_Messagealert_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:会员管理 > 通讯模板管理 > 信息提醒管理</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
	 
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
      
     	 <th width="40%"> 发送项目</th>
    
     	 <th width="25%">电子邮件</th>
    
     	 <th width="25%">手机短信</th>
    
     	 <th width="10%">站内信</th>
      </tr>
	  
	  <#list messagealertList as messagealert>
		  <tr>
		    
		    	<td align="center">${messagealert.msg_title?if_exists}</td>
		    
		    	<td align="center">
				  <input type="checkbox" name="messagealert.is_send_email" value="${messagealert.is_send_email?if_exists}"  <#if messagealert.is_send_email?if_exists='0'> id='issend' checked='checked'</#if> onclick="selectissend(this,'${messagealert.msg_code?if_exists}','0');"/>
			     <a href="###" onclick="linkToInfo('/admin_Commutemplate_msgalertview.action','commutemplate.temp_code=${messagealert.email_code?if_exists}&is_msg=1');">编辑模板</a>
		    	</td>
		    	<td align="center">
					<input type="checkbox" name="messagealert.is_send_mobile" value="${messagealert.is_send_mobile?if_exists}"
					 <#if messagealert.is_send_mobile?if_exists='0'> checked="checked" </#if>  onclick="selectissend(this,'${messagealert.msg_code?if_exists}','1');"/>
					 <a href="###" onclick="linkToInfo('/admin_Commutemplate_msgalertview.action','commutemplate.temp_code=${messagealert.mobile_code?if_exists}&is_msg=1');">编辑模板</a>
		    	</td>
		    	<td align="center">
				  <input type="checkbox" name="messagealert.is_send_letter" value="${messagealert.is_send_letter?if_exists}"  <#if messagealert.is_send_letter?if_exists='0'> checked="checked" </#if>    onclick="selectissend(this,'${messagealert.msg_code?if_exists}','2');"/>
		    	  <a href="###" onclick="linkToInfo('/admin_Commutemplate_msgalertview.action','commutemplate.temp_code=${messagealert.letter_code?if_exists}&is_msg=1');">编辑模板</a>
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
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>

<!--搜索区域开始-->
  
<!--搜索区域结束-->
  </body>
</html>

