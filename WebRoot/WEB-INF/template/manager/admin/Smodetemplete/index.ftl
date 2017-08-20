<html>
  <head>
    <title>配送摸版列表</title>   
  </head>

<body>
<@s.form action="/admin_Smodetemplete_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:商城管理 > 配送管理 > 配送摸版列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			 <td >模板内容:</td> 
			 <td><@s.textfield name="smode_templete_s"  cssStyle="width:245px;"/></td>
			 <td align="tdpad">配送方式:</td> 
			 <td><@s.textfield name="smode_name_s"  cssStyle="width:245px;"/></td>
	      	 <td><input type="submit" class="rbut" value="查询"></td>
	  	 </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
	     <th width="3%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('smodetemplete.trade_id')"/></th>
   
         <th width="10%">配送方式</th>
    
     	 <th width="10%">操作人</th>
    
     	 <th width="10%">操作时间</th>
    
    	<th width="5%">操作</th>
  </tr>
  
  <#list smodetempleteList as smodetemplete>
  <tr>
    <td><input type="checkbox" name="smodetemplete.trade_id" value="${smodetemplete.trade_id?if_exists}"/></td>    
       <td align="center">
    	<#if smodetemplete.smode_name?if_exists!=null && smodetemplete.smode_name?if_exists!=''>
	    		${smodetemplete.smode_name?if_exists}
	    	<#else>
	    	 	-
	    	</#if>
    	
    	</td>
    
    	<td align="center">
    	<#if smodetemplete.user_name?if_exists!=null && smodetemplete.user_name?if_exists!=''>
	    		${smodetemplete.user_name?if_exists}
	    	<#else>
	    	 	-
	    	</#if>
    	
    	</td>
    
    	<td align="center">${smodetemplete.in_date?if_exists}</td>
          
    <td align="center">
    <a onclick="linkToInfo('/admin_Smodetemplete_view.action','smodetemplete.trade_id=${smodetemplete.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a>
    </td>
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
     <input type="button" class="rbut" onclick="delInfo('smodetemplete.trade_id','/admin_Smodetemplete_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>  
</body>
</html>

