<html>
<head>
	<title>余额提取管理</title>
</head>
<body>

<@s.form action="/bmall_Fundtocash_list.action" method="post" id="indexForm">
<!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>余额提取</span></h2>
       
       <div class="topup">
			  <span>可提现余额<b class="mred">￥${memberfund.use_num?if_exists}</b></span>
             <input type="button" value="申请提现" class="graybut" onclick="linkToInfo('/bmall_Fundtocash_add.action','parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');"/>
       </div>
       
       <table width="100%" cellpadding="0" cellspacing="0" class="usedTab">
         <tr >
         <th  width="10%">提现余额</th>
         
	    <th  width="15%">收款方式</th>
    
     	 <th  width="20%">收款账号</th>
     	 
     	 <th  width="15%">账号姓名</th>
    
     	 <th  width="10%">状态</th>
    
     	 <th  width="10%">申请时间</th>
     	 
     	 <th  width="10%">查看</th>
         </tr>
         <#list fundtocashList as fundtocash >
      <tr> 
    	<td>${fundtocash.fund_num?if_exists}</td>
    
    	<td>
    	 <#list commparaList as comlist>
	          <#if fundtocash.getcash_type==comlist.para_value>
				       ${comlist.para_key}
	           </#if>
	     </#list>
    	</td>
    
    	<td>${fundtocash.account?if_exists}</td>
    
    	<td>${fundtocash.account_name?if_exists}</td>
    	
    	<td><a onclick="linkToInfo('/bmall_Fundtocash_list.action','order_state=${fundtocash.order_state?if_exists}');">
        <#if fundtocash.order_state?if_exists=='0'><font color='red'>未审核</font></a></#if>
        <a onclick="linkToInfo('/bmall_Fundtocash_list.action','order_state=${fundtocash.order_state?if_exists}');">
        <#if fundtocash.order_state?if_exists=='1'><font color='blue'>审核通过</font></a></#if>
        <a onclick="linkToInfo('/bmall_Fundtocash_list.action','order_state=${fundtocash.order_state?if_exists}');">
        <#if fundtocash.order_state?if_exists=='2'><font color='blue'>审核未通过</font></a></#if>
        <a onclick="linkToInfo('/bmall_Fundtocash_list.action','order_state=${fundtocash.order_state?if_exists}');">
	    <#if fundtocash.order_state?if_exists=='3'><font color='green'>已处理</font></a></#if>
	    <a onclick="linkToInfo('/bmall_Fundtocash_list.action','order_state=${fundtocash.order_state?if_exists}');">
	    <#if fundtocash.order_state?if_exists=='4'><font color='red'>作废</font></a></#if>
	    </a></td>
    	
    	<td>${fundtocash.in_date?if_exists}</td>
    	
    <td>
    <a onclick="linkToInfo('/bmall_Fundtocash_view.action','fundtocash.trade_id=${fundtocash.trade_id?if_exists}');"><img src="/include/bmall/images/view.gif"></a>
    </td>
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

