 <html>
<head>
<title>咨询列表</title>
</head>
<body>
<@s.form action="/bmall_Goodsask_list.action" method="post">
   <div class="rightside f_right">
     <div class="rpostion"><h2>
	         <td class="fthstyle1">交易管理</td>
     </h2></div>
     <div class="ropot">
       <h2 class="rotitle"><span>
	         <td class="fthstyle1">我的咨询</td>
       </span></h2>
       <div class="rosearch">
         <input type="text" name="" value="商品名称，商品编号，订单编号" class="srtext">
         <input type="button" name="" value="" class="sbut">
       </div>
       
       <table cellspacing="0">
         <tr>
         
	     <td class="fthstyle1">咨询商品</td>
    
     	 <td class="fthstyle1">咨询类型</td>
     	 
     	 <td class="fthstyle1">咨询人</td>
    
     	 <td class="fthstyle1">咨询时间</td>
    
     	 <td class="fthstyle1">回复人</td>
    
     	 <td class="fthstyle1">回复时间</td>
    
     	 <td class="fthstyle1">是否有效</td>
     	 
     	 <td class="fthstyle1">操作</td>
         <tr>
         <#list goodsaskList as goodsask>
      <tr>   
 	
    	<td align="center">${goodsask.goods_name?if_exists}</td>
    
    	<td align="center">${goodsask.c_type?if_exists}</td>
    
    	<td align="center">${goodsask.user_name?if_exists}</td>
    
    	<td align="center">${goodsask.c_date?if_exists}</td>
    
        <td align="center">
        <#if goodsask.re_cust_id?if_exists==''>还没有回复！<#else>${goodsask.re_cust_id?if_exists}</#if>
        </td>
    
    	<td align="center">${goodsask.re_date?if_exists}</td>
    	<td align="center">
    	<#if goodsask.is_enable?if_exists=='0'>有效<#else>无效</#if>
    	</td>  
    <td align="center">
    <a onclick="linkToInfo('/bmall_Goodsask_audit.action','goodsask.trade_id=${goodsask.trade_id?if_exists}');">查看</a>
    </td>
  </tr>
  </#list>
       </table>
        <div>
        ${pageBar?if_exists}
        </div>
      </div>     
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

