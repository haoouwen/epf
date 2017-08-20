<html>
  <head>
    <title>添加分类属性</title>
<script src="/wro/admin_category_update.js" type="text/javascript"></script>
  </head>
  <body>
  <@s.form action="/admin_Categoryattr_insert.action" method="post" validate="true" id="categoryattradd">
<div class="postion">
  <!--当前位置-->
  当前位置:功能模块 > 分类管理 > 分类属性管理 > 添加${modtype_name?if_exists}分类属性
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
           <tr>
             <td class="table_name" width=180px;>产品属性名称<font color='red'>*</font></td>
             <td>
             	<@s.textfield  name="categoryattr.attr_name" cssClass="txtinput" maxlength="50"/>
             	<@s.fielderror><@s.param>categoryattr.attr_name</@s.param></@s.fielderror>             	            
             </td>
           </tr> 
            <tr>
             <td class="table_name">选择信息类型:</td>
             <td>
             	<#if modtype_name_id?if_exists>
                 	<@s.select name="intr_type" list="moduleList" listValue="module_name" listKey="module_type" onchange="selectvalue(this.value);" id="intr_type"  disabled='true'/>
             	<#else>
             		<@s.select name="intr_type" list="moduleList" listValue="module_name" listKey="module_type" onchange="selectvalue(this.value);" id="intr_type" />
             	</#if>
             </td>
           </tr>
            <tr>    
           <tr id="cat">
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
             	<@s.radio name="categoryattr.is_must" list=r"#{'0':'不必填','1':'必填'}" value="0" checked="true" />
             	<@s.fielderror><@s.param>categoryattr.is_must</@s.param></@s.fielderror>  
             </td>
           </tr> 
            <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="categoryattr.sort_no" cssClass="txtinput" maxLength="6" onkeyup="checkNum(this);"  value="1"/>（只能输入数字，且越小越往前）
                <@s.fielderror><@s.param>category.sort_no</@s.param></@s.fielderror>   
             </td>
           </tr>      
           <tr>
             <td class="table_name">备选值:</td>
             <td>
             	<@s.textarea name="default_val" cssClass="mailCss"  onkeyup="checkLength(this,600);" cssStyle="height:80px;width:420px;"/>（如果是复选框，有多个备选值，请以|线隔开）        
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
	       <!--用于接收从页上传来的URL的值开始区域-->
		   <@s.hidden id="url_up_id" name="url_up_id" value="${url_up_id?if_exists}"/>
		   <!--用于接收从页上传来的URL的值结束区域-->
		   <@s.hidden name="categoryattr.attr_id" />
		   <@s.hidden name="categoryattr.cat_attr" value="${cat_value?if_exists}"/>	
		   <@s.hidden id="module_type" name="categoryattr.module_type"  />
		   <@s.hidden id="hidden_cat_value" name="hidden_cat_value" />
		   <@s.token/>	 
	       ${listSearchHiddenField?if_exists}
	       <@s.hidden name="nike_name_s"/>
	       <@s.submit  value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Categoryattr_list.action?url_up_id=${url_up_id?if_exists}&level=${level?if_exists}','');" />      
   </div>
</div>
<div class="clear"></div>
</@s.form>
<script type="text/javascript">
  $(document).ready(function(){ 
          //选择信息类型（分类）,从分类管理中心进入时方法
          var mod_type=$("#modtype_name_id").val();	    	 
          if(mod_type!=''){
          	 $("#intr_type").val(mod_type);
          }
          //获取模块值
		  val_type=$("#intr_type").val();
		  // 供应分类加载第一级菜单,第一个参数为父级ID，第二个参数为所属模块参数值,根据页面模块框加载不同的属性
		  if(val_type!=''){	 
           		cate_select("${cfg_topcatid?if_exists}",1,val_type); 
           }else{
           		cate_select("${cfg_topcatid?if_exists}",1,'supply');
           } 
          //赋给module_type值
          $("#module_type").val(val_type);
		  //根据隐藏域中的值是否为空判断是否必要设置分类select控件不可用			
		  $("#intr_type").change(function(){
				 $("#module_type").val($("#intr_type").val());
		  }); 
		  //根据隐藏域中的值是否为空判断是否必要设置分类select控件不可用
	 	  var catIds=$("#url_up_id").val();
		  if(catIds!=""){
			 	$("select[name='cat_attr']").attr("disabled","disabled");
		  }
  });
  
  //radio选中触发事件
  function selectvalue(objval){
       if(objval==""){
        	return alert("请选择信息类型");
       }else{
          $("#divlist").html("");
          cate_select("${cfg_topcatid?if_exists}",1,objval);      
       }
  }
</script>
<div class="clear"></div>
</body>
</html>