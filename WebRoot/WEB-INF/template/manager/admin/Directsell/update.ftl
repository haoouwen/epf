<html>
  <head>
    <title>修改预售商品</title>
    <script type="text/javascript" src="/include/admin/js/directsell.js"></script>
    <!--日期-->
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
	 <!--图片-->
  <#include "/include/uploadInc.html">
   <#include "/WEB-INF/template/manager/searchRadioDirectGoods.ftl"/>
    <!--FCK开始-->
    <script type="text/javascript" src="/include/components/ckeditor/ckeditor.js"></script>
	<!--FCK结束-->
	<!--所属分类开始-->
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script> 
	<script type="text/javascript">
	  $(document).ready(function(){ 
	  	isLimit();
		 loadCat("${catIdStr?if_exists}","","","direct");
		 
      });
      function checkS(){
      	var stock_deposit_num =  $("#stock_deposit_num").val();
		    var re =/^(0|([1-9]\d*))$/;
			if (!re.test(stock_deposit_num)){
		    		  jNotify("该文本框只能输入正整数,请正确输入!"); 
	    			 return false;
		    }else{
		    	document.getElementById('form_search_id').submit();	  
		    }
		    
      }
      
	</script>
	<!--所属分类结束-->
  </head>
  <body>
 <@s.form action="/admin_Directsell_update.action" method="post" validate="true" id="modiy_form">
