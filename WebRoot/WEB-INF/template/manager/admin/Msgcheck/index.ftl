<html>
  <head>
    <title>记录商城的活动管理列表</title>   
  </head>
  <body>
<@s.form action="/admin_Msgcheck_list.action" method="post" id="indexForm">
 <div class="postion">当前位置:会员管理 > 会员管理 > 验证码</div>
 <div class="rtdcont">
 <div class="rtable">
  <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
  <tr>
     	 <th width="10%"> 手机/邮箱</th>
    
     	 <th width="10%">验证码</th>
    
     	 <th width="10%">类型</th>
    
     	 <th width="10%">发送时间</th>
    
  </tr>
  
  <#list msgcheckList as msgcheck>
  <tr>
 	
    	<td align="center">${msgcheck.cp_phone?if_exists}</td>
    
    	<td align="center">${msgcheck.cp_check?if_exists}</td>
    
    	<td align="center"><#if msgcheck.cp_type=="1">注册验证码<#elseif msgcheck.cp_type=="3">手机验证<#elseif msgcheck.cp_type=="4">邮箱验证</#if></td>
    
    	<td align="center">${msgcheck.send_time?if_exists}</td>
          
  </tr>
  </#list>
</table>
</div>
   <div class="pages">
     ${pageBar?if_exists}
   </div>
 <div class="clear"/>
</div>
</@s.form>
</body>
</html>

