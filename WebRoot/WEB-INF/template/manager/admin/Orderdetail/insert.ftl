<html>
  <head>
    <title>添加详细订单</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
  </head>
  <body>
<@s.form action="/admin_Orderdetail_insert.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:商城管理 > 商品相关 > 详细订单 > 添加详细订单
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
             <td class="table_name">订单号<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="orderdetail.order_id" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>orderdetail.order_id</@s.param></@s.fielderror>
             </td>
           </tr>
       
           <tr>
             <td class="table_name">商品名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="orderdetail.goods_id" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>orderdetail.goods_id</@s.param></@s.fielderror>
             </td>
           </tr>
       
           <tr>
             <td class="table_name">商品价格：</td>
             <td>
             	<@s.textfield name="orderdetail.order_price" cssClass="txtinput" maxLength="20" onkeyup="checkFloat(this)"/>
             	<@s.fielderror><@s.param>orderdetail.order_price</@s.param></@s.fielderror>
             </td>
           </tr>
       
           <tr>
             <td class="table_name">商品数量：</td>
             <td>
             	<@s.textfield name="orderdetail.order_num" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>orderdetail.order_num</@s.param></@s.fielderror>
             </td>
           </tr>
       
           <tr>
             <td class="table_name">商品属性：</td>
             <td>
                <@s.textarea name="orderdetail.goods_attr" cssStyle="width:500px;height:100px" cssClass="txtinput" onkeyup="checkLength(this,600)"/>
             	<@s.fielderror><@s.param>orderdetail.goods_attr</@s.param></@s.fielderror>
             </td>
           </tr>
       
           <tr>
             <td class="table_name">备注：</td>
             <td>
                <@s.textarea name="orderdetail.remark" cssStyle="width:500px;height:100px" cssClass="txtinput" onkeyup="checkLength(this,600)"/>
             	<@s.fielderror><@s.param>orderdetail.remark</@s.param></@s.fielderror>
             </td>
           </tr>
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
       ${listSearchHiddenField?if_exists}
       <@s.token/> 
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Orderdetail_list.action','');"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

