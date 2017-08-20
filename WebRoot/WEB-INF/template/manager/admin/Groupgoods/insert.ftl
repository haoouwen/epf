<html>
  <head>
    <title>添加团购商品</title>
    <script type="text/javascript" src="/include/admin/js/groupgoods.js"></script>
    <!--日期-->
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	 <!--图片-->
    <#include "/include/uploadInc.html">
	<!--所属分类开始-->
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","group");
      });
	</script>
	<!--所属分类结束-->
  </head>
  <body>
  <@s.form action="/admin_Groupgoods_insert.action" method="post" validate="true" id="modiy_form"name="formshopcongif">
<div class="postion">
  <!--当前位置-->当前位置:营销推广 > 团购商品 > 添加团购商品
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 <tr>
	                  <td class="table_name">商品名称<font color='red'>*</font></td>
		             <td>
		             	<span id="goodsname">${hidden_goodsname}</span>
			             	<@s.hidden id="hidden_goodsname" name="hidden_goodsname"/>
			             	<a onclick="addRalateGoods();"><input type="button" value="添加商品" /></a>
			             	<@s.fielderror><@s.param>groupgoods.goods_id</@s.param></@s.fielderror>
	                </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">团购标题<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.group_title" id="groupGoods" onblur="filterWord('groupGoods');" cssClass="txtinput" cssStyle="width:445px;"maxlength="200"/>
		             	<@s.fielderror><@s.param>groupgoods.group_title</@s.param></@s.fielderror>
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
			             				<@s.fielderror><@s.param>groupgoods.cat_attr</@s.param></@s.fielderror>
				              		</td>
				              	</tr>
				         </table>
		             </td>
		           </tr>
	              
	              
		           <tr>
		              <td class="table_name">团购价格<font color='red'>*</font></td>
		              <td>
		                    <@s.textfield  style="width:182px;" name="shot" id="shotprice"onkeyup="checkRMB(this)"maxlength="6"/>
		                    <@s.fielderror><@s.param>shot</@s.param></@s.fielderror> 
					  </td>
		           </tr>
	           
	               <tr>
		             <td class="table_name">保证金<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.bond" cssClass="txtinput" onkeyup="checkLength(this,25000);"onkeyup="checkRMB(this)" maxlength="6"/>
		             	<@s.fielderror><@s.param>groupgoods.bond</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">同一会员购买上限<font color='red'>*</font></td>
		             <td>
		             	<@s.textfield name="groupgoods.cust_maxbuy" cssClass="txtinput" onkeyup="checkDigital(this);" maxlength="6"/>
		             	<@s.fielderror><@s.param>groupgoods.cust_maxbuy</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">开始时间<font color='red'>*</font></td>
		             <td>
		             	<input type="text"  name="groupgoods.start_date" value="<#if groupgoods!=null>${groupgoods.start_date?if_exists}</#if>" class="Wdate upWdate" style="width:162px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'updown2\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
		             	<@s.fielderror><@s.param>groupgoods.start_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">结束时间<font color='red'>*</font></td>
		             <td>
		             	  <input type="text"  name="groupgoods.end_date" value="<#if groupgoods!=null>${groupgoods.end_date?if_exists}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown1\',{M:0,d:0});}',maxDate:'2020-4-3'})"/>
		             	<@s.fielderror><@s.param>groupgoods.end_date</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">团购图片<font color='red'>*</font></td>
		              <td>
			            	<table border="0" cellpadding="0" cellspacing="0">
			             		<tr>
			             			<td style="padding:0px;">
			             			    <div id="fileQueue3"></div>
				              			 <@s.textfield name="groupgoods.img_path" id="uploadresult3" cssStyle="width:300px;"/>
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
			            
				          <@s.fielderror><@s.param>groupgoods.img_path</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">详细描述<font color='red'>*</font></td>
		             <td>
		             	       <@s.textarea name="groupgoods.group_desc" id="content" cssClass="txtinput"   />
								<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
								<script type="text/javascript">
							     CKEDITOR.replace( 'content');  
								</script>
		                    	<@s.fielderror><@s.param>groupgoods.group_desc</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		           <tr>
		             <td class="table_name">排序</td>
		             <td>
		             	<@s.textfield name="groupgoods.sort_no" cssClass="txtinput" onkeyup="checkDigital(this);" maxlength="6" value="0"/>
		             	<img class="ltip" src="/include/common/images/light.gif" alt="数字越小越靠前">
		             	<@s.fielderror><@s.param>groupgoods.sort_no</@s.param></@s.fielderror>
		             </td>
		           </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	   <@s.hidden id="goods_id" name="groupgoods.goods_id" />    
       <@s.hidden name="groupgoods.info_state" value="1"/>
       <@s.hidden name="min_sale_price" id="min_sale_price"/>
       <@s.hidden name="total_stock" id="total_stock"/>
       <@s.token/>    
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Groupgoods_list.action','');"/>
       <#include "/WEB-INF/template/manager/searchRadioGoods.ftl"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>
  
  
</body>
</html>

