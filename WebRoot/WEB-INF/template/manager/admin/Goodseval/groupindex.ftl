<html>
  <head>
     <title>商品评价</title>   
  </head>
  <body>
<@s.form action="/admin_Goodseval_groupList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:商城管理 > 商品相关 > 商品评价</div>
<!--当前位置结束-->
<!--条件查询-->
	<div class="rtdcont">
	   <div class="rseacher">
	     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>商品名称:</td>
			<td><@s.textfield name="goods_name_s"  cssStyle="width:245px;"/></td>
	       <td><input type="submit" class="rbut" value="查询"></td>
	   </tr>
	  </table>
	</div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		    <th width="50%"  >商品名称</th>
		    <th width="40%"  >信息条数</th>
		    <th width="10%"  >查看</th>
	  </tr>
	  
	  <#list goodsevalList as goodseval>
	    <tr >
		    <td align="left">
				<a onclick="linkToInfo('/admin_Goodseval_list.action','goods_id_s=${goodseval.goods_id?if_exists}');" title="查看" >${goodseval.goods_name?if_exists}</a>
		    </td>
		    
		    <td align="center">
				${goodseval.info_num?if_exists}
		    </td>
		    
		    <td align="center">
		    	<a onclick="linkToInfo('/admin_Goodseval_list.action','goods_id_s=${goodseval.goods_id?if_exists}');" title="查看" ><img src="/include/common/images/audit.png" /></a>
		    </td>
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
   </div>
<!--按钮操作存放结束-->
</div>
</@s.form>

  </body>
</html>
