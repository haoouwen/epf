<html>
  <head>
    <title>售后维护</title>
        <script type="text/javascript" src="/include/common/js/common.js"></script>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
      <script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
					     	CKEDITOR.replace('content');  
	</script>
  </head>
  <body>
  <@s.form action="/admin_Aftersales_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:商品管理 > 商品管理 > 售后维护
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		  <tr>
		             <td class="table_name">维护内容：</td>
		            
		             <td>
			             <@s.textarea id="content" name="aftersales.content" cssClass="txtinput"onkeyup="checkLength(this,10000);" maxlength="10000" cssStyle="width:260px;height:100px;"/>
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
    <@s.hidden name="aftersales.cust_id"/> 
	  <@s.token/>  
     ${listSearchHiddenField?if_exists}
      <@s.submit value="保存" />
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

