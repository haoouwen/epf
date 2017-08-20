<html>
  <head>
    <title>商家留言</title>
  </head>
  <body>
  <@s.form action="/admin_Busimes_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:商城管理 > 店铺管理 > 商家留言
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
	           <tr>
	             <td class="table_name">被留言的商店ID<font color='red'>*</font></td>
	             <td>
	             	<@s.textfield name="busimes.get_cust_id" cssClass="txtinput" maxLength="20"/>
	             	<@s.fielderror><@s.param>busimes.get_cust_id</@s.param></@s.fielderror>
	             </td>
	           </tr>
           
	           <tr>
	             <td class="table_name">留言内容<font color='red'>*</font></td>
	             <td>
	             	<@s.textarea name="busimes.msg_content" cssStyle="width:500px;height:100px;" cssClass="txtinput" maxLength="20" onkeyup="checkLength(this,600);"/>
	             	<@s.fielderror><@s.param>busimes.msg_content</@s.param></@s.fielderror>
	             </td>
	           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Busimes_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

