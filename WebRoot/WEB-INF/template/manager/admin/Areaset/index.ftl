<html>
  <head>
    <title>记录区域设置信息列表</title>   
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
  </head>
<body>
<@s.form action="/admin_Areaset_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商城管理 > 系统配置 > 配送方式>区域设置列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>区域名称:</td>
			<td><@s.textfield name="areaset_name_s"  cssStyle="width:245px;"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
         <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('areaset.areaset_id')"/></th>
     	 <th width="10%"  align="center" >配送方式名称</th>
     	 <th width="10%"  align="center" >区域名称</th>
     	 <th width="10%"  align="center" >开始地址</th>
     	 <th width="10%"  align="center" >到达地址</th>
     	 <th width="10%"  align="center" >首重价格</th>
     	 <th width="10%"  align="center" >续重价格</th>
     	 <th width="10%"  align="center" >首重</th>
     	 <th width="10%"  align="center" >续重</th>
         <th width="5%" align="center" >操作</th>
	  </tr>
	  
 <#list areasetList as areaset>
  <tr>
    <td><input type="checkbox" name="areaset.areaset_id" value="${areaset.areaset_id?if_exists}"/></td>    
 	
    	<td align="center">${areaset.smode_name?if_exists}</td>
    
    	<td align="center">${areaset.areaset_name?if_exists}</td>
    
    	<td align="center">${areaset.start_area?if_exists}</td>
    
    	<td align="center">${areaset.end_area?if_exists}</td>
    
    	<td align="center">${areaset.first_price?if_exists}</td>
    	
    	<td align="center">${areaset.cont_price?if_exists}</td>
    
        <td align="center">${areaset.first_weight?if_exists}</td>
        
    	<td align="center">${areaset.cont_weight?if_exists}</td>
        <td align="center"><a onclick="linkToInfo('/admin_Areaset_view.action','areaset.areaset_id=${areaset.areaset_id?if_exists}&smode_id=${smode_id?if_exists}');">
        <img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Areaset_add.action','smode_id=${smode_id?if_exists}');" value="添加">
     <input type="button" class="rbut" onclick="delInfo('areaset.areaset_id','/admin_Areaset_delete.action')" value="删除">
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Sendmode_list.action','');" value="返回">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
<@s.hidden name="smode_id" />
 <!--表单框隐藏域存放-->  
</@s.form>
  </body>
</html>

