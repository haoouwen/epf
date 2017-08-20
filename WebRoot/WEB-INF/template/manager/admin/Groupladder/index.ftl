<html>
  <head>
    <title>团购阶梯价格列表</title>   
  </head>
  <body>
<@s.form action="/admin_Groupladder_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Groupladder_add.action','');">添加团购阶梯价格</a></li>
     <li class="sc"><a onclick="delInfo('groupladder.trade_id','/admin_Groupladder_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','groupladder.trade_id','/admin_Groupladder_updateisrecom.action','admin_groupladder_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','groupladder.trade_id','/admin_Groupladder_updateisrecom.action','admin_groupladder_state');">不推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('groupladder.trade_id')"/></td>
   
    
     	 <td width="10%"  align="center" class="top_td">trade_id</td>
    
     	 <td width="10%"  align="center" class="top_td">group_id</td>
    
     	 <td width="10%"  align="center" class="top_td">lownum</td>
    
     	 <td width="10%"  align="center" class="top_td">price</td>
    
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list groupladderList as groupladder>
  <tr>
    <td><input type="checkbox" name="groupladder.trade_id" value="${groupladder.trade_id?if_exists}"/></td>    
 	
    	<td align="center">${groupladder.trade_id?if_exists}</td>
    
    	<td align="center">${groupladder.group_id?if_exists}</td>
    
    	<td align="center">${groupladder.lownum?if_exists}</td>
    
    	<td align="center">${groupladder.price?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Groupladder_view.action','groupladder.trade_id=${groupladder.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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

