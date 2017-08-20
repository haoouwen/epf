<html>
  <head>
    <title> 详细订单</title>   
  </head>
<body>
<@s.form action="/admin_Orderdetail_list.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:商城管理 > 商品相关 > 详细订单</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
			<td>订单号:</td>
			<td><@s.textfield name="order_id_s" cssStyle="width:200px;" /></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
		     <th width="3%" ><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('orderdetail.trade_id')"/></th>
	    
	     	 <th width="20%">订单号</th>
	    
	     	 <th width="20%">商品名称</th>
	    
	     	 <th width="20%">商品价格</th>
	    
	     	 <th width="10%">商品数量</th>
	    
	     	 <th width="20%">商品属性</th>
	    
	    	 <th width="7%">操作</th>
	  </tr>
	  
	  <#list orderdetailList as orderdetail>
		  <tr>
		    	<td><input type="checkbox" name="orderdetail.trade_id" value="${orderdetail.trade_id?if_exists}"/></td>    
		    
		    	<td align="center">${orderdetail.order_id?if_exists}</td>
		    <!--改成名称删掉改注释-->
		    	<td align="center">${orderdetail.goods_id?if_exists}</td>
		    
		    	<td align="center">${orderdetail.order_price?if_exists}</td>
		    
		    	<td align="center">${orderdetail.order_num?if_exists}</td>
		    
		    	<td align="center">${orderdetail.goods_attr?if_exists}</td>
		    
		    <td align="center"><a onclick="linkToInfo('/admin_Orderdetail_view.action','orderdetail.trade_id=${orderdetail.trade_id?if_exists}');"><img src="/include/common/images/bj.gif" /></a></td>
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
     <input type="button" class="rbut" onclick="linkToInfo('/admin_Orderdetail_add.action','');" value="添加详细订单">
     <input type="button" class="rbut" onclick="delInfo('orderdetail.trade_id','/admin_Orderdetail_delete.action')" value="删除">
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
<!--搜索区域结束-->
  </body>
</html>
