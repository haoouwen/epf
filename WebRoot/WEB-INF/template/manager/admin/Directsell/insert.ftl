<html>
  <head>
    <title>添加预售商品</title>
     <script type="text/javascript" src="/include/admin/js/directsell.js"></script>
    <!--日期-->
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	 <!--图片-->
    <#include "/include/uploadInc.html">
    <#include "/WEB-INF/template/manager/searchRadioDirectGoods.ftl"/>    
	<!--所属分类开始-->
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	    <script type="text/javascript">
	  $(document).ready(function(){ 
	  	isLimit();
		 loadCat("${catIdStr?if_exists}","","","direct");
		 
      });
	</script>
	<!--所属分类结束-->
  </head>
  <body>
  <@s.form action="/admin_Directsell_insert.action"  method="post"  validate="true"  id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:营销推广 > 预售商品 > 添加预售商品
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 	        <tr>
		             <td class="table_name"></td>
		             <td colspan="3">
		                 <font color='red'>*系统提示：当商品处于预售中，以下信息不能随意修改（商品信息，定金，预售价格，预售开始和结束时间，实际库存，预售数量），请仔细填写</font>
		             </td>
		           </tr>
					<tr>
		             <td class="table_name">预售标题<font color='red'>*</font></td>
		             <td colspan="3">
		             	<@s.textfield name="directsell.sale_title" cssClass="txtinput" onkeyup="checkLength(this,150);" id="directsell" onblur="filterWord('directsell');" maxlength="150" cssStyle="width:500px;height:22px;font-size:14px;"/>
		             	<@s.fielderror><@s.param>directsell.sale_title</@s.param></@s.fielderror>
		             </td>
		           </tr>
					
		            <tr>
	                  <td class="table_name">商品信息<font color='red'>*</font></td>
		             <td colspan="3">
		             	<span id="goodsname">${hidden_goodsname?if_exists}</span>
		             	<span id="goodssaleprice">${hidden_goodssaleprice?if_exists}</span>
		             	<span id="goodsmarketprice">${hidden_goodsmarketprice?if_exists}</span>
			             	<@s.hidden id="hidden_goodsname" name="hidden_goodsname"/>
			             	<@s.hidden id="hidden_goodssaleprice" name="hidden_goodssaleprice"/>
			             	<@s.hidden id="hidden_goodsmarketprice" name="hidden_goodsmarketprice"/>
			             	<a onclick="addRalateGoods();"><input type="button" value="添加商品" /></a>
			             	<@s.fielderror><@s.param>directsell.goods_id</@s.param></@s.fielderror>
			             	&nbsp;&nbsp;说明：预售商品必须是仓库中的并且没有参加其他活动的商品
	                </td>
		           </tr>
					
					 <tr>
		             <td class="table_name">所属分类<font color='red'>*</font></td>
		               <td colspan="3">
		                 <table cellspacing="0" cellpadding="0" style=“border:0px;”>
			             		<tr>
			             			<td>
			             				<div id="catDiv" style="margin-left:0px;"></div>
			             			</td>
			             			<td>
			             				<@s.fielderror><@s.param>directsell.cat_attr</@s.param></@s.fielderror>
			             				 &nbsp;&nbsp;说明：预售分类暂不用于前台搜索等，仅用于后台列表页查询
				              		</td>
				              	</tr>
				         </table>
				        
		             </td>
		           </tr>
					
					<tr>
		             <td class="table_name">定金<font color='red'>*</font></td>
		             <td colspan="3">
		             	￥<@s.textfield name="directsell.earnest" cssClass="txtinput" onkeyup="checkLength(this,25000);" maxlength="6"onkeyup="checkRMB(this)" cssStyle="width:90px;"/>
		             	<@s.fielderror><@s.param>directsell.earnest</@s.param></@s.fielderror>说明：定金只能小于或者等于预售价格（如果定金等于预售价格，完成定金支付后直接等待发货）
		             </td>
		           </tr>
	           
		          <tr>
		              <td class="table_name">预售价格<font color='red'>*</font></td>
		              <td colspan="3">
		              
		    				 <input type="hidden" name="lownums" id="lownums" value="1"/>
		    				￥<input type="text" name="ladprices" id="ladprice" style="width:142px;"  onkeyup="checkRMB(this)" maxlength="6" value="${ladprices?if_exists}"/>
		    				 <@s.fielderror><@s.param>lownums</@s.param></@s.fielderror> 
		    				 <@s.fielderror><@s.param>ladprices</@s.param></@s.fielderror>
		    				 说明：预售价格不能大于商品市场价
						</td>
		           </tr>
					
					<tr>
			             <td class="table_name">预售图片<font color='red'>*</font></td>
			              <td colspan="3">
			              
			                 <table border="0" cellpadding="0" cellspacing="0">
				         		<tr>
				         			<td style="padding:0px;">
				         			    <div id="fileQueue"></div>
				              			  <@s.textfield name="directsell.img_path" id="uploadresult" maxLength="100" cssStyle="width:300px;"/>
				         			</td>
				         			<td style="padding-left:3px;">
				         				<input type="file" name="uploadifyfile" id="uploadifyfile"/>
				         			</td>
				         			<td style="padding-left:3px;">
				         				<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
				              			<script>uploadOneImg();</script>
				         			</td>
				         			
				         		</tr>
				         	</table>
			            
				          <@s.fielderror><@s.param>directsell.img_path</@s.param></@s.fielderror>
		             </td>
		      </tr>

		           <tr>
			             <td class="table_name">预售开始时间<font color='red'>*</font></td>
			             <td>
			             	<input type="text"  name="directsell.start_time" value="<#if directsell!=null>${directsell.start_time?if_exists}</#if>" class="Wdate upWdate" style="width:162px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'updown2\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
			             	<@s.fielderror><@s.param>directsell.start_time</@s.param></@s.fielderror>
			             </td>
			             <td class="table_name">预售结束时间<font color='red'>*</font></td>
			             <td colspan="3">
			             	  <input type="text"  name="directsell.end_time" value="<#if directsell!=null>${directsell.end_time?if_exists}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown1\',{M:0,d:0});}',maxDate:'2020-4-3'})"/>
			             	  <@s.fielderror><@s.param>directsell.end_time</@s.param></@s.fielderror>
			             </td>
			           </tr>

			      
			      <tr>
			             <td class="table_name">开始支付尾款时间<font color='red'>*</font></td>
			             <td colspan="3">
			             	  <input type="text"  name="directsell.tail_time" value="<#if directsell!=null>${directsell.tail_time?if_exists}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown3" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown2\',{M:0,d:1});}',maxDate:'2020-4-3'})"/>
			             	  说明：预售尾款结束时间为（设置的开始支付尾款时间加上${cfg_yushouendpaytime?if_exists}天）
			             	  <@s.fielderror><@s.param>directsell.tail_time</@s.param></@s.fielderror>
			             </td>
			             
			      </tr>
                  
                  <tr>
                   <td class="table_name" style="width:20%;">预计发货时间<font color='red'>*</font></td>
			       <td colspan="3">
		             	<input type="text" name="directsell.deliver_time" value="<#if directsell!=null>${directsell.tail_time?if_exists}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown4" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown3\',{M:0,d:3});}',maxDate:'2020-4-3'})"/>
		             	<@s.fielderror><@s.param>directsell.deliver_time</@s.param></@s.fielderror>
			       </td>
                  </tr>
                  
			      </tr>
					
					
		           <tr>
		             <td class="table_name">实际库存<font color='red'>*</font></td>
		             <td colspan="3">
		             	<@s.textfield name="directsell.stock" cssClass="txtinput" onkeyup="checkDigital(this);" onkeyup="checkDigital(this);" maxlength="6"cssStyle="width:60px;"/>
		             	 &nbsp;&nbsp;说明:实际库存是指买家支付完尾款之后减掉
		             	<@s.fielderror><@s.param>directsell.stock</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		           <tr>
		             <td class="table_name">预售数量<font color='red'>*</font></td>
		             <td colspan="3">
		             	<@s.textfield name="directsell.max_buy" cssClass="txtinput" onkeyup="checkDigital(this);" onkeyup="checkDigital(this);" maxlength="6"cssStyle="width:60px;"/>
		             	&nbsp;&nbsp;说明:预定数量指可供买家支付定金的商品数量（预售数量不能大于实际库存）
		             	<@s.fielderror><@s.param>directsell.max_buy</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		           	<tr>
				             <td class="table_name">是否限购:</td>
				             <td colspan="2">
				             	<@s.radio name="directsell.is_limit" list=r"#{'0':'是','1':'否'}" value="1" onclick="isLimit();"/>
				             	<@s.fielderror><@s.param>directsell.is_limit</@s.param></@s.fielderror>
				             	<img class="ltip" src="/include/common/images/light.gif" alt="选择限购，请填写限购数量">
				             	<span id="is_limit">
				             	限购数量:
				             	<@s.textfield   name="directsell.limit_num"  cssClass="input" onkeyup="checkDigital(this);"  maxlength="6"/>
			             		<@s.fielderror><@s.param>directsell.limit_num</@s.param></@s.fielderror>
				             	</span>
				             </td>
		           </tr> 
		           
		           <tr>
		             <td class="table_name">排序<font color='red'>*</font></td>
		             <td colspan="3">
		             	<@s.textfield name="directsell.sort" cssClass="txtinput" onkeyup="checkDigital(this);" value="0" onkeyup="checkDigital(this);" maxlength="6"cssStyle="width:50px;"/>
		             	<img class="ltip" src="/include/common/images/light.gif" alt="数字越小越靠前">
		             	<@s.fielderror><@s.param>directsell.sort</@s.param></@s.fielderror>
		             </td>
		           </tr>
	               <tr>
			             <td class="table_name">详细描述<font color='red'>*</font></td>
			             <td colspan="3">
			             	       <@s.textarea name="directsell.saledesc" id="content" cssClass="txtinput"   />
									<script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
									<script type="text/javascript">
								     CKEDITOR.replace( 'content');  
									</script>
			                    	<@s.fielderror><@s.param>directsell.saledesc</@s.param></@s.fielderror>
			             </td>
			      </tr>
		</table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
       <@s.hidden id="goods_id" name="directsell.goods_id" />    
       <@s.hidden name="directsell.info_state" value="1"/>
       <@s.hidden name="addorupdate" value="1"/>
       <@s.token/>    
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Directsell_list.action','');"/>
       
   </div>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>

