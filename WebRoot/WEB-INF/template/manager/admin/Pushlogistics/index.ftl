<html>
  <head>
    <title>物流推送列表</title>   
  </head>
  <body>
<@s.form action="/admin_Pushlogistics_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Pushlogistics_add.action','');">添加物流推送</a></li>
     <li class="sc"><a onclick="delInfo('pushlogistics.trade_id','/admin_Pushlogistics_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','pushlogistics.trade_id','/admin_Pushlogistics_updateisrecom.action','admin_pushlogistics_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','pushlogistics.trade_id','/admin_Pushlogistics_updateisrecom.action','admin_pushlogistics_state');">不推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('pushlogistics.trade_id')"/></td>
   
    
     	 <td width="10%"  align="center" class="top_td">trade_id</td>
    
     	 <td width="10%"  align="center" class="top_td">number</td>
    
     	 <td width="10%"  align="center" class="top_td">com</td>
    
     	 <td width="10%"  align="center" class="top_td">status</td>
    
     	 <td width="10%"  align="center" class="top_td">time</td>
    
     	 <td width="10%"  align="center" class="top_td">content</td>
    
     	 <td width="10%"  align="center" class="top_td">is_ship</td>
    
     	 <td width="10%"  align="center" class="top_td">is_send</td>
    
     	 <td width="10%"  align="center" class="top_td">is_sign</td>
    
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list pushlogisticsList as pushlogistics>
  <tr>
    <td><input type="checkbox" name="pushlogistics.trade_id" value="${pushlogistics.trade_id?if_exists}"/></td>    
 	
    	<td align="center">${pushlogistics.trade_id?if_exists}</td>
    
    	<td align="center">${pushlogistics.number?if_exists}</td>
    
    	<td align="center">${pushlogistics.com?if_exists}</td>
    
    	<td align="center">${pushlogistics.status?if_exists}</td>
    
    	<td align="center">${pushlogistics.time?if_exists}</td>
    
    	<td align="center">${pushlogistics.content?if_exists}</td>
    
    	<td align="center">${pushlogistics.is_ship?if_exists}</td>
    
    	<td align="center">${pushlogistics.is_send?if_exists}</td>
    
    	<td align="center">${pushlogistics.is_sign?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Pushlogistics_view.action','pushlogistics.trade_id=${pushlogistics.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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

