<html>
  <head>
    <title>记录更新的索引记录列表</title>   
  </head>
  <body>
<@s.form action="/admin_Indexrecord_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Indexrecord_add.action','');">添加记录更新的索引记录</a></li>
     <li class="sc"><a onclick="delInfo('indexrecord.trade_id','/admin_Indexrecord_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','indexrecord.trade_id','/admin_Indexrecord_updateisrecom.action','admin_indexrecord_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','indexrecord.trade_id','/admin_Indexrecord_updateisrecom.action','admin_indexrecord_state');">不推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('indexrecord.trade_id')"/></td>
   
    
     	 <td width="10%"  align="center" class="top_td">trade_id</td>
    
     	 <td width="10%"  align="center" class="top_td">module_name</td>
    
     	 <td width="10%"  align="center" class="top_td">info_id</td>
    
     	 <td width="10%"  align="center" class="top_td">oper_type</td>
    
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list indexrecordList as indexrecord>
  <tr>
    <td><input type="checkbox" name="indexrecord.trade_id" value="${indexrecord.trade_id?if_exists}"/></td>    
 	
    	<td align="center">${indexrecord.trade_id?if_exists}</td>
    
    	<td align="center">${indexrecord.module_name?if_exists}</td>
    
    	<td align="center">${indexrecord.info_id?if_exists}</td>
    
    	<td align="center">${indexrecord.oper_type?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Indexrecord_view.action','indexrecord.trade_id=${indexrecord.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
			<input type="button" name="search" value="搜索" onclick="searchSubmit();" />
			<input type="button" name="close" value="关闭" onclick="closeSearch();"/>
		</td>
	</tr>
	</table>
</div>

</@s.form>
</body>
</html>

