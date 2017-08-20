<html>
  <head>
    <title>审核秒杀商品</title>
    <#include "/include/uploadInc.html">
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","spike");
      });
	</script>
  </head>
  <body>
  
  <@s.form action="/admin_Spikegoods_auditState.action" method="post" validate="true" id="modiy_form">
<div class="postion">
 	当前位置:营销推广 > 秒杀商品 > 审核秒杀商品
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
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
		             <td class="table_name">秒杀标题:</td>
		             <td>
		             	${spikegoods.spike_title?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">所属分类:</td>
		               <td>
		               ${cat_String?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">秒杀价格:</td>
		             <td>
		             ${spikegoods.price?if_exists}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">开始时间:</td>
		             <td>
		             ${spikegoods.start_date?if_exists[0..18]}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">结束时间:</td>
		             <td>
		             ${spikegoods.end_date?if_exists[0..18]}
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">秒杀图片:</td>
         	                         <td width="500px" valign="top">
									             		<!--显示图片-->	             		
									             	    <ul id="show_pic" class="show_pic">
									             	    	<#if spikegoods.img_path!=null && spikegoods.img_path!=null>
									             	    	<#list spikegoods.img_path?if_exists?split(",") as img>
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
				             ${spikegoods.spike_desc?if_exists}
				             </div>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">排序:</td>
		             <td>
		             ${spikegoods.sort_no?if_exists}
		             </td>
		           </tr>

		       <tr>
	             <td class="table_name">审核状态<font color='red'>*</font></td>
	             <td>
	             <#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='1'>
					   <input type="radio" name="spikegoods.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='2'>
				       <input type="radio" name="spikegoods.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)">${infoState.para_key?if_exists}
				    </#if>
    			 </#list>
	             <@s.fielderror><@s.param>spikegoods.info_state</@s.param></@s.fielderror>
	                  <img class="ltip" src="/include/common/images/light.gif" alt="若审核不通过请点击审核不通过选项填写理由">
	           	    <br/>
	             <@s.textarea name="reason"id="reason"  maxlength="100" cssStyle="width:260px;height:100px;display:none;"/>
	             </td>
	           </tr>
		</table>
	</div>
	<div class="clear"/>
       <#include "/WEB-INF/template/manager/admin/Audithistory/auditlist.ftl"/>
   <div class="bsbut_detail">
 	       <@s.hidden name="spikegoods.goods_id"/>
	      <@s.hidden name="spikegoods.trade_id" />    
	       <@s.token/>    
	       ${listSearchHiddenField?if_exists}
           <@s.submit value="保存" />
	       <@s.reset value="重置"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Spikegoods_auditList.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

