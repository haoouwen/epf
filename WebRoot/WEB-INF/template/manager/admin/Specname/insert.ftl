<html>
  <head>
    <title>添加规格</title>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
    <script language="javascript" src="/include/admin/js/speclist.js"></script>
	<script type="text/javascript" src="/include/admin/js/morecat.js"></script>	
	<link rel="stylesheet" href="/include/admin/css/morecat.css" type="text/css" />
		<script type="text/javascript">
		   $(document).ready(function(){
		     //所属分类的回选
		      loadCat("${catIdStr?if_exists}","","","goods");
		   });
		</script>
  </head>
  <body>
  
  <@s.form action="/admin_Specname_insert.action" method="post" validate="true" id="specform">
<div class="postion">
 	当前位置:商品管理 > 规格管理 > 规格列表 > 添加规格
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 		           <tr>
		             <td class="table_name">规格名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield id="specname" name="specname.sname" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>specname.sname</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">规格备注:</td>
		             <td>
		             	<@s.textfield name="specname.snote" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>specname.snote</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">规格别名:</td>
		             <td>
		             	<@s.textfield name="specname.salias" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>specname.salias</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">显示类型:</td>
		             <td>
		             	<@s.radio name="specname.show_type" class="show_type_id" list=r"#{'0':'文字','1':'图片'}" value="0" onclick="getshowtype(this.value);" />
		             	<@s.fielderror><@s.param>specname.show_type</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr style="display:none;">
		             <td class="table_name">显示方式:</td>
		             <td>
		             	<@s.radio name="specname.show_method" list=r"#{'0':'平铺','1':'下拉'}" value="0"/>
		             	<@s.fielderror><@s.param>specname.show_method</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">所属类目:</td>
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
				                 	   <@s.fielderror><@s.param>specname.cat_attr</@s.param></@s.fielderror>
				                 	   <img class="ltip" src="/include/common/images/light.gif" alt="选完所属分类请点击新增分类">
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
		 <fieldset class="fset">
			  <legend ><input type="button" id="addspevalue" onclick="addSpecValue();" value="添加规格值" />&nbsp;<img class="ltip" src="/include/common/images/light.gif" alt="规格值为必填项！"><@s.fielderror><@s.param>all_spec_value_attr</@s.param></@s.fielderror></legend>
			  <div style="width:100%;height:230px;overflow:auto;" >
			   	    <table width="100%" cellspacing="1" cellpadding="4"  id="addspecvalue_id" >
						  <tr class="top_tr" >
			
			                 <th width="10%" align="center" class="top_td">规格排序</th>
			            
			                 <th width="20%" align="center" class="top_td">规格值</th>
			            
			                 <th width="40%" align="center" class="top_td"><span id="specimgid">规格图片</span></th>
							      
							 <th width="10%" align="center" class="top_td">操作</th> 
							 
						  </tr>
					</table>  
			   </div>
       </fieldset>   
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <input type="button" value="保存" onclick="submitSpecInfo('/admin_Specname_insert.action');"/>
	       <@s.reset value="重置"/>
	       <@s.hidden id="no_spec_image" name="no_spec_image"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Specname_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

