<html>
  <head>
    <title>添加套餐商品</title>
    <#include "/include/uploadInc.html">
      <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	 <script type="text/javascript">
     $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","combo");
      });
	</script>
  </head>
  <body>
  <@s.form action="/admin_Combo_insert.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:营销推广 > 套餐商品 > 添加套餐商品
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
					             	<input type="button" value="添加相关商品" onclick="addRalateGoods();"/>
					             	<div id="selralategoods" class="selralategoods">
								             		<table width="100%"><tr class="rg_title">
									             		<td class="span_td1">操作</td>
									             		<td class="span_td2">商品名称</td>
								             		</tr></table>
								             		<table id="selulrg" class="selulrg"  width="100%">
								             		
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
		             	<@s.textfield name="combo.combo_price"  cssStyle="width:50px;" onkeyup="checkRMB(this);"maxlength="6"/>元
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
			              			  <@s.textfield name="combo.combo_img" id="uploadresult" cssStyle="width:250px;"/>
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
		             	<@s.textfield name="combo.stock"  onkeyup="checkLength(this,10);" maxlength="10"/>
		             	<@s.fielderror><@s.param>combo.stock</@s.param></@s.fielderror>
		             </td>
		           </tr>
	           
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
	   <@s.token/>   
       <@s.hidden  id="relate_goods"  name="combo.goods_str"/> 
       <@s.hidden value="1"  name="combo.info_state"/>    
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

