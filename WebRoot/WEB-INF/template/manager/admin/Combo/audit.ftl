<html>
  <head>
    <title>审核套餐商品</title>
    <#include "/include/uploadInc.html">
  </head>
  <body>
  <@s.form action="/admin_Combo_auditState.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:营销推广 > 套餐商品 > 审核套餐商品
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
	           		<tr>
		             <td class="table_name">套餐名称:</td>
		             <td>
		             <@s.label name="combo.combo_name" value="${combo.combo_name?if_exists}"/>
		             </td>
		           </tr>
		           <tr>
		             <td class="table_name">商品:</td>
		             <td>
		             	<table class="wwtable" cellspacing="1" cellpadding="8" >
										  <tr>
								             <td>
								             	<#include "/WEB-INF/template/manager/searchCheckboxGoods.ftl"/>
								             	<div id="selralategoods" class="selralategoods">
								             		<table width="100%"><tr class="rg_title">
									             		<td class="span_td1">操作</td>
									             		<td class="span_td2">商品名称</td>
								             		</tr></table>
								             		<table id="selulrg" class="selulrg"  width="100%">
								             			<#list ralateList as rlt>
									             			<tr id="ralate_li${rlt.goods_id}">
																<td class="span_td1">
																<img src="/include/common/images/delete.png" onclick="del_ralate(${rlt.goods_id});">
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
		             <td class="table_name">套餐价格:</td>
		             <td>
		             	 <@s.label name="combo.combo_price" value="${combo.combo_price?if_exists}"/>
		             </td>
		           </tr>
		           <tr>
		             <td class="table_name">商品图片:</td>
		             <td>
		                     <#list  combo.combo_img?if_exists?split(',') as img>
						            <#if img?if_exists>
						               <a class="group" href="${img?if_exists}">
						              	         <img class="showimgstyle" src="${img?if_exists}" style="width:100px;height:100px;"/>
						               </a> 
						               <#else>
						                       <img src="/include/common/images/nopic.jpg" >
						               </#if>
					            </#list>
		             </td>
		           </tr>
	           		
		           <tr>
		             <td class="table_name">描述:</td>
		             <td>
		             	${combo.combo_center?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">库存:</td>
		             <td>
		             	<@s.label name="combo.stock" value="${combo.stock?if_exists}"/>
		             </td>
		           </tr>
		           
		            <tr>
		             <td class="table_name">排序:</td>
		             <td>
		             ${combo.sort_no?if_exists}
		             </td>
		           </tr>
	           		<tr>
					  <td class="table_name">审核状态<font color='red'>*</font></td>
					  <td >
					  	<#list infoStateList as infoState>
							<#if infoState.para_value?if_exists='1'>
							   <input type="radio" name="combo.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
						    <#elseif infoState.para_value?if_exists='2'>
						       <input type="radio" name="combo.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
						    </#if>
					    </#list>
			            <img class="ltip" src="/include/common/images/light.gif" alt="若审核不通过请点击审核不通过选项填写理由">
			           	<br/>
			            <@s.textarea name="reason"id="reason"  maxlength="100" cssStyle="width:260px;height:100px;display:none;"/>
					    <@s.fielderror><@s.param>combo.info_state</@s.param></@s.fielderror>
					  </td>
				 </tr>
				 
		</table>
		 <#include "/WEB-INF/template/manager/admin/Audithistory/auditlist.ftl"/>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	       <@s.token/>   
	       <@s.hidden  id="relate_goods"  name="combo.goods_str"/> 
	       <@s.hidden name="combo.trade_id"/> 
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="审核" />
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Combo_auditList.action','');"/>    
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

