<html>
  <head>
    <title>添加关于我们</title>
  </head>
  <body>

<@s.form action="/admin_Aboutus_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:网站管理 > 基本功能 > 添加关于我们
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
             <td class="table_name">关于我们标题<font color='red'>*</font></td></td>
             <td>
             	<@s.textfield name="aboutus.title"  cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>aboutus.title</@s.param></@s.fielderror>
             </td>
           </tr>
             <tr>
             <td class="table_name">栏目名称<font color='red'>*</font></td></td>
             <td>
             
             <@s.select name="aboutus.ch_id" list="commparaList" listValue="para_key" listKey="para_value" headerKey="0" headerValue="请选择"/>
             <a href="/admin_Commpara_list.action?para_code_s=ch_id" target="_blank">
             [栏目管理]</a>
             <@s.fielderror><@s.param>aboutus.ch_id</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td width="19%" class="table_name">信息内容<font color='red'>*</font></td></td>
             <td>
             	<@s.textarea name="aboutus.content" id="content" cssClass="txtinput" cssStyle="width:500px;height:100px"/>
             	<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				<script type="text/javascript">
			     CKEDITOR.replace('content');  
				</script>
             	<@s.fielderror><@s.param>aboutus.content</@s.param></@s.fielderror>
             </td>
           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    <@s.token/>
   <@s.hidden name="key_name"/>
   ${listSearchHiddenField?if_exists}
   <@s.submit value="保存"/>
   <@s.reset value="重置"/>
   <@s.hidden name="mall_type" />
   <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Aboutus_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
 </body>
</html>