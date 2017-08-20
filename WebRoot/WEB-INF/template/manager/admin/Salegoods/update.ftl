<html>
  <head>
    <title>修改商品促销</title>
    <!--日期-->
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/admin/js/salegoods.js"></script> 
	<!--所属分类开始-->
	<#include "/WEB-INF/template/manager/searchCheckboxsaleGoods.ftl"/>
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
	<script type="text/javascript" src="/include/common/js/searchCheckboxcoupon.js"></script>
	<script type="text/javascript" src="/include/common/js/searchCheckboxredpacket.js"></script>
	<script type="text/javascript" src="/include/common/js/searchCheckboxfreegoods.js"></script> 	 
	 <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","goods");
		 
      });
	</script>
	<!--所属分类结束-->  
  </head>
  <body>  
<@s.form action="/admin_Salegoods_update.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：营销推广 > 促销管理 > 修改商品促销
</div>
<div class="rtdcont">
	<div class="rdtable">
	    <h2>基本信息</h2>
		<table  width="100%" cellspacing="0" cellpadding="0">
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>规则名称</td>
	           <td class="table_right">
	             	<@s.textfield name="salegoods.sale_name" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>salegoods.sale_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	         <tr>
	           <td class="table_name"><font color='red'>*</font>列表名称</td>
	           <td class="table_right">
	             	<@s.textfield name="salegoods.sale_name_list" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>salegoods.sale_name_list</@s.param></@s.fielderror>
	           </td>
	        </tr>	
			 <tr>
	           <td class="table_name"><font color='red'>*</font>规则描述</td>
	           <td class="table_right">
	             	<@s.textarea name="salegoods.content" style="width: 300px; height: 100px;" maxLength="50" onpropertychange="if(value.length>50) value=value.substr(0,49)" />
	             	<@s.fielderror><@s.param>salegoods.content</@s.param></@s.fielderror>
	             	*(规则描述限制输入50个字)
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">状态:</td>
	           <td class="table_right">
	                <@s.radio name="salegoods.info_state" list=r"#{'1':'启用','0':'禁用'}"/>
	             	<@s.fielderror><@s.param>salegoods.info_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">倒计时开关:</td>
	           <td class="table_right">
	                <@s.radio name="salegoods.limit_time" list=r"#{'1':'是','0':'否'}"/>
	             	<@s.fielderror><@s.param>salegoods.limit_time</@s.param></@s.fielderror>
	           </td>
	        </tr>		    
	    
			 <tr>
	           <td class="table_name">优先级:</td>
	           <td class="table_right">
	             	<@s.textfield name="salegoods.priority" cssClass="txtinput" cssStyle="width:40px;" maxLength="20" onkeyup="checkNum(this)"/>
	             	 <span><font color="red">温馨提示：数字越小优先级越高</font></span>
	             	<@s.fielderror><@s.param>salegoods.priority</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>生效平台</td>
	           <td class="table_right">
	             	<input type="checkbox" name="salegoods.platform" value="0"<#if salegoods.platform?if_exists?trim?index_of('0') gt -1>checked</#if>/>PC端
	             	<input type="checkbox" name="salegoods.platform" value="1"<#if salegoods.platform?if_exists?trim?index_of('1') gt -1>checked</#if>/>触屏端
	             	<@s.fielderror><@s.param>salegoods.platform</@s.param></@s.fielderror>
	           </td>
	        </tr>		    
	    
			 <tr>
	           <td class="table_name">是否排它:</td>
	           <td class="table_right">
	                <@s.radio name="salegoods.is_recome" list=r"#{'1':'是','0':'否'}" /></br>
	             	<@s.fielderror><@s.param>salegoods.is_recome</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>开始时间</td>
	           <td class="table_right">
	             	<input type="text"  name="salegoods.start_time" value="<#if salegoods!=null>${salegoods.start_time?if_exists}</#if>" class="Wdate upWdate" style="width:162px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00 ',maxDate:'#F{$dp.$D(\'updown2\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
	             	<@s.fielderror><@s.param>salegoods.start_time</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>结束时间</td>
	           <td class="table_right">
	             	<input type="text"  name="salegoods.end_time" value="<#if salegoods!=null>${salegoods.end_time?if_exists}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00 ',minDate:'#F{$dp.$D(\'updown1\',{M:0,d:0});}',maxDate:'2020-4-3'})"/>
	             	<@s.fielderror><@s.param>salegoods.end_time</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>会员级别</td>
	           <td class="table_right">
	             	<#list commparaList as commpara>
	             	  <input type="checkbox" name="salegoods.member_level" value="${commpara.level_code}" <#if salegoods.member_level?if_exists?index_of('${commpara.level_code?trim}') gt -1>checked</#if>/>${commpara.level_name}
	             	</#list>
	             	<@s.fielderror><@s.param>salegoods.member_level</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
        </table>
        </br>
	    <h2>优惠条件</h2>
		<table  width="100%" cellspacing="0" cellpadding="0">
			 <tr>
	           <td class="table_right">
	             	  <input type="radio" name="salegoods.term_state" value="1" onclick="selectRadio('salegoods.term_state','term');clearCoupon();" <#if salegoods.term_state?if_exists=='1'>checked</#if>>指定商品</br>
	             	  <div class="coupon" id="term1">
	             	  <input type="button" value="添加商品" onclick="addRalateGoods('searchgoods','0',this);"/>
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
	             	  <input type="radio" name="salegoods.term_state" value="2" onclick="selectRadio('salegoods.term_state','term');clearCoupon();" <#if salegoods.term_state?if_exists=='2'>checked</#if>>商品分类</br>
	             	  <div class="coupon" id="term2">商品分类<div id="catDiv" style="margin-left:0px;"></div></div>
	             	  <input type="radio" name="salegoods.term_state" value="3" onclick="selectRadio('salegoods.term_state','term');clearCoupon();" <#if salegoods.term_state?if_exists=='3'>checked</#if>>所有商品</br>
	             	  <div class="coupon" id="term3">所有商品</div>
	             	  <input type="radio" name="salegoods.term_state" value="4" onclick="selectRadio('salegoods.term_state','term');clearCoupon();" <#if salegoods != null && salegoods.term_state?if_exists=='4'>checked</#if>>指定的商品总价满X,对商品优惠</br>
	             	  <div class="coupon" id="term4">
	             	  <input type="button" value="添加商品" onclick="addRalateGoods('searchgoods','1',this);"/>
	             	   商品金额满<input  type="text" style="width:50px;heigth:24px;" id="need_money1"  name="salegoods.need_money" onkeyup="checkNum(this)" <#if salegoods != null && salegoods.term_state?if_exists=='4'>value="${salegoods.need_money?if_exists}"</#if>/>
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
				             		<table id="selulzg" class="selulrg"  width="100%">
	             							<#list goodsList as rlt>
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
	             	<@s.fielderror><@s.param>salegoods.term_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
        </table> 
        </br> 
	    <h2>优惠方案</h2>
		<table  width="100%" cellspacing="0" cellpadding="0">
			 <tr>
	           <td class="table_right">
	             	  <input type="radio" name="salegoods.coupon_state" value="1" onclick="selectRadio('salegoods.coupon_state','coupon');" <#if salegoods.coupon_state?if_exists=='1'>checked</#if>>符合应用条件的商品送优惠券</br>
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
	             	  <input type="radio" name="salegoods.coupon_state" value="2" onclick="selectRadio('salegoods.coupon_state','coupon')" <#if salegoods.coupon_state?if_exists=='2'>checked</#if>>符合应用条件的商品送红包</br>
	             	  <div class="coupon" id="coupon2">
	             	  	        <input type="button" value="添加红包" onclick="addRedpacket();"/>
 	  					        <div id="selralategoods" class="selralategoods">
				             		<table width="100%"><tr class="rg_title">
					             		<td class="span_td1">操作</td>
					             		<td class="span_td2">红包名称</td>
				             		</tr></table>
				             		<table id="selulrp" class="selulrg"  width="100%">
				             				<#list redpacketList as rlt>
							             			<tr id="redpacket_li${rlt.red_id}">
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
	             	  <input type="radio" name="salegoods.coupon_state" value="7" onclick="selectRadio('salegoods.coupon_state','coupon')" <#if salegoods != null && salegoods.coupon_state?if_exists=='7'>checked</#if>>符合应用条件的商品送赠品</br>
	             	  <div class="coupon" id="coupon7">
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
	             	  <input type="radio" name="salegoods.coupon_state" value="3" onclick="selectRadio('salegoods.coupon_state','coupon')" <#if salegoods.coupon_state?if_exists=='3'>checked</#if>>符合应用条件的商品以固定折扣出售</br>
	             	   <div class="coupon" id="coupon3">商品价格乘以<input  type="text" style="heigth:24px;" id="coupon_money1" onkeyup="checkNum(this)" <#if salegoods.coupon_state?if_exists=='3'>value="${salegoods.coupon_plan}"</#if>/>%折扣出售</div>
	             	  <!--
	             	  <input type="radio" name="salegoods.coupon_state" value="4" onclick="selectRadio('salegoods.coupon_state','coupon')" <#if salegoods.coupon_state?if_exists=='4'>checked</#if>>符合应用条件的商品固定价格购买</br>
	             	  <div class="coupon" id="coupon4">商品价格以<input  type="text" style="heigth:24px;" id="coupon_money2" onkeyup="checkNum(this)" <#if salegoods.coupon_state?if_exists=='4'>value="${salegoods.coupon_plan}"</#if>/>出售</div>
	             	  <input type="radio" name="salegoods.coupon_state" value="5" onclick="selectRadio('salegoods.coupon_state','coupon')" <#if salegoods.coupon_state?if_exists=='5'>checked</#if>>符合应用条件的商品减去固定折扣出售</br>
	             	  <div class="coupon" id="coupon5">商品价格减去<input  type="text" style="heigth:24px;" id="coupon_money3" onkeyup="checkNum(this)" <#if salegoods.coupon_state?if_exists=='5'>value="${salegoods.coupon_plan}"</#if>/>%折扣出售</div>
	             	  -->
	             	  <input type="radio" name="salegoods.coupon_state" value="6" onclick="selectRadio('salegoods.coupon_state','coupon')" <#if salegoods.coupon_state?if_exists=='6'>checked</#if>>符合应用条件的商品减固定价格购买</br>
	             	  <div class="coupon" id="coupon6">商品价格优惠<input  type="text" style="heigth:24px;" id="coupon_money4" onkeyup="checkNum(this)" <#if salegoods.coupon_state?if_exists=='6'>value="${salegoods.coupon_plan}"</#if>/>出售</div>	           
	             	<@s.fielderror><@s.param>salegoods.coupon_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	        
        </table>               
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.hidden name="salegoods.term" id="relate_goods"/>
           <@s.hidden name="salegoods.coupon_plan" id="coupon_plan"/>
           <@s.hidden name="salegoods.sale_id"/>
           <@s.hidden  id="type"/>
           <@s.token/> 
	       ${listSearchHiddenField?if_exists}
           <input type="button" onclick="checkForm();" value="提交"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Salegoods_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

