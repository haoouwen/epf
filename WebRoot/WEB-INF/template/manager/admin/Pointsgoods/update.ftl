<html>
  <head>
    <title>修改积分商品</title>
    <#include "/include/uploadInc.html">
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","inter");
      });
	</script>
  </head>
  <body>
<@s.form action="/admin_Pointsgoods_update.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:营销推广 > 积分商品 > 修改积分商品
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
			   <tr>
		             <td class="table_name">商品名称<font color='red'>*</font></td>
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
		         	<@s.textfield name="pointsgoods.buy_points" cssClass="txtinput" onkeyup="checkRMB(this);"  maxlength="6"/>
		         	<@s.fielderror><@s.param>pointsgoods.buy_points</@s.param></@s.fielderror>
		         </td>
		       </tr>
		       <!-- 
		       <tr>
		         <td class="table_name">购买金额<font color='red'>*</font></td>
		         <td>
		         	<@s.textfield name="pointsgoods.buy_sum" cssClass="txtinput"  onkeyup="checkRMB(this)"maxlength="6"/>
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
		             			    <div id="fileQueue3"></div>
			              			 <@s.textfield name="pointsgoods.inter_image" id="uploadresult3" cssStyle="width:300px;"/>
		             			</td>
		             			<td style="padding-left:3px;">
		             				<input type="file" name="uploadifyfile" id="uploadifyfile3"/>
		             			</td>
		             			<td style="padding-left:3px;">
		             				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult3');"/>
			              			<script>uploadImg("uploadifyfile3","uploadresult3","fileQueue3");</script>
		             			</td>
		             		</tr>
		             	</table>
		         	<@s.fielderror><@s.param>pointsgoods.inter_image</@s.param></@s.fielderror>
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
		         <td class="table_name">排序:</td>
		         <td>
		         	<@s.textfield name="pointsgoods.sort_no" cssClass="txtinput" onkeyup="checkDigital(this);"  maxlength="6"/>
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
		       
		     <tr>
		     <td class="table_name">推荐:</td>
		     <td>
		     <@s.radio name="pointsgoods.lab" list=r"#{0:'普通','1':'推荐','2':'热门','3':'品牌'}"/>
		     <@s.fielderror><@s.param>pointsgoods.lab</@s.param></@s.fielderror>
		     </td>
		   </tr>
		   
		    <tr>
		     <td class="table_name">审核状态:</td>
		     <td>
		     <#list infoStateList as infoState>
				<#if infoState.para_value?if_exists='1'>
				   <input type="radio" name="pointsgoods.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)"  <#if pointsgoods.info_state='1'>checked</#if>>${infoState.para_key?if_exists}
			    <#elseif infoState.para_value?if_exists='3'>
			       <input type="radio" name="pointsgoods.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)"  <#if pointsgoods.info_state='3'>checked</#if>>${infoState.para_key?if_exists}
			    </#if>
			 </#list>
		     <@s.fielderror><@s.param>pointsgoods.info_state</@s.param></@s.fielderror>
		          <img class="ltip" src="/include/common/images/light.gif" alt="若禁用请点击禁用选项填写理由">
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
	  <@s.hidden name="is_update" />
      <@s.hidden id="goods_id" name="pointsgoods.goods_id"/>    
      <@s.hidden name="pointsgoods.trade_id" />
      <@s.hidden name="pointsgoods.cust_id" />    
      <@s.hidden name="pointsgoods.user_id" />    
      <@s.token/>    
      ${listSearchHiddenField?if_exists}
      <@s.submit value="保存" />
      <@s.reset value="重置"/>
      <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Pointsgoods_list.action','');"/>
      <#include "/WEB-INF/template/manager/searchRadioGoods.ftl"/>
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

