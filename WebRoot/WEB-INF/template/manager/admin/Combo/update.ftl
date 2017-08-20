<html>
  <head>
    <title>修改套餐表</title>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/common/js/jquery.easydrag.handler.js"></script>
	<script type="text/javascript" src="/include/admin/js/goods.js"></script>
	<script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
	<script type="text/javascript" src="/include/common/js/jqModal.js"></script>
	<script type="text/javascript" src="/include/common/js/tablePlugin.js"></script>
    <link href="/include/common/css/jqModal.css" rel="stylesheet" type="text/css" />  
	<link href="/include/admin/css/goods.css" rel="stylesheet" type="text/css" />  
  <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	 <script type="text/javascript">
     $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","combo");
      });
	</script>
  </head>
  <body>
  <@s.form action="/admin_Combo_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:营销推广 > 套餐商品 > 修改套餐商品
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
       		   		<tr>
		             <td class="table_name">套餐名称<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="combo.combo_name" cssClass="txtinput" maxlength="30" id="comboName" onblur="filterWord('comboName');"/>
		             	<@s.fielderror><@s.param>combo.combo_name</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		            <tr>
		             <td class="table_name">所属分类<font color='red'>*</font></td>
		               <td>
		                 <table>
			             		<tr>
			             			<td class="tdbottom">
			             				<div id="catDiv" style="margin-left:0px;"></div>
			             			</td>
			             			<td class="tdbottom">
			             				<@s.fielderror><@s.param>combo.cat_attr</@s.param></@s.fielderror>
				              		</td>
				              	</tr>
				         </table>
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">商品<font color='red'>*</font></td>
		             <td>
		             	<table class="wwtable" cellspacing="1" cellpadding="8" >
										  <tr>
								             <td>
								             	<input type="button" value="修改相关商品" onclick="addRalateGoods();"/>
								             	<div id="selralategoods" class="selralategoods">
								             	     <table width="100%"><tr class="rg_title">
									             		<td class="span_td1">操作</td>
									             		<td class="span_td2">商品名称</td>
								             		 </tr></table>
								             		<table id="selulrg" class="selulrg"  width="100%">
								             			<#list ralateList as rlt>
									             			<tr id="ralate_li${rlt.goods_id}">
																<td class="span_td1">
																<img src="/include/common/images/delete.png" onclick="del_ralate(${rlt.goods_id});"title='删除'>
																<input class="al_goods_id" type="hidden" value="${rlt.goods_id}">
																</td>
																<td class="span_td2">${rlt.goods_name}</td>
															 </tr>
														 </#list>
								             		</table>
								             	</div>
								             </td>
								           </tr>
					       	    	</table>
					       	   <@s.fielderror><@s.param>combo.goods_str</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">套餐价格<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="combo.combo_price" cssStyle="width:50px;" onkeyup="checkRMB(this);"maxlength="6"/>元
		             	<@s.fielderror><@s.param>combo.combo_price</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           <tr>
		             <td class="table_name">商品图片<font color='red'>*</font></td>
		             <td>
					     <table border="0" cellpadding="0" cellspacing="0">
		             		<tr>
		             			<td style="padding:0px;">
		             			    <div id="fileQueue"></div>
			              			  <@s.textfield name="combo.combo_img" id="uploadresult" cssStyle="width:300px;"/>
		             			</td>
		             			<td style="padding-left:3px;">
		             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
		             			</td>
		             			<td style="padding-left:3px;">
		             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
			              			<script>uploadGoodsImageMulti();</script>
		             			</td>
		             			<td>
		             			   &nbsp;&nbsp;<@s.fielderror><@s.param>combo.combo_img</@s.param></@s.fielderror>
		             			</td>
		             		</tr>
		             	</table>  
		             </td>
		           </tr>
	           		
		           <tr>
		             <td class="table_name">套餐商品描述<font color='red'>*</font></td>
		             <td>
		             	<@s.textarea name="combo.combo_center" id="content" cssClass="txtinput"/>
						<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
						<script type="text/javascript">
					     	var editor = CKEDITOR.replace( 'content');  
						</script>
						<@s.fielderror><@s.param>combo.combo_center</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
	           <tr>
		             <td class="table_name">排序:</td>
		             <td>
		             	<@s.textfield name="combo.sort_no" cssClass="txtinput" onkeyup="checkDigital(this);"/>
		             	<@s.fielderror><@s.param>combo.sort_no</@s.param></@s.fielderror>
		             </td>
		        </tr>
	           
		           <tr>
		             <td class="table_name">库存<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="combo.stock" cssClass="txtinput" onkeyup="checkLength(this,10);" maxlength="10"/>
		             	<@s.fielderror><@s.param>combo.stock</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		      <tr>
	             <td class="table_name">审核状态:</td>
	             <td>
	             <#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='1'>
					   <input type="radio" name="combo.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)"  <#if combo.info_state='1'>checked</#if> >${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='3'>
				       <input type="radio" name="combo.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)"  <#if combo.info_state='3'>checked</#if> >${infoState.para_key?if_exists}
				    </#if>
    			 </#list>
	             <@s.fielderror><@s.param>combo.info_state</@s.param></@s.fielderror>
	                  <img class="ltip" src="/include/common/images/light.gif" alt="若禁用请点击禁用选项填写理由">
	           	    <br/>
	             <@s.textarea name="reason"id="reason"  maxlength="100" cssStyle="width:260px;height:100px;display:none;"/>
	             </td>
	           </tr>
	           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
       <@s.token/>   
       <@s.hidden  id="relate_goods"  name="combo.goods_str"/> 
       <@s.hidden name="combo.trade_id"/> 
       <@s.hidden name="combo.cust_id"/> 
       <@s.hidden name="is_update" value="1"/>  
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Combo_list.action','');"/>    
       <#include "/WEB-INF/template/manager/searchCheckboxGoods.ftl"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

