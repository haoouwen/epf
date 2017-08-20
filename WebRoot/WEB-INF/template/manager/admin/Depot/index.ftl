<html>
  <head>
    <title>仓库列表</title>   
  </head>
<body>
<@s.form action="/admin_Depot_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置：商品管理 > 库存管理 > 仓库列表</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td class="tdpad">仓库名称:&nbsp;</td>
			<td><@s.textfield name="title_s" cssStyle="width:150px;"maxlength="15"/>&nbsp;</td>
			 <td class="tdpad">联系人:&nbsp;</td>
			 <td><@s.textfield name="contact" cssStyle="width:100px;" maxlength="10"/>&nbsp;</td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
         <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('depot.depot_id')"/></th>
     	 <th width="20%"  >仓库名称</th>
     	 <th width="10%"  >联系人</th>
     	 <th width="10%"  >联系邮件</th>
     	 <th width="8%"  >联系电话</th>
     	 <th width="18%"  >仓库地址</th>
     	 <th width="18%"  >物流方式</th>
         <th width="5%" >操作</th>
	  </tr>
<#list depotList as depot>
  <tr>
    <td>
     <#if depot.is_sys?if_exists='0'>
      <img class="ltip" src="/include/common/images/light.gif" alt="提示:设定当前系统仓库不可删除!">
     <#else>
       <input type="checkbox"  name="depot.depot_id" value="${depot.depot_id?if_exists}"/></td>    
    </#if>
    	<td align="center" title="${depot.depot_name?if_exists}">
           <a onclick="linkToInfo('/admin_Depot_view.action','depot.depot_id=${depot.depot_id?if_exists}');">
              <#if depot.depot_name?if_exists!=''>
					<#if depot.depot_name? length lt 20 >
						${depot.depot_name?if_exists}
					<#else>
						${depot.depot_name[0..19]?if_exists}...    
					</#if>
			  </#if>
			</a>
			
        </td>
        <td align="center">${depot.contact?if_exists}</td>
    	<td align="center">${depot.depot_mail?if_exists}</td>
    	<td align="center">${depot.phone?if_exists}</td>
        <td align="center">${depot.depot_add?if_exists}</td>
        <td align="center">
          <#if depot.is_international=='0'>
            <font color="green">国内</font>
           <#else>
            <font color="red">国际</font>
          </#if>        
        </td>
        <td align="center"><a onclick="linkToInfo('/admin_Depot_view.action','depot.depot_id=${depot.depot_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" style="display:none" onclick="linkToInfo('/admin_Depot_add.action','');" value="添加">
     <input type="button" class="rbut"style="display:none" onclick="delInfo('depot.depot_id','/admin_Depot_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>  
</@s.form>
  </body>
</html>

