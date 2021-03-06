<html>
  <head>
    <title>修改订单促销</title>
    <!--日期-->
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/admin/js/saleorder.js"></script> 
    <#include "/WEB-INF/template/manager/searchCheckboxsaleGoods.ftl"/>
	<script type="text/javascript" src="/include/common/js/searchCheckboxcoupon.js"></script> 
    <script type="text/javascript" src="/include/common/js/searchCheckboxredpacket.js"></script>
    <script type="text/javascript" src="/include/common/js/searchCheckboxfreegoods.js"></script> 	
	<!--所属分类结束-->  
  </head>
  <body>  
<@s.form action="/admin_Saleorder_update.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：营销推广 > 促销管理 > 修改订单促销
</div>
<div class="rtdcont">
	<div class="rdtable">
	    <h2>基本信息</h2>
		<table  width="100%" cellspacing="0" cellpadding="0">
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>规则名称</td>
	           <td class="table_right">
	             	<@s.textfield name="saleorder.sale_name" cssClass="txtinput" maxLength="50" />
	             	<@s.fielderror><@s.param>saleorder.sale_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>规则描述</td>
	           <td class="table_right">
	             	<@s.textarea name="saleorder.content" style="width: 300px; height: 100px;" maxLength="50" onpropertychange="if(value.length>50) value=value.substr(0,49)" />
	             	<@s.fielderror><@s.param>saleorder.content</@s.param></@s.fielderror>
	             	*(规则描述限制输入50个字)
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">状态:</td>
	           <td class="table_right">
	                <@s.radio name="saleorder.info_state" list=r"#{'1':'启用','0':'禁用'}" />
	             	<@s.fielderror><@s.param>saleorder.info_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">优先级:</td>
	           <td class="table_right">
	             	<@s.textfield name="saleorder.priority" cssClass="txtinput" cssStyle="width:40px;" maxLength="20"   onkeyup="checkNum(this)"/>
	             	<span><font color="red">温馨提示：数字越小优先级越高</span>
	             	<@s.fielderror><@s.param>saleorder.priority</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>生效平台</td>
	           <td class="table_right">
	             	<input type="checkbox" name="saleorder.platform" value="0"<#if saleorder.platform?if_exists?trim?index_of('0') gt -1>checked</#if>/>PC端
	             	<input type="checkbox" name="saleorder.platform" value="1"<#if saleorder.platform?if_exists?trim?index_of('1') gt -1>checked</#if>/>触屏端
	             	<@s.fielderror><@s.param>saleorder.platform</@s.param></@s.fielderror>
	           </td>
	        </tr>	    
	    
			 <tr>
	           <td class="table_name">是否排它:</td>
	           <td class="table_right">
	                <@s.radio name="saleorder.is_recome" list=r"#{'1':'是','0':'否'}" /></br>
	             	<@s.fielderror><@s.param>saleorder.is_recome</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>开始时间</td>
	           <td class="table_right">
	             	<input type="text"  name="saleorder.start_time" value="<#if saleorder!=null>${saleorder.start_time?if_exists}</#if>" class="Wdate upWdate" style="width:162px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00 ',maxDate:'#F{$dp.$D(\'updown2\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
	             	<@s.fielderror><@s.param>saleorder.start_time</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>结束时间</td>
	           <td class="table_right">
	             	<input type="text"  name="saleorder.end_time" value="<#if saleorder!=null>${saleorder.end_time?if_exists}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00 ',minDate:'#F{$dp.$D(\'updown1\',{M:0,d:0});}',maxDate:'2020-4-3'})"/>
	             	<@s.fielderror><@s.param>saleorder.end_time</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>会员级别</td>
	           <td class="table_right">
	             	<#list commparaList as commpara>
	             	  <input type="checkbox" name="saleorder.member_level" value="${commpara.level_code}"<#if saleorder.member_level?if_exists?index_of('${commpara.level_code?trim}') gt -1>checked</#if>/>${commpara.level_name}
	             	</#list>
	             	<@s.fielderror><@s.param>saleorder.member_level</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
        </table>
        </br>
	    <h2>优惠条件</h2>
		<table  width="100%" cellspacing="0" cellpadding="0">
			 <tr>
	           <td class="table_right">
	             	  <input type="radio" name="saleorder.term_state" value="1" onclick="selectRadio('saleorder.term_state','term');clearCoupon();hideCoupon();" <#if saleorder.term_state?if_exists=='1'>checked</#if>>当订单总价满X时,给予优惠</br>
	             	  <div class="coupon" id="term1">订单金额满<input  type="text" style="width:50px;heigth:24px;" id="need_money1" onkeyup="checkNum(this)" <#if saleorder.term_state?if_exists=='1'>value="${saleorder.need_money?if_exists}"</#if>/></div>
	             	  <input type="radio" name="saleorder.term_state" value="2" onclick="selectRadio('saleorder.term_state','term');clearCoupon();showCoupon();" <#if saleorder.term_state?if_exists=='2'>checked</#if>>对所有订单给予优惠</br>
	             	  <div class="coupon" id="term2">对所有订单给予优惠</div>
                      <input type="radio" name="saleorder.term_state" value="3" onclick="selectRadio('saleorder.term_state','term');clearCoupon();showCoupon();" <#if saleorder!=null && saleorder.term_state?if_exists=='3'>checked</#if>>当指定所有商品总价满X时,给予优惠</br>
	             	 <div class="coupon" id="term3">
	             	 	      <input type="button" value="添加商品" onclick="addRalateGoods('searchgoods','0',this);"/>
	             	 	       商品金额满<input  type="text" style="width:50px;heigth:24px;" id="need_money2" onkeyup="checkNum(this)" <#if saleorder!=null && saleorder.term_state?if_exists=='3'>value="${saleorder.need_money?if_exists}"</#if>/>
 	  					        <div id="selralategoods" class="selralategoods">
				             		<table width="100%"><tr class="rg_title">
					             		<tr>
						             		<td class="span_td2" width="40%">商品名称</td>
						             		<td class="span_td2" width="30%">所属分类</td>
						             		<td class="span_td2" width="10%">销售价</td>
						             		<td class="span_td2" width="10%">库存数量</td>
						             		<td class="span_td1" width="10%">操作</td>
					             		</tr>
				             		</table>
				             		<table id="selulrg" class="selulrg"  width="100%">
	             							<#list ralateList as rlt>
						             			<tr id="ralate_li${rlt.goods_id}">
													<td class='span_td4' width='40%'>${(rlt.goods_name)?if_exists}</td>
													<td class='span_td4' width='30%'>${(rlt.cat_attr)?if_exists}</td>
													<td class='span_td4' width='10%'>${(rlt.min_sale_price)?if_exists}</td>
													<td class='span_td4' width='10%'>${(rlt.total_stock)?if_exists}</td>
													<td class='span_td3' width='10%'><img  onclick="del_ralate('${(rlt.goods_id)?if_exists}');" src='/include/common/images/delete.png' >
													<input type='hidden' class="al_goods_id"  value="${(rlt.goods_id)?if_exists}"/></td>
												 </tr>
											 </#list>				             		
				             		</table>
				             	</div>
	             	 </div>
	             	 <!--
	             	 <input type="radio" name="saleorder.term_state" value="4" onclick="selectRadio('saleorder.term_state','term');clearCoupon();" <#if saleorder!=null && saleorder.term_state?if_exists=='4'>checked</#if>>当所有商品总价满X时,给予优惠</br>
	             	  <div class="coupon" id="term4">商品金额满<input  type="text" style="width:50px;heigth:24px;" id="need_money3" onkeyup="checkNum(this)" <#if saleorder!=null && saleorder.term_state?if_exists=='4'>value="${saleorder.need_money?if_exists}"</#if>/></div>
	             	  -->		             	  	           
	             	<@s.fielderror><@s.param>saleorder.term_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
        </table> 
        </br> 
	    <h2>优惠方案</h2>
		<table  width="100%" cellspacing="0" cellpadding="0">
			 <tr>
	           <td class="table_right">
	             	  <input type="radio" name="saleorder.coupon_state" value="1" onclick="selectRadio('saleorder.coupon_state','coupon')" <#if saleorder.coupon_state?if_exists=='1'>checked</#if>>订单送优惠券</br>
	             	  <div class="coupon" id="coupon1">
	             	  <input type="button" value="添加优惠券" onclick="addCoupon();"/>
 	  					        <div id="selralategoods" class="selralategoods">
				             		<table width="100%"><tr class="rg_title">
					             		<td class="span_td1">操作</td>
					             		<td class="span_td2">优惠券名称</td>
				             		</tr></table>
				             		<table id="selulcp" class="selulrg"  width="100%">
				             				<#list couponList as rlt>
							             			<tr id="coupon_li${rlt.coupon_id}">
														<td class="span_td1">
														<img src="/include/common/images/delete.png" onclick="del_coupon(${rlt.coupon_id});"title='删除'>
														<input class="al_coupon_id" type="hidden" value="${rlt.coupon_id}">
														</td>
														<td class="span_td2">${rlt.coupon_name}</td>
													 </tr>
											</#list>				             		
				             		</table>
				             	</div>	             	  
	             	  </div>
	             	  <input type="radio" name="saleorder.coupon_state" value="2" onclick="selectRadio('saleorder.coupon_state','coupon')" <#if saleorder.coupon_state?if_exists=='2'>checked</#if>>订单送红包</br>
                      <div class="coupon" id="coupon2">
	             	  	        <input type="button" value="添加红包" onclick="addRedpacket();"/>
 	  					        <div id="selralategoods" class="selralategoods">
				             		<table width="100%"><tr class="rg_title">
					             		<td class="span_td1">操作</td>
					             		<td class="span_td2">红包名称</td>
				             		</tr></table>
				             		<table id="selulrp" class="selulrg"  width="100%">
				             				<#list redpacketList as rlt>
							             			<tr id="coupon_li${rlt.red_id}">
														<td class="span_td1">
														<img src="/include/common/images/delete.png" onclick="del_redpacket(${rlt.red_id});"title='删除'>
														<input class="al_red_id" type="hidden" value="${rlt.red_id}">
														</td>
														<td class="span_td2">${rlt.red_name}</td>
													 </tr>
											</#list>					             		
				             		</table>
				             	</div>	  
	             	  </div>
	             	  <input type="radio" name="saleorder.coupon_state" value="4" onclick="selectRadio('saleorder.coupon_state','coupon')" <#if saleorder != null && saleorder.coupon_state?if_exists=='4'>checked</#if>>订单送赠品</br>
	             	  <div class="coupon" id="coupon4">
	             	  	        <input type="button" value="添加赠品" onclick="addFreeGoods();"/>
 	  					        <div id="selralategoods" class="selralategoods">
				             		<table width="100%"><tr class="rg_title">
						             		<td class="span_td2" width="40%">商品名称</td>
						             		<td class="span_td2" width="30%">所属分类</td>
						             		<td class="span_td2" width="10%">销售价</td>
						             		<td class="span_td2" width="10%">库存数量</td>
						             		<td class="span_td1" width="10%">操作</td>
				             		</tr></table>
				             		<table id="selulfg" class="selulrg"  width="100%">
				             				<#list freegoodsList as rlt>
						             			<tr id="fg_li${rlt.fg_id}">
													<td class='span_td4' width='40%'>${(rlt.fg_name)?if_exists}</td>
													<td class='span_td4' width='30%'>${(rlt.cat_attr)?if_exists}</td>
													<td class='span_td4' width='10%'>${(rlt.fg_price)?if_exists}</td>
													<td class='span_td4' width='10%'>${(rlt.fg_number)?if_exists}</td>
													<td class='span_td3' width='10%'><img  onclick="del_fg('${(rlt.fg_id)?if_exists}');" src='/include/common/images/delete.png' >
													<input type='hidden' class="al_fg_id_id"  value="${(rlt.fg_id)?if_exists}"/></td>
												 </tr>
											</#list>					             		
				             		</table>
				             	</div>	  
	             	  </div>	             	  
	             	  <input type="radio" name="saleorder.coupon_state" value="3" onclick="selectRadio('saleorder.coupon_state','coupon')" <#if saleorder.coupon_state?if_exists=='3'>checked</#if>>订单免运费</br>		           
	             	  <div id="couponDiv">
	             	  <input type="radio" name="saleorder.coupon_state" value="5" onclick="selectRadio('saleorder.coupon_state','coupon')" <#if saleorder != null && saleorder.coupon_state?if_exists=='5'>checked</#if>>订单减固定价格</br>
	             	  <div class="coupon" id="coupon5">订单价格优惠<input  type="text" style="heigth:24px;" id="coupon_money4" onkeyup="checkNum(this)" <#if saleorder != null && saleorder.coupon_state?if_exists=='5'>value="${saleorder.coupon_plan}"</#if>/></div>
	             	   </div>
	             	  <@s.fielderror><@s.param>saleorder.coupon_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	        
        </table>               
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.hidden name="saleorder.term" id="relate_goods"/>
           <@s.hidden name="saleorder.need_money" id="need_money" />
           <@s.hidden name="saleorder.coupon_plan" id="coupon_plan"/>
           <@s.hidden  id="type"/>
           <@s.hidden name="saleorder.sale_id"/>
           <@s.token/> 
	       ${listSearchHiddenField?if_exists}
           <input type="button" onclick="checkForm();" value="提交"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Saleorder_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

