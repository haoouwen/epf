<html>
  <head>
    <title>添加售后维护</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
      <script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
					     	CKEDITOR.replace('content');  
	</script>
  </head>
  <body>
  <@s.form action="/admin_Aftersales_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:商品管理 > 商品管理 > 添加售后维护
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		  <tr>
		             <td class="table_name"  >会员标识<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="aftersales.cust_id" cssClass="txtinput" maxLength="20 " onkeyup="checkDigital(this)"/>
		             	  <img class="ltip" src="/include/common/images/light.gif" alt="该文本框只输入数字不能输入重复的会员标识">
		             	<@s.fielderror><@s.param>aftersales.cust_id</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">维护内容：</td>
		            
		             <td>
			             <@s.textarea id="content" name="aftersales.content" cssClass="txtinput"onkeyup="checkLength(this,25000);" maxlength="25000" cssStyle="width:260px;height:100px;"/>
			             	<@s.fielderror><@s.param>saftersales.content</@s.param></@s.fielderror>
							<script type="text/javascript">
						     	CKEDITOR.replace('content');  
							</script>
			             
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Aftersales_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

