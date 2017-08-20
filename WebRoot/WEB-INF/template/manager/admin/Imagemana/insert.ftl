<html>
  <head>
    <title>添加图片</title>
    <script type="text/javascript" src="/include/common/js/common.js"></script>
    <#include "/include/uploadInc.html">
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
  </head>
  <body>
  <@s.form action="/admin_Imagemana_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:会员管理 > 空间管理 > 添加图片
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		           <tr>
		             <td class="table_name" cssStyle="width:220px;height:60px;" >图片路径<font color='red'>*</font></td>
		             <td>
		             		<table border="0" cellpadding="0" cellspacing="0">
			             		<tr>
			             			<td style="padding:0px;">
			             			    <div id="fileQueue"></div>
				              			 <@s.textfield name="imagemana.img_path" id="uploadresult" cssStyle="width:300px;"/>
			             			</td>
			             			<td style="padding-left:3px;">
			             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
			             			</td>
			             			<td style="padding-left:3px;">
			             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
				              			<script>uploadImgControlAndYin(1);uploadOneImg();</script>
			             			</td>
			             		</tr>
			             	</table>
		             		<@s.fielderror><@s.param>imagemana.img_path</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name" cssStyle="width:220px;height:60px;" >备注：</td>
		             <td>
		             	<@s.textarea name="imagemana.note" cssClass="txtinput" maxLength="100" cssStyle="width:260px;height:60px;"onkeyup="checkLength(this,100)"/>
		             	<@s.fielderror><@s.param>imagemana.note</@s.param></@s.fielderror>
		             </td>
		           </tr>
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Imagemana_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  
</body>
</html>

