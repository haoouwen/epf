<html>
<head>
	<title>积分管理</title>
</head>
<body>
<@s.form action="/bmall_Interhistory_bmalllist.action" method="post" id="indexForm">
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>积分管理</span></h2>
           
            <div class="topup">
        	当前可用积分：<font color="red"><b><#if (memberinter.fund_num)?if_exists!="" && (memberinter.fund_num)?if_exists!=null>${(memberinter.fund_num)?if_exists}<#else>0</#if></b></font>&nbsp;分</p>
         
        </div>
         <table width="100%" cellpadding="0" cellspacing="0" class="usedTab">
         <tr>
         <th  width="10%">操作人</th>
	     <th  width="10%">添加</th>
     	 <th  width="10%">减少</th>
     	 <th  width="15%">余下积分</th>
     	 <th  width="40%">事由</th>
     	 <th  width="10%">操作时间</th>
         <tr>
         <#list interhistoryList as indexhistory>
      <tr>   
 	    <td>
 	    <#if indexhistory.user_name?if_exists!=null && indexhistory.user_name?if_exists!=''>
 	       ${indexhistory.user_name?if_exists}
 	    <#else>
 	      系统操作
 	    </#if>
 	    </td>
 	    
    	<td>${indexhistory.inter_in?if_exists}</td>
    
    	<td>${indexhistory.inter_out?if_exists}</td>
       
        <td>${indexhistory.thisinter?if_exists}</td>
    
        <td>${indexhistory.reason?if_exists}</td>
    
    	<td>${indexhistory.in_date?if_exists}</td>
  </tr>
  </#list>
        </table>
        <div class="listbottom">
        	${pageBar?if_exists}
        </div>
   </div>
</div>
<div class="clear"></div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>  
</@s.form>
</body>
</html>

