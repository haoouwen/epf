<html>
  <head>
    <title>添加分类导航</title>
   <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	<script type="text/javascript" src="/include/admin/js/morecat.js"></script>	
	<link rel="stylesheet" href="/include/admin/css/morecat.css" type="text/css" />
	<script type="text/javascript">
	   $(document).ready(function(){
	     //所属分类的回选
	       loadCatMore("${catIdStr?if_exists}","","","goods","2");
	      //提交表单
		  $("#pgform").submit(function(){
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
<@s.form action="/admin_Catnav_insert.action" method="post" validate="true"  id="pgform">
<div class="postion">
	当前位置：网站管理 > 商城导航 > 分类导航
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		     
		     <tr>
	           <td class="table_name" width="20%">分类别名<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="catnav.custom_name"  maxLength="30" cssClass="txtinput" />
	           </td>
	        </tr>	
			 <tr>
	           <td class="table_name">组合分类<font color='red'>*</font></td>
	           <td class="table_right">
	             <@s.hidden type="hidden" id="cat_attr_str" name="cat_attr_str"/>
			             	 <div class="morecatdiv">
				                 <table border="0" cellpadding="0" cellspacing="0">
				                 	<tr>
					                 	<td  colspan="2" class="tdbottom">
					                 		<div id="catDiv" style="margin-left:-5px;"></div>
					                 	</td>
				                 	   <td class="tdbottom" >
					                 	   <a class="oper_add" href="###" onclick="com_add_cat()" >[新增分类]</a>
					                 	   <@s.fielderror><@s.param>catnav.cat_attr</@s.param></@s.fielderror>
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
	           <td class="table_name" width="20%">排序<font color='red'>*</font></td>
	           <td class="table_right">
	             	<@s.textfield name="catnav.sort_no" cssStyle="width:50px;" maxLength="20" value="0" />
	             	<img class="ltip" src="/include/common/images/light.gif" alt="数字越小，排的越前！">
	             	<@s.fielderror><@s.param>catnav.sort_no</@s.param></@s.fielderror>
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
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Catnav_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

