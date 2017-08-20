<html>
  <head>
    <title>商品收藏</title>   
  </head>
<body>
<@s.form action="/admin_Collect_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 商品相关 > 商品收藏</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td class="tdpad">收藏标题:</td>
			<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
		   <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('collect.coll_id')"/></th>
		    
		     	 <th width="20%"  align="center" >会员名称</th>
		    <!--  预留着到时删掉
		     	 <th width="10%"  align="center" >商品名称</th>
		    -->
		     	 <th width="20%"  align="center" >收藏标题</th>
		    
		     	 <th width="30%"  align="center" >收藏链接</th>
		    
		     	 <th width="20%"  align="center" >添加时间</th>
		    
		    
		    <th width="7%" align="center" >操作</th>
	  </tr>
	  
		 <#list collectList as collect>
		  <tr>
		    <td><input type="checkbox" name="collect.coll_id" value="${collect.coll_id?if_exists}"/></td>    
		 	
		    	<td align="center">
		    	<#if collect.cust_name?if_exists!=null && collect.cust_name?if_exists!=''>
			    		${collect.cust_name?if_exists}
			    	<#else>
			    	 	-
			    	</#if>
		    	
		    	</td>
		    <!--  预留着到时删掉
		    	<td align="center">
		    	<#if collect.goods_name?if_exists!=null && collect.goods_name?if_exists!=''>
			    		${collect.goods_name?if_exists}
			    	<#else>
			    	 	-
			    	</#if>
		    	
		    	</td>
		    -->
		    	<td align="center">
		    	<#if coll_type_s==0>
		    	${collect.title?if_exists}
		        <#elseif coll_type_s==1>
		        ${collect.brand_name?if_exists}
		        </#if>
		    	</td>
		    
		    	<td align="center"><a href="${collect.link_url?if_exists}">${collect.link_url?if_exists}</a></td>
		    
		    	<td align="center">${collect.in_date?if_exists}</td>
		          
		    <td align="center"><a onclick="linkToInfo('/admin_Collect_view.action','collect.coll_id=${collect.coll_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
<!--按钮操作存放-->
   <div class="bsbut">

   	<input type="button" class="rbut" value="商品收藏" onclick="linkToInfo('/admin_Collect_list.action','coll_type_s=0&cust_id_s=${cust_id_s}')"/>
	<input type="button" class="rbut" value="品牌收藏" onclick="linkToInfo('/admin_Collect_list.action','coll_type_s=1&cust_id_s=${cust_id_s}')"/>
     <input type="button" class="rbut" onclick="delInfo('collect.coll_id','/admin_Collect_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
<@s.hidden name="cust_id_s" />
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>

