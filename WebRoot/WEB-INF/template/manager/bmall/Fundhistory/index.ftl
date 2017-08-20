<#setting datetime_format="yyyy-MM-dd HH:mm:ss"/> 
<html>
<head>
	<title>交易流水列表</title>
</head>
<body>

<@s.form action="/bmall_Fundhistory_list.action" id="indexForm">
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>交易流水</span></h2>
       
         <table width="100%" cellpadding="0" cellspacing="0" class="usedTab">
         <tr >
         <th  width="10%">收入</th>
         
	     <th  width="10%">支出</th>
    
     	 <th  width="10%">余额</th>
     	 
     	 <th  width="10%">操作人</th>
    
     	 <th  width="43%">事由</th>
    
     	 <th  width="17%">操作时间</th>
         </tr>
        <#list fundhistoryList as fundhistory>
      <tr align="center"> 
    	<td>${fundhistory.fund_in?string("#0.00")?if_exists}</td>
    
    	<td>${fundhistory.fund_out?string("#0.00")?if_exists}</td>
    
    	<td>${fundhistory.balance?string("#0.00")?if_exists}</td>
    
    	<td>
    	<#if fundhistory.user_name==''>
    	系统操作
    	<#else>
    	${fundhistory.user_name?if_exists}
    	</#if>
    	</td>
    	
    	<td align="left">${fundhistory.reason?if_exists}</td>
    	
    	<td>${fundhistory.in_date?if_exists}</td>
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

