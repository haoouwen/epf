<html>
  <head>
    <title>修改商城模板</title>
     <#include "/include/uploadInc.html">
     <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>  
  </head>
  <body>
  <@s.form action="/admin_Malltemplate_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->当前位置:网站管理 > 网站页面 > 商城模板管理 > 修改商城模板
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
		          <tr>
		             <td class="table_name">模板路径:</font></td>
		             	<td>
		                <@s.textfield name="malltemplate.template_path" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>malltemplate.template_path</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">模板封面图<font color='red'>*</font></td>
		             <td>
		             	<table border="0" cellpadding="0" cellspacing="0">
		             		<tr>
		             			<td style="padding:0px;">
		             			    <div id="fileQueue"></div>
			              			 <@s.textfield name="malltemplate.template_image" id="uploadresult" cssStyle="width:300px;"/>
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
		             	<@s.fielderror><@s.param>malltemplate.template_image</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">作者<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="malltemplate.author" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>malltemplate.author</@s.param></@s.fielderror>
		             </td>
		           </tr>
		             <#if malltemplate.is_enable?if_exists=="0">
		                <@s.hidden name="malltemplate.is_enable" value="${malltemplate.is_enable?if_exists}"/>
		             <#else>
             	  <tr>
		             <td class="table_name">是否启用:</font></td>
		             <td>
		                <@s.radio name="malltemplate.is_enable" list=r"#{'0':'是','1':'否'}" />
             	     	<@s.fielderror><@s.param>malltemplate.is_enable</@s.param></@s.fielderror>
		             </td>
	             </tr>
		           </#if>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
    	<@s.token/>
  		  <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       <@s.hidden name="malltemplate.in_date"/>    
	       <@s.hidden name="malltemplate.trade_id"/>
	       <@s.hidden name="malltemplate.template_code"/>
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Malltemplate_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  
</body>
</html>

