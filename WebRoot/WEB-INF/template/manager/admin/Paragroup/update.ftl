<html>
  <head>
    <title>修改参数组</title>
     <#include "/include/uploadInc.html">
   <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	<script type="text/javascript" src="/include/admin/js/morecat.js"></script>	
	<link rel="stylesheet" href="/include/admin/css/morecat.css" type="text/css" />
    <script type="text/javascript">
		   $(document).ready(function(){
		     //所属分类的回选
		      loadCat("${catIdStr?if_exists}","","","goods");
		     //提交表单
		     $("#modiy_form").submit(function(){
		          var cat_attr_str="";
		          $("input:hidden[name='all_cat_id_str']").each(function(){
		              cat_attr_str+=$(this).val()+"|";
		          }) 
		          $("#cat_attr_str").val(cat_attr_str);     
		        
		          return true;
		     }); 
		   });
		</script>
  </head>
  <body>

<@s.form action="/admin_Paragroup_update.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:商品管理 > 商品参数管理 > 修改参数组
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
             <td class="table_name">参数组名<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="paragroup.para_name" cssClass="txtinput" maxLength="20"/>
             	<@s.fielderror><@s.param>paragroup.para_name</@s.param></@s.fielderror>
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
				                 		<div id="catDiv" style="margin-left:0px;"></div>
				                 	</td>
			                 	   <td class="tdbottom" >
				                 	   <a class="oper_add" href="###" onclick="com_add_cat()" >[新增分类]</a>
				                 	   <@s.fielderror><@s.param>paragroup.cat_attr</@s.param></@s.fielderror>
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
           
           <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="paragroup.sort_no" cssClass="txtinput" maxLength="11" value="0" cssStyle="width:80px;" onkeyup="checkNum(this);"/>
             	<@s.fielderror><@s.param>paragroup.sort_no</@s.param></@s.fielderror>
             </td>
           </tr>
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
       <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>   
       <@s.hidden name="paragroup.para_group_id"/>
       <@s.hidden name="paragroup.in_date"/>
       <@s.hidden name="oldinfotitle" value="${(paragroup.para_name)?if_exists}"/>
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Paragroup_list.action','');"/>
       <@s.token/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

