<html>
  <head>
    <title>添加门店服务</title>
    <#include "/include/uploadInc.html">
    <script src="/include/components/colorpick/iColorPicker.js" type="text/javascript"></script>
     <script type="text/javascript" src="/include/common/js/common.js"></script>
     <script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
  </head>
  <body>  
<@s.form action="/admin_Storeservce_insert.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：会员管理 > 区域代理 > 添加门店服务
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		
			 <tr>
	           <td class="table_name" width="20%">门店服务名称<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="storeservce.store_name" cssClass="txtinput" maxLength="50" />
	                 <input name="storeservce.store_color" id="title_color" type="text" value="" size="10"  class="iColorPicker" title="请选择颜色" />
	             	<@s.fielderror><@s.param>storeservce.store_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">门店服务图标<font color='red'>*</font></td>
	           <td class="table_right">
	                <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="storeservce.store_img" id="uploadresult" cssStyle="width:200px;"/>
                        </td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onClick="showpicture('uploadresult');"/>
	              			<script>uploadOneImg();</script>
             			</td>
             			<td>
             			   <@s.fielderror><@s.param>storeservce.store_img</@s.param></@s.fielderror>
             			   【建议图片宽80px,高80px】
             			</td>
             		</tr>
             	</table>
	          
	             	
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">状态<font color='red'>*</font></td>
	           <td class="table_right">
	                <@s.radio name="storeservce.state" list=r"#{'0':'启用','1':'禁用'}" value="0" onclick="getMemValue();"/>
	             	<@s.fielderror><@s.param>storeservce.state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">详细<font color='red'>*</font></td>
	           <td>
	                <@s.textarea id="content" name="storeservce.content" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="25000"/>
		                 <@s.fielderror><@s.param>storeservce.content</@s.param></@s.fielderror>
							<script type="text/javascript">
						     	CKEDITOR.replace('content');  
							</script>
	           </td>
	        </tr>	
	        
        </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
            <@s.token/>
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Storeservce_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

