<html>
  <head>
    <title>添加积分商品</title>
    <#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	 <script type="text/javascript">
     $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","inter");
      });
	</script>
  </head>
  <body>
<@s.form action="/admin_Pointsgoods_insert.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:营销推广 > 积分商品 > 添加积分商品
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
                  <td class="table_name">商品名称<font color='red'>*</font></td>
	             <td>
	             	<span id="goodsname">${hidden_goodsname}</span>
		             	<@s.hidden id="hidden_goodsname" name="hidden_goodsname"/>
		             	<a onclick="addRalateGoods();"><input type="button" value="添加商品" /></a>
		             	<@s.fielderror><@s.param>pointsgoods.goods_id</@s.param></@s.fielderror>
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
	             				<@s.fielderror><@s.param>pointsgoods.cat_attr</@s.param></@s.fielderror>
		              		</td>
		              	</tr>
		         </table>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">购买积分<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="pointsgoods.buy_points" cssClass="txtinput" onkeyup="checkRMB(this);" maxlength="6" />
             	<@s.fielderror><@s.param>pointsgoods.buy_points</@s.param></@s.fielderror>
             </td>
           </tr>
           <!--
           <tr>
             <td class="table_name">购买金额<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="pointsgoods.buy_sum" cssClass="txtinput" onkeyup="checkRMB(this)" maxlength="6"/>
             	<@s.fielderror><@s.param>pointsgoods.buy_sum</@s.param></@s.fielderror>
             </td>
           </tr>
           -->
           <tr>
             <td class="table_name">积分图片<font color='red'>*</font></td>
             <td>
             	<table border="0" cellpadding="0" cellspacing="0">
	             		<tr>
	             			<td style="padding:0px;">
	             			    <div id="fileQueue"></div>
		              			 <@s.textfield name="pointsgoods.inter_image" id="uploadresult" cssStyle="width:300px;"/>
	             			</td>
	             			<td style="padding-left:3px;">
	             				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
	             			</td>
	             			<td style="padding-left:3px;">
	             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
		              			<script>uploadGoodsImageMoreMulti("uploadifyfile","uploadresult","fileQueue");</script>
	             			</td>
	             			<td><@s.fielderror><@s.param>pointsgoods.inter_image</@s.param></@s.fielderror></td>
	             		</tr>
	            </table>
             </td>
           </tr>
           
           <tr>
             <td class="table_name">积分商品描述<font color='red'>*</font></td>
             <td>
                   <@s.textarea name="pointsgoods.description" id="content" cssClass="txtinput"   />
				   <script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
				   <script type="text/javascript">
				    	CKEDITOR.replace( 'content');  
				   </script>
         	       <@s.fielderror><@s.param>pointsgoods.description</@s.param></@s.fielderror>
             </td>
           </tr>
            <tr>
             <td class="table_name">标签:</td>
             <td>
                   <@s.checkboxlist name="pointsgoods.lab" list=r"#{'1':'推荐','2':'热门','3':'品牌'}" />
             </td>
           </tr>
        <tr>
             <td class="table_name">排序:</td>
             <td>
             	<@s.textfield name="pointsgoods.sort_no" cssClass="txtinput" onkeyup="checkDigital(this);" value="0" maxlength="6"/>
             	<@s.fielderror><@s.param>pointsgoods.sort_no</@s.param></@s.fielderror>
             </td>
       </tr>
       
           <tr>
             <td class="table_name">库存<font color='red'>*</font></td>
             <td>
             	<@s.textfield name="pointsgoods.stock" cssClass="txtinput" onkeyup="checkDigital(this);"  maxlength="6"/>
             	<@s.fielderror><@s.param>pointsgoods.stock</@s.param></@s.fielderror>
             </td>
           </tr>
		 
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
     <@s.hidden id="goods_id" name="pointsgoods.goods_id"/>    
     <@s.hidden name="pointsgoods.info_state" value="1"/>
     <@s.token/>    
     ${listSearchHiddenField?if_exists}
     <@s.submit value="保存" />
     <@s.reset value="重置"/>
     <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Pointsgoods_list.action','');"/>
     <#include "/WEB-INF/template/manager/searchPointsGoods.ftl"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

