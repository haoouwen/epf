<html>
  <head>
    <title>添加短信模板</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
    <script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
					     	CKEDITOR.replace('content');  
	</script>
  </head>
  <body>
  <@s.form action="/admin_Commutemplate_dxinsert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:会员管理 > 通讯模板管理 > 短信模板 > 添加短信模板
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
		           
	           <tr>
		             <td class="table_name" Style="width:220px;">模板代码<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="commutemplate.temp_code" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>commutemplate.temp_code</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name" Style="width:220px;">模板名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="commutemplate.temp_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>commutemplate.temp_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">发送至<font color='red'>*</font></td>
		             <td>
		             	<@s.radio name="commutemplate.send_who" list=r"#{'1':'买家','2':'卖家','3':'买家和卖家'}"/>
		             	<@s.fielderror><@s.param>commutemplate.send_who</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">买家模板内容:</td>
		             <td>
		             	<@s.textarea id="content" name="commutemplate.temp_con" cssClass="txtinput" maxLength="1000"onkeyup="checkLength(this,1000)"/>
		             	<@s.fielderror><@s.param>commutemplate.temp_con</@s.param></@s.fielderror>
		             	<script type="text/javascript">
					     	CKEDITOR.replace('content');  
						</script>
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">卖家模板内容:</td>
		             <td>
		             	<@s.textarea id="contentTwo" name="commutemplate.temp_conTwo" cssClass="txtinput" maxLength="1000"onkeyup="checkLength(this,1000)"/>
		             	<@s.fielderror><@s.param>commutemplate.temp_conTwo</@s.param></@s.fielderror>
		             	<script type="text/javascript">
					     	CKEDITOR.replace('contentTwo');  
						</script>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name" Style="width:220px;">标签解释:</td>
		             <td>
		                <@s.textarea name="commutemplate.tag_desc" cssClass="txtinput" maxLength="300" cssStyle="width:500px;height:150px;"onkeyup="checkLength(this,300)"/>
		             	
		             	<@s.fielderror><@s.param>commutemplate.tag_desc</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
	           <!--是否为系统模板默认否-->
		           <@s.hidden name="commutemplate.sys_temp" cssClass="txtinput" maxLength="20" value="0"/> 
		           <@s.fielderror><@s.param>commutemplate.sys_temp</@s.param></@s.fielderror>
		           
	           <!--模板类型默认短信（该模板为短信模板）-->
		           <@s.hidden name="commutemplate.temp_type" cssClass="txtinput" maxLength="20" value="1"/> 
		           <@s.fielderror><@s.param>commutemplate.temp_type</@s.param></@s.fielderror>
		             
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>    
	       ${listSearchHiddenField?if_exists}
           <@s.token/> 
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Commutemplate_dxlist.action','');"/>    
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