<div class="postion">
  <!--当前位置-->
  当前位置:营销推广 > 预售商品 > 修改预售商品
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据-->
		 
		          <tr>
		             <td class="table_name">预售标题<font color='red'>*</font></td>
		             <td colspan="3">
		             	<@s.textfield name="directsell.sale_title" cssClass="txtinput" id="directsell" onblur="filterWord('directsell');" onkeyup="checkLength(this,150);" maxlength="150" cssStyle="width:500px;height:22px;font-size:14px;"/>
		             	<@s.fielderror><@s.param>directsell.sale_title</@s.param></@s.fielderror>
		             </td>
		           </tr>
					
		            <tr>
	                  <td class="table_name">商品信息<font color='red'>*</font></td>
		             <td colspan="3">
		           
		                 <#if hidden_goodsname!=null && hidden_goodssaleprice!=null>
		                      商品名称：<span id="goodsname">${hidden_goodsname?if_exists}</span>
		             	      &nbsp;&nbsp;商品销售价格：<span id="goodssaleprice">${hidden_goodssaleprice?if_exists}</span><span class="danweiShow" style="display:inline;">元</span>
		             	      &nbsp;&nbsp;商品市场价格：<span id="goodsmarketprice">${hidden_goodsmarketprice?if_exists}</span><span class="danweiShow" style="display:inline;">元</span>&nbsp;&nbsp;
		             	     
		             	<#else>
			       			  该商品已删除
			            </#if>   
		             	<@s.hidden id="hidden_goodsname" name="hidden_goodsname"/>
		             	<@s.hidden id="hidden_goodssaleprice" name="hidden_goodssaleprice"/>
		             	<@s.hidden id="hidden_goodsmarketprice" name="hidden_goodsmarketprice"/>
		             	&nbsp;&nbsp;说明：预售商品必须是仓库中的并且没有参加其他活动的商品
	                </td>
		           </tr>
					
				   <tr>
		             <td class="table_name">所属分类<font color='red'>*</font></td>
		               <td colspan="3">
		                 <table  cellspacing="0" cellpadding="0" style=“border:0px;”>
			             		<tr>
			             			<td class="tdbottom">
			             				<div id="catDiv" style="margin-left:0px;"></div>
			             			</td>
			             			<td class="tdbottom">
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
		             	￥<@s.label name="directsell.earnest"/>&nbsp;&nbsp;说明：定金只能小于或者等于预售价格（如果定金等于预售价格，完成定金支付后直接等待发货）
		             </td>
		           </tr>
	           
		          <tr>
		              <td class="table_name">预售价格<font color='red'>*</font></td>
		              <td colspan="3">
		    				 <#if labList?if_exists?size gt 0 &&labList.lownum!='1'>
										                		<#assign uptr=0/>                                   
																<#list labList as lab>
																		<#assign uptrone=uptr+1/>
																		<#assign uptrtwo=uptr+2/>
																		￥${lab.price?if_exists}
																		   <@s.hidden name="ladprices" value="${lab.price?if_exists}"/>
																	<#assign uptr=uptr+2/>
																</#list>
							</#if>
									 <input type="hidden" name="lownums" id="lownums" value="1"/>
				    				 <@s.fielderror><@s.param>lownums</@s.param></@s.fielderror> 
				    				 <@s.fielderror><@s.param>ladprices</@s.param></@s.fielderror>&nbsp;&nbsp;说明：预售价格不能大于商品市场价 
						</td>
		           </tr>
					
				   <tr>
			             <td class="table_name">预售图片<font color='red'>*</font></td>
			              <td colspan="3">
			            	<img src="${(directsell.img_path)?if_exists}" width="100px" height="100px"/>
		                </td>
		           </tr>

		           <tr>
			             <td class="table_name">预售开始时间<font color='red'>*</font></td>
			             <td>
			             	<input type="text" disabled="disabled"  name="directsell.start_time" value="<#if directsell!=null&&directsell.start_time!=''>${directsell.start_time[0..18]}</#if>" class="Wdate upWdate" style="width:162px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'updown2\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
			             	<@s.fielderror><@s.param>directsell.start_time</@s.param></@s.fielderror>
			             </td>
			              <td class="table_name">预售结束时间<font color='red'>*</font></td>
			             <td>
			             	  <input type="text" disabled="disabled"  name="directsell.end_time"  value="<#if directsell!=null&&directsell.end_time!=''>${directsell.end_time[0..18]}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown1\',{M:0,d:0});}',maxDate:'2020-4-3'})"/>
			                 <@s.fielderror><@s.param>directsell.end_time</@s.param></@s.fielderror>
			             </td>
			           </tr>
			      
			        <tr>
			             <td class="table_name">开始支付尾款时间<font color='red'>*</font></td>
			             <td colspan="3">
			             	  <input type="text"  name="directsell.tail_time" value="<#if directsell!=null>${directsell.tail_time?if_exists[0..18]}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown3" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown2\',{M:0,d:1});}',maxDate:'2020-4-3'})"/>
			             	   尾款支付结束时间为：${directsell.final_time?if_exists[0..18]}（说明：开始支付尾款时间加上${cfg_yushouendpaytime?if_exists}天）
			             	<@s.fielderror><@s.param>directsell.tail_time</@s.param></@s.fielderror>
			             </td>
			      </tr>
			      <tr>
			          <td class="table_name">预计发货时间<font color='red'>*</font></td>
			             <td colspan="3">
			             	  <input type="text"  name="directsell.deliver_time" value="<#if directsell!=null>${directsell.deliver_time?if_exists[0..18]}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown4" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown3\',{M:0,d:3});}',maxDate:'2020-4-3'})"/>
			             	<@s.fielderror><@s.param>directsell.deliver_time</@s.param></@s.fielderror>
			             </td>
			      </tr>
				
				
		           <tr>
		             <td class="table_name">实际库存<font color='red'>*</font></td>
		             <td colspan="3">
		             	<@s.label name="directsell.stock"/>
		             	 (预设的库存数量为：${(directsell.stock_maxbuy)?if_exists})
		             	 &nbsp;&nbsp;说明:实际库存是指买家支付完尾款之后减掉
		             	 <input type="button" id="addstock" name="addstock" value="增加库存" onclick="addShowDIV('searchDiv','300px','220px','1');" class="rbut"/>
		             	 
		             	<@s.fielderror><@s.param>directsell.stock</@s.param></@s.fielderror>
		             </td>
		           </tr>
		           
		             <tr>
		             <td class="table_name">预售数量<font color='red'>*</font></td>
		             <td colspan="3">
		                      <span style='font-size:12pt;color:red;'><#if (directsell.deposit_num)=="" || (directsell.deposit_num)==null>0<#else>${directsell.deposit_num?if_exists}</#if>/${directsell.max_buy?if_exists}</span>&nbsp;&nbsp;(已付定金数/预售最大数量)
		                      <input type="button" id="adddeposit" name="adddeposit" value="增加预售数量" onclick="addShowDIV('searchDiv','300px','220px','2');" class="rbut"/>
		             </td>
		           </tr>
		            <tr>
			             <td class="table_name">发布时间：</td>
			             <td colspan="3">
			             	  <input type="text" disabled="disabled"  name="directsell.in_date" value="<#if directsell!=null>${directsell.in_date?if_exists[0..18]}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown3" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown2\',{M:0,d:1});}',maxDate:'2020-4-3'})"/>
			             </td>
			             
			      </tr>
			      
			      <tr>
										             <td class="table_name">是否限购:</td>
										             <td colspan="2">
										             	<@s.radio name="directsell.is_limit" list=r"#{'0':'是','1':'否'}"  onclick="isLimit();"/>
										             	<@s.fielderror><@s.param>directsell.is_limit</@s.param></@s.fielderror>
										             	<img class="ltip" src="/include/common/images/light.gif" alt="选择限购，请填写限购数量">
										             <span id="is_limit">
										             	限购数量:
										             	<@s.textfield   name="directsell.limit_num" cssClass="input" onkeyup="checkDigital(this);"  maxlength="6"/>
									             		<@s.fielderror><@s.param>directsell.limit_num</@s.param></@s.fielderror>
										             </span>
										             </td>
			           </tr> 
		            <tr>
		             <td class="table_name">排序<font color='red'>*</font></td>
		             <td colspan="3">
		             	<@s.textfield name="directsell.sort" cssClass="txtinput" onkeyup="checkDigital(this);"  onkeyup="checkDigital(this);" maxlength="6"cssStyle="width:50px;"/>
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
		           

	           <tr>
	             <td class="table_name">审核状态:</td>
	             <td colspan="3">
	             <#list infoStateList as infoState>
					<#if infoState.para_value?if_exists='1'>
					   <input type="radio" name="directsell.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)" <#if directsell.info_state='1'>checked</#if>>${infoState.para_key?if_exists}
				    <#elseif infoState.para_value?if_exists='3'>
				       <input type="radio" name="directsell.info_state" value="${infoState.para_value?if_exists}" onclick="checkinput(this)" <#if directsell.info_state='3'>checked</#if>>${infoState.para_key?if_exists}
				    </#if>
    			 </#list>
	             <@s.fielderror><@s.param>directsell.info_state</@s.param></@s.fielderror>
	                  <img class="ltip" src="/include/common/images/light.gif" alt="若禁用请点击禁用选项填写理由">
	           	    <br/>
	             <@s.textarea name="reason"id="reason"  maxlength="100" cssStyle="width:260px;height:100px;display:none;"/>
	             </td>
	           </tr>
	           
		</table>
		  <#include "/WEB-INF/template/manager/admin/Audithistory/auditlist.ftl"/>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
    <!--操作按钮-->
       <@s.hidden name="directsell.goods_id" id="goods_id"/>
       <@s.hidden name="old_goods_id" id="old_goods_id" value="${directsell.goods_id?if_exists}"/>
       <@s.hidden name="directsell.trade_id" />
       <@s.hidden name="is_update" value="1"/>    
       <@s.hidden name="directsell.user_id"/> 
       <@s.hidden name="directsell.cust_id"/>
       <@s.hidden name="addorupdate" value="2"/> 
       <@s.hidden name="addorupdate" value="0"/>
       <@s.token/>  
       ${listSearchHiddenField?if_exists}
       <@s.submit value="保存" />
       <@s.reset value="重置"/>
       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Directsell_list.action','');"/>    
   </div>
</div>
<div class="clear"></div>

</@s.form>

<!--增加库存和预售数量区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Directsell_addnum.action" method="post"  id="form_search_id" >
		<table width="100%" border="0" cellspacing="0">
	 <tr>
			<td class="searchDiv_td">增加<span id="stock_maxbuy_name"></span>数量：</td> 
			<td><@s.textfield id="stock_deposit_num" name="stock_deposit_num"  cssStyle="width:180px;"/></td>
	 </tr>
		<tr>
			<td align="center" colspan="2" style="border:0px;">
			    <@s.hidden name="directsell.trade_id" />
			    <@s.hidden id="s_flag" name="stock_deposit" />
				<input type="button" id="addnum" name="addnum" value="新增" onclick="checkS();" />&nbsp;
			   <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
			</td>
	   </tr>
		</table>
		</@s.form>
	</div>		   
<!--增加库存和预售数量区域结束-->
</body>
</html>