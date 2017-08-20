<html>
  <head>
    <title>收货地址管理</title>   
  </head>
<body>
<@s.form action="/admin_Buyeraddr_groupList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：会员管理 > 会员相关 > 收货地址管理</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>会员名称:</td>
			<td><@s.textfield name="cust_name_s"  cssStyle="width:245px;"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
	         <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('buyeraddr.addr_id')"/></th>
	   
	     	 <th width="10%"  align="center" >会员名称</th>
	    
	     	 <th width="10%"  align="center" >收货人姓名</th>
	     	 
	     	 <th width="10%"  align="center" >地址信息条数</th>
	    
	         <th width="5%" align="center" >查看</th>
	  </tr>
	  
	 <#list buyeraddrList as buyeraddr>
	  <tr>
	    <td><input type="checkbox" name="buyeraddr.addr_id" value="${buyeraddr.addr_id?if_exists}"/></td>    
	 	
	    	<td align="center">
		    	<#if buyeraddr.cust_name?if_exists!=null && buyeraddr.cust_name?if_exists!=''>
		    		<a href="/admin_Buyeraddr_list.action?cust_id_s=${buyeraddr.cust_id?if_exists}" title="查看">${buyeraddr.cust_name?if_exists}</a>
		    	<#else>
		    	 	-
		    	</#if>
	    	</td>
	    
	    	<td align="center">
	    	<#if buyeraddr.consignee?if_exists!=null && buyeraddr.consignee?if_exists!=''>
		    		${buyeraddr.consignee?if_exists}
		    	<#else>
		    	 	-
		    	</#if>
	    	</td>
	    	
	    	<td align="center">
	    		<#if buyeraddr.info_num?if_exists!=null && buyeraddr.info_num?if_exists!=''>
		    		${buyeraddr.info_num?if_exists}
		    	<#else>
		    	 	-
		    	</#if>
	    	</td>
	    	
	    	<td align="center"><a href="/admin_Buyeraddr_list.action?cust_id_s=${buyeraddr.cust_id?if_exists}" title="查看"><img src="/include/common/images/audit.png" /></a></td>
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
</@s.form>
  </body>
</html>

