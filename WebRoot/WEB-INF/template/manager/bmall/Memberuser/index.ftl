 <html>
<head>
<title>账户信息</title>
</head>
<body>
<@s.form action="/bmall_Goodseval_list.action" method="post" id="indexForm">
   <div class="rightside f_right">
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">账户信息</td>
       </span></h2>
       <div class="rosearch">
         <input type="text" name="" value="商品名称，商品编号，订单编号" class="srtext">
         <input type="button" name="" value="" class="sbut">
       </div>
   
      <table cellspacing="0" class="bmall_list_table">
         <tr>
         
	     <th class="fthstyle1">用户名/子账号</th>
    
     	 <th class="fthstyle1">真实姓名</th>
    
     	 <th class="fthstyle1">性别</th>
    
     	 <th class="fthstyle1">手机号</th>
     	 
         <tr>
         <#list memberuserList as memberuser>
      <tr style="text-align:center;">   
 	
    	<td align="left">${memberuser.user_name?if_exists}</td>
    	
    	</td>
    
    	<td >
		 <#if memberuser.real_name?if_exists!=null && memberuser.real_name?if_exists!=''>
    		${memberuser.real_name?if_exists}
    	<#else>
    	 	-
    	</#if>
    	
    	</td>
    
    	<td >
    	<#if memberuser.sex?if_exists!='1'>
			    	女
			    	<#else>
			    	 	男
			    	</#if>
    	
    	</td>
    	
    	<td >
    	<#if memberuser.cellphone?if_exists!=null && memberuser.cellphone?if_exists!=''>
    		${memberuser.cellphone?if_exists}
    	<#else>
    	 	-
    	</#if>
    	
    	</td>
    	
  </tr>
  </#list>
       </table>
        <div class="listbottom">
        ${pageBar?if_exists}
        </div>
      </div>     
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

