<html>
  <head>
    <title>添加商品收藏</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
  </head>
  <body>
  <@s.form action="/admin_Collect_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:商城管理 > 商品相关 > 商品收藏 > 添加商品收藏
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
		           <tr>
		             <td class="table_name" >商品名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="collect.goods_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>collect.goods_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">收藏标题<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="collect.title" cssClass="txtinput" maxLength="100"/>
		             	<@s.fielderror><@s.param>collect.title</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">收藏链接<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="collect.link_url" cssClass="txtinput" maxLength="100"/>
		             	<@s.fielderror><@s.param>collect.link_url</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">备注:</td>
		             <td>
		             	<@s.textarea name="collect.remark" cssClass="txtinput" maxLength="100" cssStyle="width:260px;height:60px;"onkeyup="checkLength(this,100)"/>
		             	<@s.fielderror><@s.param>collect.remark</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	       <@s.hidden name="collect.goods_id"/>    
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
	       <@s.token/> 
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Collect_list.action','');"/>    
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

