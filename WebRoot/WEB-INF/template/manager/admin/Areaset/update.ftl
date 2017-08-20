<html>
  <head>
    <title>修改记录区域设置信息</title>
	<#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>	
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	<script type="text/javascript" src="/include/bmall/js/areaset.js"></script>	
	 <script type="text/javascript">
	  $(document).ready(function(){
	      //所属地区的回选
         loadArea("${areaIdStr?if_exists}","");
         //提交表单
         $("#com_insert").submit(function(){
              var area_attr_str="";
              $("input:hidden[name='all_area_id_str']").each(function(){
                  area_attr_str+=$(this).val()+"|";
              }) 
              $("#area_attr_str").val(area_attr_str);     
              
              
              return true;
         }); 
	  });
	  
	</script>
	<style type="text/css">
     .zitd{width:100px;text-align:right;}
     .zitxt{width:80px;}
     .datenum{width:20px;}
     .attr{border:1px solid #E3E3E3;}
	 .oper_add{color:#990000;}
	 .oper{margin-left:20px;color:#990000;}
	 .cert_td1{width:160px;padding-right:20px;text-align:right;}
	 .cat_sel{margin-left:7px;}
	</style>
  </head>
  <body>
  <@s.form action="/admin_Areaset_update.action" method="post" validate="true" id="modiy_form">
   <@s.hidden name="smode_id" />
   <@s.hidden name="areaset.areaset_id" />
<div class="postion">
  <!--当前位置-->
  当前位置:商城管理 > 系统配置 > 配送方式>更新区域设置
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		   <tr>
		             <td class="table_name">区域名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="areaset.areaset_name" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>areaset.areaset_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
		            <tr>
			             <td class="table_name">选择地区：</td>
			             <td style="padding-left:10px;">
			             	<table border="0" cellpadding="0" cellspacing="0">
				                 	<tr>
					                 	<td  colspan="2" class="tdbottom">
					                 		<div id="areaDiv" style="margin-left:-5px;" ></div>
					                 	</td>
				                 	   <td class="tdbottom" >
					                 	   <a class="oper_add" href="###" onclick="com_add_first_area()" >[添加开始地址]</a>
					                 	  
				                 	   </td>
				                 	    <td class="tdbottom" style="padding-left:10px;">
					                 	   <a class="oper_add" href="###" onclick="com_add_end_area()" >[添加到达地址]</a>
					                 	  
				                 	   </td>
				                 	</tr>
				                 </table>	
			             </td>
			           </tr>
		           <tr>
		             <td class="table_name">开始地址<font color='red'>*</font></td>
		             <td>
		                  <input type="hidden" id="area_attr_str_first" name="area_attr_str_first" value="${area_attr_str_first?if_exists}">
			             	<div>
				                 <div id="show_add_cat_first">
				                     <#if area_attr_str_first?if_exists!="">
				                     <div style='line-height:20px;' id='first_area_id'>
				                     ${area_attr_str_first_name?if_exists}<a class='oper' href='###' onclick='del_add_area(this)'>[删除]</a>
				                     </div>
				                     </#if>
				                 </div>
					         </div>
					          <@s.fielderror><@s.param>start_area</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">到达地址<font color='red'>*</font></td>
		             <td>
		               
		            	 <input type="hidden" id="area_attr_str" name="area_attr_str" value="${area_attr_str?if_exists}">
			             	<div>
				                 <div id="show_add_cat">
					                 <#list area_attr_list as areas>
		                            <div style='line-height:20px;'>
			                          <input type='hidden' name='all_area_id_str' value="${areas.id?if_exists} "/>${areas.val?if_exists} 
			                          <a class='oper' href='###' onclick='del_add_area(this)'>[删除]</a>
		                          </div>
	                    			 </#list>
				                 </div>
					         </div>
					          <@s.fielderror><@s.param>end_arear</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">首重价格<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="areaset.first_price" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>areaset.first_price</@s.param></@s.fielderror>
		             </td>
		           </tr>
	              <tr>
		             <td class="table_name">续重价格<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="areaset.cont_price" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>areaset.cont_price</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           <tr>
		             <td class="table_name">续重<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="areaset.cont_weight" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>areaset.cont_weight</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">首重<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="areaset.first_weight" cssClass="txtinput" maxLength="20"/>
		             	<@s.fielderror><@s.param>areaset.first_weight</@s.param></@s.fielderror>
		             </td>
		           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	       <@s.token/>    
	       <@s.hidden id="hidden_area_value" name="hidden_area_value"/>
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Areaset_list.action','smode_id=${smode_id?if_exists}');"/>    
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

