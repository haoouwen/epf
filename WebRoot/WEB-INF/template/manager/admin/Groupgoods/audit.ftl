<html>
  <head>
    <title>审核团购商品</title>
    <!--图片-->
    <#include "/include/uploadInc.html">
  </head>
  <body>
  <@s.form action="/admin_Groupgoods_auditState.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置--> 	当前位置:营销推广 > 团购商品 > 审核团购商品
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
	                  <td class="table_name">商品名称:</td>
		             <td>
		                         <#if hidden_goodsname!=null>
					                ${hidden_goodsname}
					                <#else>
					         该商品已删除
					                </#if>
	                </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">团购标题:</td>
		             <td>
		             	${groupgoods.group_title?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">所属分类:</td>
		               <td>
		               ${cat_name?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">保证金:</td>
		             <td>
		             ${groupgoods.bond?if_exists} <font color="red">可用金额为：${memberfund.use_num}</font>
		             </td>
		           </tr>

		           <tr>
		             <td class="table_name">同一会员购买上限:</td>
		             <td>
		             ${groupgoods.cust_maxbuy?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">开始日期:</td>
		             <td>
		             ${groupgoods.start_date?if_exists[0..18]}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">结束日期:</td>
		             <td>
		             ${groupgoods.end_date?if_exists[0..18]}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">团购图片:</td>
         	                         <td width="500px" valign="top">
									             		<!--显示图片-->	             		
									             	    <ul id="show_pic" class="show_pic">
									             	    	<#if groupgoods.img_path!=null && groupgoods.img_path!=null>
									             	    	<#list groupgoods.img_path?if_exists?split(",") as img>
										             	   		<li>
											             	   		<input type="hidden" value="${img?if_exists}" name="gimg">
											             	   		<a href="${img?if_exists}" class="group cboxElement">
											             	   			<img src="${img?if_exists}" style="width:100px;height:100px;"></a>
											             	   		<div>
											             	   		</div>
										             	   		</li>
									             	   		</#list>
									             	   		</#if>
									             	    </ul>   
							               </div>  
							             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">详细描述:</td>
		             <td>
				             <div style="width:900px;height:auto;overflow:auto;" >
				             ${groupgoods.group_desc?if_exists}
				             </div>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">排序:</td>
		             <td>
		             ${groupgoods.sort_no?if_exists}
		             </td>
		           </tr>

		       <tr>
	             <td class="table_name">审核状态<font color='red'>*</font></td>
	             <td>
	             <#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='1'>
					   <input type="radio" name="groupgoods.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='2'>
				       <input type="radio" name="groupgoods.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
				    </#if>
    			 </#list>
                 <img class="ltip" src="/include/common/images/light.gif" alt="若审核不通过请点击审核不通过选项填写理由">
	             <@s.fielderror><@s.param>groupgoods.info_state</@s.param></@s.fielderror>
           	     <br/>
	             <@s.textarea name="reason"id="reason"  maxlength="100" cssStyle="width:260px;height:100px;display:none;"/>
	             </td>
	           </tr>
		</table>
		
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
  		 <@s.hidden name="groupgoods.goods_id"/>
	      <@s.hidden name="groupgoods.trade_id" />    
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Groupgoods_auditList.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

