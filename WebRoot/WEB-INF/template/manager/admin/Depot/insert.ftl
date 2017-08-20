<html>
  <head>
    <title>添加仓库</title>
  </head>
  <body>
<@s.form action="/admin_Depot_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
   当前位置:商品管理 >库存管理 > 修改仓库
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		      <tr>
		             <td class="table_name" style="width:220px;" height="60px;">仓库名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="depot.depot_name" cssStyle="width:300px;height:20px;"  maxlength="20"/>
		             	<@s.fielderror><@s.param>depot.depot_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           <tr>
		             <td class="table_name">联系人<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="depot.contact" cssClass="txtinput" maxlength="15"/>
		             	<@s.fielderror><@s.param>depot.contact</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">联系邮件<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="depot.depot_mail" cssClass="txtinput" maxlength="40"/>
		             	<img class="ltip" src="/include/common/images/light.gif" alt="邮件格式:xxxx@xxx.com">
		             	<@s.fielderror><@s.param>depot.depot_mail</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		          <tr>
		             <td class="table_name">是否设定系统仓库<font color='red'>*</font></td>
		             <td>
		             	<@s.radio name="depot.is_sys" list=r"#{'0':'是','1':'否'}"value="1"/>
		             	  <font color="red">系统仓库是不可删除！</font>
		             	<@s.fielderror><@s.param>depot.is_sys</@s.param></@s.fielderror>
		             </td>
		           </tr> 
	              
	              <tr>
		             <td class="table_name">国际物流方式<font color='red'>*</font></td>
		             <td>
		             	<@s.radio name="depot.is_international" list=r"#{'0':'国内','1':'国际'}"/>
		             	<@s.fielderror><@s.param>depot.is_international</@s.param></@s.fielderror>
		             </td>
		           </tr> 
	              
	              
	               <tr>
		             <td class="table_name">联系电话<font color='red'>*</font></td>
		             <td>
		                <@s.textfield name="depot.phone" cssClass="txtinput" maxLength="11"/>
		             	<img class="ltip" src="/include/common/images/light.gif" alt="手机号码格式：只能以13/15/18, 开头的11位数字!">
		             	<@s.fielderror><@s.param>depot.phone</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">仓库地址<font color='red'>*</font></td>
		             <td>
         				<@s.textfield name="depot.depot_add" cssClass="txtinput"  maxlength="100" cssStyle="width:450px;height:20px;"/>
		             	<@s.fielderror><@s.param>depot.depot_add</@s.param></@s.fielderror>
		             </td>
		           </tr>
	              
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    <@s.token/>
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       ${listSearchHiddenField?if_exists}
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Depot_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>