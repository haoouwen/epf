<html>
  <head>
    <title>商城后台角色信息列表</title>   
  </head>
  <body>
<@s.form action="/admin_Memrole_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Memrole_add.action','');">添加商城后台角色信息</a></li>
     <li class="sc"><a onclick="delInfo('memrole.memrole_id','/admin_Memrole_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','memrole.memrole_id','/admin_Memrole_updateisrecom.action','admin_memrole_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','memrole.memrole_id','/admin_Memrole_updateisrecom.action','admin_memrole_state');">不推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('memrole.memrole_id')"/></td>
   
    
     	 <td width="10%"  align="center" class="top_td">memrole_id</td>
    
     	 <td width="10%"  align="center" class="top_td">cust_id</td>
    
     	 <td width="10%"  align="center" class="top_td">memrole_name</td>
    
     	 <td width="10%"  align="center" class="top_td">memmenu_right</td>
    
     	 <td width="10%"  align="center" class="top_td">remark</td>
    
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list memroleList as memrole>
  <tr>
    <td><input type="checkbox" name="memrole.memrole_id" value="${memrole.memrole_id?if_exists}"/></td>    
 	
    	<td align="center">${memrole.memrole_id?if_exists}</td>
    
    	<td align="center">${memrole.cust_id?if_exists}</td>
    
    	<td align="center">${memrole.memrole_name?if_exists}</td>
    
    	<td align="center">${memrole.memmenu_right?if_exists}</td>
    
    	<td align="center">${memrole.remark?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Memrole_view.action','memrole.memrole_id=${memrole.memrole_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
  </tr>
  </#list>
</table>
 </div>
 <div class="listbottom">
   ${pageBar?if_exists}
 </div>
 <div class="clear"></div>
</div>
</div>
<div class="clear"></div>


<!--搜索区域开始-->

<div class="divceng" style="display:none;" id="searchDiv">
	<table align="left">
		<tr>
		<td align="right">下载标题:</td> 
		<td><@s.textfield name="title_s"  cssStyle="width:245px;"/></td>
	</tr>
		<tr>
		<td align="center" colspan="2" style="border:0px;">
			<input type="button" name="search" value="搜索" onclick="showSelectDiv('area_attr','cat_attr','search_area_attr','search_cat_attr');" />
			<input type="button" name="close" value="关闭" onclick="closeSearch('searchDiv')"/>
		</td>
	</tr>
	</table>
</div>

</@s.form>
</body>
</html>

