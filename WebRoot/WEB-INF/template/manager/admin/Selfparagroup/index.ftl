<html>
  <head>
    <title>记录自定义参数组信息列表</title>   
  </head>
  <body>
<@s.form action="/admin_Selfparagroup_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Selfparagroup_add.action','');">添加记录自定义参数组信息</a></li>
     <li class="sc"><a onclick="delInfo('selfparagroup.slef_para_group_id','/admin_Selfparagroup_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','selfparagroup.slef_para_group_id','/admin_Selfparagroup_updateisrecom.action','admin_selfparagroup_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','selfparagroup.slef_para_group_id','/admin_Selfparagroup_updateisrecom.action','admin_selfparagroup_state');">不推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('selfparagroup.slef_para_group_id')"/></td>
   
    
     	 <td width="10%"  align="center" class="top_td">slef_para_group_id</td>
    
     	 <td width="10%"  align="center" class="top_td">para_group_id</td>
    
     	 <td width="10%"  align="center" class="top_td">goods_id</td>
    
     	 <td width="10%"  align="center" class="top_td">sort_no</td>
    
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list selfparagroupList as selfparagroup>
  <tr>
    <td><input type="checkbox" name="selfparagroup.slef_para_group_id" value="${selfparagroup.slef_para_group_id?if_exists}"/></td>    
 	
    	<td align="center">${selfparagroup.slef_para_group_id?if_exists}</td>
    
    	<td align="center">${selfparagroup.para_group_id?if_exists}</td>
    
    	<td align="center">${selfparagroup.goods_id?if_exists}</td>
    
    	<td align="center">${selfparagroup.sort_no?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Selfparagroup_view.action','selfparagroup.slef_para_group_id=${selfparagroup.slef_para_group_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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

