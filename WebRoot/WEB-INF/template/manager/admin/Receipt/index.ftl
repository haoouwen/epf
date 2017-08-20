<html>
  <head>
    <title>记录单据模板信息列表</title>   
  </head>
  <body>
<@s.form action="/admin_Receipt_list.action" method="post">
<div class="main_index f_left">
   <div class="pageLocation">
 	当前位置:1 > 2 > 3
   </div>
 <div class="main_top">
   <ul class="main_top_ul">
     <li class="tj"><a onclick="linkToInfo('/admin_Receipt_add.action','');">添加记录单据模板信息</a></li>
     <li class="sc"><a onclick="delInfo('receipt.receipt_code','/admin_Receipt_delete.action')">删除</a></li>
     <li class="shuaix"><a onclick="showSearch(this,'searchDiv');">筛选</a></li>
     <li class="qiyong"><a onclick="updateRecomState('1','receipt.receipt_code','/admin_Receipt_updateisrecom.action','admin_receipt_state');">推荐</a></li>
     <li class="jingyong"><a onclick="updateRecomState('0','receipt.receipt_code','/admin_Receipt_updateisrecom.action','admin_receipt_state');">不推荐</a></li>
   </ul>
 </div>
 <div class="main_cont">
  <table width="100%" border="0" cellspacing="0" class="indexTab">
  <tr style="background:url(images/top_tr.gif) repeat-x;">
    <td width="3%" class="top_td"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('receipt.receipt_code')"/></td>
   
    
     	 <td width="10%"  align="center" class="top_td">receipt_code</td>
    
     	 <td width="10%"  align="center" class="top_td">receipt_name</td>
    
     	 <td width="10%"  align="center" class="top_td">receipt_content</td>
    
    
    <td width="5%" align="center" class="top_td">修改</td>
  </tr>
  
  <#list receiptList as receipt>
  <tr>
    <td><input type="checkbox" name="receipt.receipt_code" value="${receipt.receipt_code?if_exists}"/></td>    
 	
    	<td align="center">${receipt.receipt_code?if_exists}</td>
    
    	<td align="center">${receipt.receipt_name?if_exists}</td>
    
    	<td align="center">${receipt.receipt_content?if_exists}</td>
          
    <td align="center"><a onclick="linkToInfo('/admin_Receipt_view.action','receipt.receipt_code=${receipt.receipt_code?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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

