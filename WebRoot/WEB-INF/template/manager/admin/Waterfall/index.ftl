<html>
  <head>
    <title>瀑布流管理</title>   
  </head>
  <body>
<@s.form action="/admin_Waterfall_list.action" method="post">
  <div class="postion">当前位置:网站管理 > 网站页面 > 瀑布流管理 </div>
<div class="rtdcont">
 <div class="rtable">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
		    <td align="center" width="6%" class="top_td">
		    	<input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('waterfall.wf_code')"/>
		    </td>
            
            <td width="10%" align="center" class="top_td">瀑布名称</td>
                    
            <td width="20%" align="center" class="top_td">操作时间</td>
                    
            <td width="20%" align="center" class="top_td">操作人</td>
				      
			<td width="10%" align="center" class="top_td">修改</td> 
  </tr>
   <#list waterfallList as waterfall>
	    <tr bgcolor="<#if waterfall_index%2==0>#FFFFFF<#else>#f8f8f8</#if>">
	    
	    <td align="center"><input type="checkbox" name="waterfall.wf_code" value="${waterfall.wf_code?if_exists}"/></td>
	    
	    
        <td align="center">${waterfall.wf_code?if_exists}</td>
        
        <td align="center">${waterfall.in_date?if_exists?string("yyyy-MM-dd HH:mm:ss")}</td>
        
        <td align="center">${waterfall.user_name?if_exists}</td>
        
	    
	    <td align="center"><a onclick="linkToInfo('/admin_Waterfall_view.action','waterfall.wf_code=${waterfall.wf_code?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
      </tr>
     </#list> 
  
  
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
 <div class="bsbut">

             <input type="button" class="rbut" onclick="linkToInfo('/admin_Waterfall_add.action','');" value="添加瀑布流">
             <input type="button" class="rbut"onclick="delInfo('waterfall.wf_code','/admin_Waterfall_delete.action')" value="删除">
           </div>
</div>


</@s.form>
<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
		<tr>
		<td align="right">下载标题:</td> 
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
		<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="searchSubmit();" />
			<input type="button" name="close" value="关闭" onclick="closeSearch();"/>
		</td>
	</tr>
	</table>
</div>
</body>
</html>

