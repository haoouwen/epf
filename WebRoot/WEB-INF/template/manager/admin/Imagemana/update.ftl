<html>
  <head>
    <title>修改图片</title>
     <#include "/include/uploadInc.html">
    
  </head>
  <body>
  <@s.form action="/admin_Imagemana_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->当前位置:网站管理 > 空间管理 > 修改图片
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
		             <td class="table_name">会员名称:</td>
		             <td>
		             	${cust_name?if_exists}
		             </td>
		           </tr>
	           
	           <tr>
		             <td class="table_name">原图:</td>
		             <td>
		             	 <#list imagemana.img_path?if_exists?if_exists?split(',') as img>
		                       <#if img?if_exists>
					               <a class="group" href="${img?if_exists}">
					              	 <img class="showimgstyle" src="${img?if_exists}" style="width:110; height:110;"/>
					               </a> 
				               <#else>
				               	   <img src="/include/common/images/nopic.jpg"  width="110" height="110">
				               </#if>
		               </#list>
		                </td>
		            </tr>
	                <tr>
		             <td class="table_name" cssStyle="width:220px;height:60px;" >上传新图片<font color='red'>*</font></td>
		             <td>
		             		<table border="0" cellpadding="0" cellspacing="0">
			             		<tr>
			             			<td style="padding:0px;">
			             			    <div id="fileQueue"></div>
				              			 <@s.textfield name="reImagepath" id="uploadresult" cssStyle="width:300px;" />
			             			</td>
			             			<td style="padding-left:3px;">
			             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
			             			</td>
			             			<td style="padding-left:3px;">
			             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
				              			<script>uploadImgControlAndYin(1);uploadOneImg();</script>
			             			</td>
			             			<td>
											<@s.fielderror><@s.param>reImagepath</@s.param></@s.fielderror>
			             			</td>
			             		</tr>
			             	</table>
		             		<@s.fielderror><@s.param>imagemana.img_path</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">备注:</td>
		             <td>
		             	<@s.textarea name="imagemana.note" cssClass="txtinput" maxLength="100" onkeyup="checkLength(this,100)"/>
		             	<@s.fielderror><@s.param>imagemana.note</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">图片上传时间:</td>
		             <td>
		                ${imagemana.in_date?if_exists[0..18]}
		             </td>
		           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
   		   <@s.hidden  name="imagemana.serial_id"/>
	        <@s.hidden  name="imagemana.cust_id"/>
	       <@s.hidden  name="imagemana.user_id"/>
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

