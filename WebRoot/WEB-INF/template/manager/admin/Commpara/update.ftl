<html>
  <head>
    <title>修改参数</title>
    <#include "/include/uploadInc.html">
  </head>
  <body>
  <@s.form action="/admin_Commpara_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:系统管理 > 系统管理 > 参数管理 > 修改参数
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
           <tr>
             <td class="table_name">参数编码<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="commpara.para_code" cssClass="txtinput" maxLength="20" />
             	<@s.fielderror><@s.param>commpara.para_code</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">参数名称<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="commpara.para_name" cssClass="txtinput" maxLength="20" />
             	<@s.fielderror><@s.param>commpara.para_name</@s.param></@s.fielderror>
             </td>
           </tr>
          <tr>
             <td class="table_name">参数键<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="commpara.para_key" cssClass="txtinput" maxLength="20" />
             	<@s.fielderror><@s.param>commpara.para_key</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">参数值<font color='red'> *</font></td>
             <td>
             	<@s.textfield name="commpara.para_value" cssClass="txtinput" maxLength="200" />
             	<@s.fielderror><@s.param>commpara.para_value</@s.param></@s.fielderror>
             </td>
           </tr>
           
           <tr>
            <td class="table_name">图标：</td>
	         <td>
	            <table border="0" cellpadding="0" cellspacing="0">
				             		<tr>
				             			<td style="padding:0px;">
				             			    <div id="fileQueue"></div>
					              			 <@s.textfield name="commpara.img_path" id="uploadresult" cssStyle="width:300px;"/>
				             			</td>
				             			<td style="padding-left:3px;">
				             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
				             			</td>
				             			<td style="padding-left:3px;">
				             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
				             				<img class='ltip' src="/include/common/images/light.gif" alt="建议图片大小：宽220px高55px">
					              			<script>uploadImgControlAndYin(1);uploadOneImg();</script>
				             			</td>
				             		</tr>
				</table>
	         	<@s.fielderror><@s.param>commpara.img_path</@s.param></@s.fielderror>
	         </td>
	       </tr>
           
             <tr>
             <td class="table_name">是否有效:</td>
             <td>
             	<@s.radio name="commpara.enabled" list=r"#{'0':'有效','1':'无效'}" />
             	<img class='ltip' src="/include/common/images/light.gif" alt="<@s.property value="%{getText('manager.admin.Commpara.enabled')}"/>"> 
             	<@s.fielderror><@s.param>commpara.enabled</@s.param></@s.fielderror>
             </td>
           </tr>
           <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="commpara.sort_no" cssClass="txtinput" style="width:80px;" maxLength="4"/>
             	<@s.fielderror><@s.param>commpara.sort_no</@s.param></@s.fielderror>
             	<img class='ltip' src="/include/common/images/light.gif"  alt="<@s.property value="%{getText('manager.sort_no')}"/>"> 
             </td>
           </tr>
           <tr>
             <td class="table_name">系统提示:</td>
             <td>
             	<span><img class='ltip' src="/include/common/images/light.gif" alt="编辑保存后,请点击更新缓存" />
             	[编辑保存后,请点击<a href="###" onclick="renewload();"><font color="red">更新缓存</font></a>]</span>
             </td>
           </tr>
           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	       <@s.token/>
	       <@s.hidden name="commpara.para_id"/>
	       ${listSearchHiddenField?if_exists}
	       <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	      <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Commpara_groupList.action','');"/>    
   </div>
</div>
<div class="clear"></div>
</@s.form>
  </body>
</html>