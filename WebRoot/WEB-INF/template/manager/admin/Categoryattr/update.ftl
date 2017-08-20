<html>
  <head>
     <title>修改分类属性</title>
<script src="/wro/admin_category_update.js" type="text/javascript"></script>	 
	 <script type="text/javascript">
	  $(document).ready(function(){      
         //所属分类的回选
         cate_select(${cfg_topcatid?if_exists},1,"${modtype_s?if_exists}");   
	  });
	</script>
  </head>
  <body>
  <@s.form action="/admin_Categoryattr_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:功能模块 > 分类管理 > 分类属性管理  > 修改${modtype_name?if_exists}分类属性
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		  
           <tr>
             <td class="table_name">产品属性名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield  name="categoryattr.attr_name" cssClass="txtinput" maxlength="50"/>
             	<@s.fielderror><@s.param>categoryattr.attr_name</@s.param></@s.fielderror>             	            
             </td>
           </tr>
           <tr>
             <td class="table_name">所属分类<font color='red'>*</font></td>
             <td>
             	<table>
             		<tr>
             			<td class="tdbottom">
             				<div id="divlist"></div>
             			</td>
             			<td class="tdbottom">
             				<@s.fielderror><@s.param>cate_attr</@s.param></@s.fielderror>
	              		</td>
	              	</tr>
	            </table>       
             </td>   
           </tr>   
           <tr>
             <td class="table_name">属性类型<font color='red'>*</font></td>
             <td>
             	<@s.select name="categoryattr.attr_type" list=r"#{'0':'单行文本','1':'多行文本','2':'单选框','3':'复选框','4':'图片上传','5':'附件','6':'WEB编辑器','7':'日期'}"/><!--需移到参数管理-->    
             	<@s.fielderror><@s.param>categoryattr.attr_type</@s.param></@s.fielderror>  
             </td>
           </tr>
            <tr>
             <td class="table_name">是否必填:</td>
             <td>
             	<@s.radio name="categoryattr.is_must" list=r"#{'0':'不必填','1':'必填'}" checked="true" />
             	<@s.fielderror><@s.param>categoryattr.is_must</@s.param></@s.fielderror>  
             </td>
           </tr> 
            <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield    name="categoryattr.sort_no" cssClass="txtinput" maxLength="6" onkeyup="checkNum(this);"/>（只能输入数字，且越小越往前）
                <@s.fielderror><@s.param>category.sort_no</@s.param></@s.fielderror>   
             </td>
           </tr>      
           <tr>
             <td class="table_name">备选值:</td>
             <td>    
              	<@s.textarea name="default_val" cssClass="mailCss"  onkeyup="checkLength(this,600);" cssStyle="height:80px;width:420px;"/>   （如果是复选框，有多个备选值，请以|线隔开）        
             </td>     
           </tr>
           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	       <@s.hidden name="modtype_name"/>	
	       <@s.hidden name="level"/>
	       <@s.hidden id="modtype_name_id" name="modtype_name_id" value="${modtype_name_id?if_exists}"/>
	       <input type="hidden" name="categoryattr.module_type" value="${categoryattr.module_type?if_exists}" id="module_type"/>
	       <@s.token/>
	       <@s.hidden name="categoryattr.attr_id" />
	       ${listSearchHiddenField?if_exists}
		   <@s.hidden id="url_up_id" name="url_up_id" value="${url_up_id?if_exists}"/>
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
	       <@s.submit value="保存"/>     
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Categoryattr_list.action?url_up_id=${url_up_id?if_exists}&level=${level?if_exists}','');"/>    
   </div>
</div>
<div class="clear"></div>
</@s.form>
<script type="text/javascript">
  $(document).ready(function(){ 
 		 var catIds=$("#url_up_id").val();
		 if(catIds!=""){
		 	$("select[name='cat_attr']").attr("disabled","disabled");
		 }
  });
</script>
<div class="clear"></div>
 </body>
</html>