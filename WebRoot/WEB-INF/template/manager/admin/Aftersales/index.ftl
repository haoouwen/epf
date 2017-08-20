<html>
  <head>
    <title>售后维护列表</title>   
  </head>
<body>
<@s.form action="/admin_Aftersales_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商品管理 > 商品管理 > 售后维护</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
       <tr >
		 <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('aftersales.cust_id')"/></th>
     	 <th width="30%"  align="center" >会员名称</th>
     	 <th width="50%"  align="center" >维护内容</th>
         <th width="17%" align="center" >操作</th>
	  </tr>
  <#list aftersalesList as aftersales>
  <tr>
    <td><input type="checkbox" name="aftersales.cust_id" value="${aftersales.cust_id?if_exists}"/></td>    
 	
    	<td align="center">${aftersales.cust_id?if_exists}</td>
    
        <td align="center">
    
		    <#if (aftersales.content)?if_exists?length lt 300>
           			${aftersales.content}
   			<#else>
           			${aftersales.content[0..299]}
   			</#if>
    	
        </td>
    <td align="center"><a onclick="linkToInfo('/admin_Aftersales_view.action','aftersales.cust_id=${aftersales.cust_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Aftersales_add.action','');" value="添加">
     <input type="button" class="rbut"onclick="delInfo('aftersales.cust_id','/admin_Aftersales_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>

