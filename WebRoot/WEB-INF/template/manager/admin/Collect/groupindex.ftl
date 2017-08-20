<html>
  <head>
    <title>商品收藏</title>   
  </head>
<body>
<@s.form action="/admin_Collect_groupList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 商品相关 > 商品收藏</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			 <td>会员名称:</td>
			<td>
				<@s.textfield name="cust_name_s"  cssStyle="width:245px;"/>
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
	     	 <th width="50%"  align="center" >会员名称</th>
	     	 
	     	 <th width="40%"  align="center" >信息条数</th>
	    
	     	 <th width="10%"  align="center" >查看</th>
	  </tr>
	  
		 <#list collectList as collect>
		  <tr>
		 	
		    	<td align="center">
		    	<#if collect.cust_name?if_exists!=null && collect.cust_name?if_exists!=''>
			    		<a onclick="linkToInfo('/admin_Collect_list.action','cust_id_s=${collect.cust_id?if_exists}');" title="查看" >${collect.cust_name?if_exists}</a>
			    	<#else>
			    	 	-
			    	</#if>
		    	
		    	</td>
		    
		    	<td align="center">${collect.info_num?if_exists}</td>
		          
		    <td align="center"><a onclick="linkToInfo('/admin_Collect_list.action','cust_id_s=${collect.cust_id?if_exists}');" title="查看" ><img src="/include/common/images/audit.png" /></a></td>
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
</div>
<!--表单框隐藏域存放-->
<@s.hidden name="cust_id_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>

