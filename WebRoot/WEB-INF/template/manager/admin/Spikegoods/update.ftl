<html>
  <head>
    <title>修改秒杀商品</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <#include "/include/uploadInc.html">
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","spike");
      });
	</script>
  </head>
  <body>
  
  <@s.form action="/admin_Spikegoods_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
 	当前位置:营销推广 > 秒杀商品 > 修改秒杀商品
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		  <tr>
	                  <td class="table_name" style="width:220px;" height="60px;">商品名称:</td>
		             <td>
		                	<span id="goodsname">
			                	 <#if hidden_goodsname!=null>
				                ${hidden_goodsname}
				                <#else>
				         该商品已删除
				                </#if>
		                	</span>
			             	<@s.hidden id="hidden_goodsname" name="hidden_goodsname"/>
			             	<a onclick="addRalateGoods();"><input type="button" value="修改商品" /></a>
			             	<@s.fielderror><@s.param>pointsgoods.goods_id</@s.param></@s.fielderror>
	                </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">秒杀标题<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="spikegoods.spike_title" id="spikeGoods" onblur="filterWord('spikeGoods');" cssClass="txtinput"  cssStyle="width:445px;"/>
		             	<@s.fielderror><@s.param>spikegoods.spike_title</@s.param></@s.fielderror>
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
			             				<@s.fielderror><@s.param>spikegoods.cat_attr</@s.param></@s.fielderror>
				              		</td>
				              	</tr>
				         </table>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">秒杀价格<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="spikegoods.price" cssClass="txtinput" onkeyup="checkLength(this,25000);"onkeyup="checkRMB(this)" maxlength="25000"/>
		             	<@s.fielderror><@s.param>spikegoods.price</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">开始时间<font color='red'>*</font></td>
		             <td>
		             	<input type="text"  value="<#if spikegoods!=null> ${spikegoods.start_date?if_exists}</#if>"   name="spikegoods.start_date"  class="Wdate upWdate" style="width:162px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'updown2\')}',minDate:'#F{$dp.$D(\'updown2\',{d:-1})}'})"/>
		             	<@s.fielderror><@s.param>spikegoods.start_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">结束时间<font color='red'>*</font></td>
		             <td>
		             	  <input type="text"   value="<#if spikegoods!=null>${spikegoods.end_date?if_exists}</#if>"   name="spikegoods.end_date" class="Wdate downWdate" style="width:162px;" id="updown2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'updown1\');}',maxDate:'#F{$dp.$D(\'updown1\',{d:1});}'})"/>
		             	<@s.fielderror><@s.param>spikegoods.end_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		            <tr>
		             <td class="table_name">秒杀图片<font color='red'>*</font></td>
						  <td >
					          <table border="0" cellpadding="0" cellspacing="0">
			             		<tr>
			             			<td style="padding:0px;">
			             			    <div id="fileQueue3"></div>
				              			 <@s.textfield name="spikegoods.img_path" id="uploadresult3" cssStyle="width:300px;"/>
			             			</td>
			             			<td style="padding-left:3px;">
			             				<input type="file" name="uploadifyfile" id="uploadifyfile3"/>
			             			</td>
			             			<td style="padding-left:3px;">
			             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult3');"/>
				              			<img class="ltip" src="/include/common/images/light.gif" alt="建议上传最多不超过10张左右图片">
				              			<script>uploadGoodsImageMoreMulti("uploadifyfile3","uploadresult3","fileQueue3");</script>
			             			</td>
			             		</tr>
			             	</table>
				             <@s.fielderror><@s.param>spikegoods.img_path</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">详细描述<font color='red'>*</font></td>
		             <td>
		             	       <@s.textarea name="spikegoods.spike_desc" id="content" cssClass="txtinput"   />
								<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
								<script type="text/javascript">
							     CKEDITOR.replace( 'content');  
								</script>
		                    	<@s.fielderror><@s.param>spikegoods.spike_desc</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">排序<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="spikegoods.sort_no" cssClass="txtinput" onkeyup="checkDigital(this);" maxlength="6"/>
		             	<@s.fielderror><@s.param>spikegoods.sort_no</@s.param></@s.fielderror>
		             </td>
		           </tr>

		          
		           <!-- <tr>
		             <td class="table_name">库存<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="spikegoods.stock" cssClass="txtinput" onkeyup="checkDigital(this);"maxlength="6"/>
		             	<@s.fielderror><@s.param>spikegoods.stock</@s.param></@s.fielderror>
		             </td>
		           </tr> -->
		          
	           <tr>
	             <td class="table_name">审核状态:</td>
	             <td>
	             <#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='1'>
					   <input type="radio" name="spikegoods.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)"  <#if spikegoods.info_state='1'>checked</#if>>${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='3'>
				       <input type="radio" name="spikegoods.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)"  <#if spikegoods.info_state='3'>checked</#if>>${infoState.para_key?if_exists}
				    </#if>
    			 </#list>
	             <@s.fielderror><@s.param>spikegoods.info_state</@s.param></@s.fielderror>
	                  <img class="ltip" src="/include/common/images/light.gif" alt="若禁用请点击禁用选项填写理由">
	           	    <br/>
	             <@s.textarea name="reason"id="reason"  maxlength="100" cssStyle="width:260px;height:100px;display:none;"/>
	             </td>
	           </tr>
		</table>
	</div>
	<div class="clear"/>
	         <#include "/WEB-INF/template/manager/admin/Audithistory/auditlist.ftl"/>
   <div class="bsbut_detail">
       <@s.hidden name="spikegoods.goods_id" id="goods_id"/>
       <@s.hidden name="spikegoods.trade_id" />    
       <@s.hidden name="spikegoods.cust_id" />    
       <@s.hidden name="spikegoods.user_id" />    
       <@s.token/>    
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Spikegoods_list.action','');"/>
       <#include "/WEB-INF/template/manager/searchRadioGoods.ftl"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

