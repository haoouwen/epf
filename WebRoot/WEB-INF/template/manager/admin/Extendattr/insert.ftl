<html>
  <head>
    <title>添加属性</title>
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	<script type="text/javascript" src="/include/admin/js/morecat.js"></script>	
	<link rel="stylesheet" href="/include/admin/css/morecat.css" type="text/css" />
	<script type="text/javascript">
	   $(document).ready(function(){
	     //所属分类的回选
	     loadCat("${catIdStr?if_exists}","","","goods");
	     //提交表单
	     $("#cat_attr_s").submit(function(){
	          var cat_attr_str="";
	          $("input:hidden[name='all_cat_id_str']").each(function(){
	              cat_attr_str+=$(this).val()+"|";
	          }) 
	          $("#cat_attr_str").val(cat_attr_str);     
	          return true;
	     }); 
	   });
	   function chekValue(){
	   		var defa=$("#de_value").val();
	   		if(defa.indexOf("，")>=0){
	   			$("#de_value").val("");
	   			var newdel=defa.replace("，",",");
	   			$("#de_value").val(newdel);
	   			
	   		}
	   }
	   
	</script>
  </head>
  <body>
  
  <@s.form action="/admin_Extendattr_insert.action" method="post" validate="true" id="cat_attr_s">
<div class="postion">
  <!--当前位置-->
  当前位置：商品管理> 商品属性管理 > 属性列表 > 添加属性
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		           <tr>
		             <td class="table_name">属性名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="extendattr.attr_name" cssClass="txtinput" maxLength="20" />
		             	<@s.fielderror><@s.param>extendattr.attr_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">选项类型:</td>
		             <td>
		                <@s.radio name="extendattr.option_type" list=r"#{'0':'选择项','1':'输入框','2':'下拉框'}" value="1" checked="true" />
		             	<@s.fielderror><@s.param>extendattr.option_type</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">选择项可选值:</td>
		             <td>
	             		<@s.textfield id="de_value" name="extendattr.default_value" cssClass="txtinput" maxLength="100" onkeyup="chekValue();" cssStyle="width:360px"/>
	             		<font style="color:red">若可选值个数为多个，请用英文逗号隔开。例：0,1,2</font>
		             	<@s.fielderror><@s.param>extendattr.default_value</@s.param></@s.fielderror>
		             </td>
		             
		           </tr>
	           
		           <tr>
		             <td class="table_name">是否显示:</td>
			         <td>
			         	<@s.radio name="extendattr.is_display" list=r"#{'0':'显示','1':'不显示'}" value="0" checked="true" />
			         	<@s.fielderror><@s.param>extendattr.is_display</@s.param></@s.fielderror> 
			         </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">排序:</td>
		             <td>
		             	<@s.textfield name="extendattr.sort_no" cssClass="txtinput" maxLength="11" value="0" cssStyle="width:80px;" onkeyup="checkNum(this)"/>
		             	<@s.fielderror><@s.param>extendattr.sort_no</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">商品分类<font color='red'>*</font></td>
		             <td>
			             <@s.hidden type="hidden" id="cat_attr_str" name="cat_attr_str"/>
		             	 <div class="morecatdiv">
			                 <table border="0" cellpadding="0" cellspacing="0">
			                 	<tr>
				                 	<td  colspan="2" class="tdbottom">
				                 		<div id="catDiv" style="margin-left:-5px;"></div>
				                 	</td>
			                 	   <td class="tdbottom" >
				                 	   <a class="oper_add" href="###" onclick="com_add_cat()" >[新增分类]</a>
				                 	   <@s.fielderror><@s.param>cat_attr_str</@s.param></@s.fielderror>
			                 	   </td>
			                 	</tr>
			                 </table>	
			                 
			                 <#if cat_attr_list?if_exists?size gt 0>
				                 <div id="show_add_cat" class="show_add_cat">
				                     <#list cat_attr_list as cat>
				                          <div style='line-height:20px;'>
					                          <input type='hidden' name='all_cat_id_str' value="${cat.id?if_exists} "/>${cat.val?if_exists} 
					                          <a class='oper' href='###' onclick='del_add_cat(this)'>[删除]</a>
				                          </div>
				                     </#list>
				                 </div>
			                 </#if>
			                 
				         </div>
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Extendattr_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

