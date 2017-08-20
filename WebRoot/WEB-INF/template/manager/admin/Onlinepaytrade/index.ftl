<html>
  <head>
    <title>记录网银支付流水信息列表</title>   
  </head>
  <body>
<@s.form action="/admin_Onlinepaytrade_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Onlinepaytrade_add.action','');">添加记录网银支付流水信息</a></li>
     <li class="sc"><a onclick="delInfo('onlinepaytrade.trade_id','/admin_Onlinepaytrade_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','onlinepaytrade.trade_id','/admin_Onlinepaytrade_updateisrecom.action','admin_onlinepaytrade_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','onlinepaytrade.trade_id','/admin_Onlinepaytrade_updateisrecom.action','admin_onlinepaytrade_state');">不推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('onlinepaytrade.trade_id')"/></td>
   
    
     	 <td width="10%"  align="center" class="top_td">trade_id</td>
    
     	 <td width="10%"  align="center" class="top_td">cust_id</td>
    
     	 <td width="10%"  align="center" class="top_td">online_amount</td>
    
     	 <td width="10%"  align="center" class="top_td">circlegold_amount</td>
    
     	 <td width="10%"  align="center" class="top_td">in_date</td>
    
     	 <td width="10%"  align="center" class="top_td">state</td>
    
     	 <td width="10%"  align="center" class="top_td">pay_type</td>
    
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list onlinepaytradeList as onlinepaytrade>
  <tr>
    <td><input type="checkbox" name="onlinepaytrade.trade_id" value="${onlinepaytrade.trade_id?if_exists}"/></td>    
 	
    	<td align="center">${onlinepaytrade.trade_id?if_exists}</td>
    
    	<td align="center">${onlinepaytrade.cust_id?if_exists}</td>
    
    	<td align="center">${onlinepaytrade.online_amount?if_exists}</td>
    
    	<td align="center">${onlinepaytrade.circlegold_amount?if_exists}</td>
    
    	<td align="center">${onlinepaytrade.in_date?if_exists}</td>
    
    	<td align="center">${onlinepaytrade.state?if_exists}</td>
    
    	<td align="center">${onlinepaytrade.pay_type?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Onlinepaytrade_view.action','onlinepaytrade.trade_id=${onlinepaytrade.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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

