<html>
  <head>
    <title>审核积分商品</title>
    <#include "/include/uploadInc.html">
  </head>
  <body>
<@s.form action="/admin_Pointsgoods_auditState.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:营销推广 > 积分商品 > 审核积分商品
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
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
             <td class="table_name">购买积分:</td>
             <td>
                  ${pointsgoods.buy_points?if_exists}
             </td>
           </tr>
       
           <tr>
             <td class="table_name">积分图片:</td>
             <td>
		             <#list pointsgoods.inter_image?if_exists?split(',') as img>
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
             <td class="table_name">积分商品描述:</td>
             <td>
               ${pointsgoods.description?if_exists}
             </td>
           </tr>
           <tr>
             <td class="table_name">库存:</td>
             <td>
                 ${pointsgoods.stock?if_exists}
             </td>
           </tr>
           
        <tr>
         <td class="table_name">审核状态<font color='red'>*</font></td>
         <td>
             <#list infoStateList as infoState>
				<#if infoState.para_value?if_exists='1'>
				   <input type="radio" name="pointsgoods.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
			    <#elseif infoState.para_value?if_exists='2'>
			       <input type="radio" name="pointsgoods.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
			    </#if>
			 </#list>
             <@s.fielderror><@s.param>pointsgoods.info_state</@s.param></@s.fielderror>
                  <img class="ltip" src="/include/common/images/light.gif" alt="若审核不通过请点击审核不通过选项填写理由">
           	    <br/>
             <@s.textarea name="reason"id="reason"  maxlength="100" cssStyle="width:260px;height:100px;display:none;"/>
         </td>
       </tr>
		 
		 
		 <!--详细页table的数据结束-->
		</table>
     <#include "/WEB-INF/template/manager/admin/Audithistory/auditlist.ftl"/>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
       <@s.hidden name="pointsgoods.trade_id" />
       <@s.token/>    
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Pointsgoods_auditList.action','');"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

