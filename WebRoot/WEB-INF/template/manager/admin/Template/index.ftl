<html>
  <head>
    <title>模板管理</title>   
  </head>
  <body>
<@s.form action="/admin_Template_list.action" method="post">
  <div class="postion">当前位置:网站管理 > 网站页面 > 模板管理 </div>
<div class="rtdcont">
 <div class="rtable">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
		    <td align="center" width="6%" class="top_td">
		    	<input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('template.template_id')"/>
		    </td>
            
            <td width="10%" align="center" class="top_td">模板名称</td>
                    
            <td width="20%" align="center" class="top_td">操作时间</td>
                    
            <td width="20%" align="center" class="top_td">操作人</td>
				      
			<td width="10%" align="center" class="top_td">修改</td> 
  </tr>
   <#list templateList as template>
	    <tr bgcolor="<#if template_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
	    <td align="center"><input type="checkbox" name="template.template_id" value="${template.template_id?if_exists}"/></td>
	    
	    
        <td align="center">${template.template_name?if_exists}</td>
        
        <td align="center">${template.in_date?if_exists}</td>
        
        <td align="center">${template.user_name?if_exists}</td>
        
	    
	    <td align="center"><a onclick="linkToInfo('/admin_Template_view.action','template.template_id=${template.template_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
      </tr>
     </#list> 
  
  
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
 <div class="bsbut">

             <input type="button" class="rbut" onclick="linkToInfo('/admin_Template_add.action','');" value="添加">
             <input type="button" class="rbut"onclick="delInfo('template.template_id','/admin_Template_delete.action')" value="删除">
           </div>
</div>


</@s.form>
</body>
</html>

