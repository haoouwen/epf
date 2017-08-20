<html>
  <head>
    <title>修改赠品</title>
     <#include "/include/uploadInc.html">
     <script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
     <link rel="stylesheet" href="/include/admin/css/pagetip.css" type="text/css" />
     <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","freegoods");
      });
	</script>
 
  </head>
<body >
<@s.form action="/admin_Freegoods_update.action"  method="post" validate="true"  id="detailForm">
<div class="postion">
 当前位置：营销推广 > 赠品 > 修改赠品
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
	    
	     <tr>
	           <td class="table_name" width="15%">赠品名称<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="freegoods.fg_name" cssStyle="width:300px;" maxLength="100" />
	             	<@s.fielderror><@s.param>freegoods.fg_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	          <tr>
	           <td class="table_name">赠品分类<font color='red'>*</font></td>
	           <td class="table_right">
	                <div id="catDiv" style="margin-left:-5px;"></div>
         				<@s.fielderror><@s.param>freegoods.cat_attr</@s.param></@s.fielderror>
	           </td>
	        </tr>
	         <tr>
	           <td class="table_name">图片<font color='red'>*</font></td>
	           <td class="table_right">
	                <table border="0" cellpadding="0" cellspacing="0">
             		<tr>
             			<td style="padding:0px;">
             			    <div id="fileQueue"></div>
	              			  <@s.textfield name="freegoods.img_path" id="uploadresult" cssStyle="width:275px;"/>
                        </td>
             			<td style="padding-left:3px;">
             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
             			</td>
             			<td style="padding-left:3px;">
             				<img src="/include/components/upload/borwssee.jpg" onClick="showpicture('uploadresult');"/>
	              			<script>uploadImgControlAndYin(1);sysUploadImg('uploadifyfile','uploadresult','fileQueue');</script>
             			</td>
             			<td>
             			   <@s.fielderror><@s.param>freegoods.img_path</@s.param></@s.fielderror>
             			    【建议图片宽80px,高30px】 
             			</td>
             		</tr>
             	</table>
            </td>
            </tr>
	         
	    <tr>
	           <td class="table_name">排序<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="freegoods.fg_sort" cssClass="txtinput" maxLength="5" cssStyle="width:50px;" onkeyup="checkNum(this);"/>
	             	<@s.fielderror><@s.param>freegoods.fg_sort</@s.param></@s.fielderror>
	           </td>
	        </tr>
			 <tr>
	           <td class="table_name">状态<font color='red'>*</font></td>
	           <td class="table_right">
	           <@s.radio name="freegoods.fg_state" list=r"#{'0':'启用','1':'禁用'}"/>
	             	<@s.fielderror><@s.param>freegoods.fg_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
			 <td class="table_name">详细内容<font color='red'>*</font></td>
			  <td>
			   <@s.textarea id="content" name="freegoods.fg_content" cssClass="txtinput" />
			   <@s.fielderror><@s.param>freegoods.fg_content</@s.param></@s.fielderror>
			   <script type="text/javascript">
				  CKEDITOR.replace('content');  
				</script>
				</td>			
	        </tr>	
        </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
         <@s.hidden name="freegoods.fg_number" value="0"/>
	     <@s.hidden name="freegoods.fg_price" value="0"/>
           <@s.token/>
 		   <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.hidden name="freegoods.fg_id"/>
		   ${listSearchHiddenField?if_exists}
           <@s.submit value="保存"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Freegoods_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

