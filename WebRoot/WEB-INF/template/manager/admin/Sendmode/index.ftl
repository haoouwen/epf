<html>
  <head>
    <title>快递公司</title>   
  </head>

<body>
<@s.form action="/admin_Sendmode_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion"> 当前位置:商城管理 > 配送管理 > 快递公司</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td >快递公司名称:</td> 
			<td><@s.textfield name="smode_name_s"  cssStyle="width:200px;"/></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	   	  </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
         <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('sendmode.smode_id')"/></th>
    
         <th width="6%">排序</th>
         
     	 <th width="20%"  >快递公司名称</th>
     	 
     	 <th width="15%"  >快递公司代码</th>
     	 
     	 <th width="15%"  >运单号长度</th>
 
    	 <th width="10%" >操作</th>
  </tr>
  
  <#list sendmodeList as sendmode>
  <tr bgcolor="<#if sendmode_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
    <td><input type="checkbox" name="sendmode.smode_id" value="${sendmode.smode_id?if_exists}"/></td>    
 	
   		 <td align="center"><input name="isort_no" value="${sendmode.sort_no?if_exists}" style="width:50px" onkeyup="checkNum(this)" /></td>
   		 
    	<td align="center">
    	  <a onclick="linkToInfo('/admin_Sendmode_view.action','sendmode.smode_id=${sendmode.smode_id?if_exists}');">
    	${sendmode.smode_name?if_exists}
    	</a>
    	</td>
       <td align="center">
    	${sendmode.en_name?if_exists}
    	</td>
         <td align="center">
    	${sendmode.sendlength?if_exists}位
    	</td>
    	<td align="center">
    
	    <a onclick="linkToInfo('/admin_Sendmode_view.action','sendmode.smode_id=${sendmode.smode_id?if_exists}');">
	    	<img src="/include/common/images/bj.gif" /></a>
    	
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Sendmode_add.action','');" value="添加">
     <input type="button" class="rbut" onclick="delInfo('sendmode.smode_id','/admin_Sendmode_delete.action')" value="删除">
     <input type="button" class="rbut" onclick="updateSort('sendmode.smode_id','isort_no','/admin_Sendmode_updateSort.action');" value="排序">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden  name="rate_s"/>
  <@s.hidden  name="mix_insured_s"/>
  <@s.hidden  name="max_insured_s"/>
  <@s.hidden  name="reach_pay_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>
</body>
</html>
