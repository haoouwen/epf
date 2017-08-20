<#setting datetime_format="yyyy-MM-dd HH:mm:ss"/> 
<html>
<head>
	<title>冻结资金明细列表</title>
</head>
<body>

<@s.form action="/bmall_Fundhistory_frostList.action" id="indexForm">
<div class="rightside f_right">
<div class="postion">
	  <a href="#">账号管理</a><span>></span><a href="#">余额管理</a><span>></span><a href="#">查看余额</a><span>></span><a href="#">冻结资金明细列表</a>
</div>
   <div class="ropot">
       <h2 class="rotitle">
       		<span><td class="fthstyle1">冻结资金明细列表</td></span>
       </h2>
       
       <table cellspacing="0" class="bmall_list_table">
          <table cellspacing="0" class="bmall_list_table" width="100%">
         <tr >
         <th class="fthstyle1" width="10%">冻结/解冻</th>

     	 <th class="fthstyle1" width="10%">余额</th>
     	 
     	 <th class="fthstyle1" width="10%">操作人</th>
    
     	 <th class="fthstyle1" width="53%">事由</th>
    
     	 <th class="fthstyle1" width="17%">操作时间</th>
         </tr>
        <#list fundhistoryList as fundhistory>
      <tr align="center"> 
    	<td>
    		<#if fundhistory.action_type='3'>
    		  <font class="redcolor">冻结</font>
    		<#elseif fundhistory.action_type='4'>
    		  <font class="greencolor">解冻</font>
    		</#if>
    	</td>
    
    	<td>
			<#if fundhistory.action_type='3'>
    		  <font class="redcolor">-${fundhistory.fund_out?string("#0.00")?if_exists}</font>
    		<#elseif fundhistory.action_type='4'>
    		  <font class="greencolor">${fundhistory.fund_in?string("#0.00")?if_exists}</font>
    		</#if>
		</td>
    
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
</@s.form>
</body>
</html>

