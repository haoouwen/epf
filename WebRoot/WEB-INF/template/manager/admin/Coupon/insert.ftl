<html>
  <head>
    <title>添加优惠券</title>
    <!--日期-->
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>
    <script type="text/javascript" src="/include/admin/js/coupon.js"></script> 
    <#include "/WEB-INF/template/manager/searchCheckboxsaleGoods.ftl"/>
    <script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
    <script type="text/javascript">
	  $(document).ready(function(){ 
		 loadCat("${catIdStr?if_exists}","","","goods");
      });
	</script>
  </head>
  <body>  
<@s.form action="/admin_Coupon_insert.action" method="post" validate="true"  id="detailForm">
<div class="postion">
	当前位置：营销推广>优惠券管理 > 添加优惠券
</div>
<div class="rtdcont">
	<div class="rdtable">
	    <h2>基本信息</h2>
		<table  width="100%" cellspacing="0" cellpadding="0">
		
			 <tr>
	           <td class="table_name"><font color='red'>*</font>名称</td>
	           <td class="table_right">
	             	<@s.textfield name="coupon.coupon_name" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>coupon.coupon_name</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>号码</td>
	           <td class="table_right">
	             	<@s.textfield name="coupon.coupon_no" cssClass="txtinput" maxLength="20" />
	             	<@s.fielderror><@s.param>coupon.coupon_no</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name">状态:</td>
	           <td class="table_right">
	             	<@s.radio name="coupon.info_state" list=r"#{'1':'启用','0':'禁用'}" value="1"/>
	             	<@s.fielderror><@s.param>coupon.info_state</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	        
			 <tr>
	           <td class="table_name">是否支持前台领取:</td>
	           <td class="table_right">
	             	<@s.radio name="coupon.is_show" list=r"#{'1':'是','0':'否'}" value="1"/>
	             	<@s.fielderror><@s.param>coupon.is_show</@s.param></@s.fielderror>
	           </td>
	        </tr>	        
	        <!--
			 <tr>
	           <td class="table_name">优惠券类型:</td>
	           <td class="table_right">
	             	<input type="radio" name="coupon.coupon_type" value="0" checked>A类优惠券 说明：此类优惠券，顾客只需获得一张，即可在规定的时间内重复使用。</br>
	             	<input type="radio" name="coupon.coupon_type" value="1">B类优惠券 说明：此类优惠券，顾客可一次获得多张，但在规定时间内每张只能使用一次，无法重复使用。 只限会员使用。 
	             	<@s.fielderror><@s.param>coupon.coupon_type</@s.param></@s.fielderror>
	           </td>
	        </tr>
	        -->	
	        
			 <tr>
	           <td class="table_name"><font color='red'>*</font>发布数量</td>
	           <td class="table_right">
	             	<@s.textfield name="coupon.coupon_num" cssClass="txtinput" maxLength="20" value="不限制"/>
	             	<@s.fielderror><@s.param>coupon.coupon_num</@s.param></@s.fielderror>
	           </td>
	        </tr>		        
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>规则描述</td>
	           <td class="table_right">
	             	<@s.textarea name="coupon.content" style="width: 300px; height: 100px;" 
	             	onpropertychange="if(value.length>50) value=value.substr(0,49)" maxLength="50" />
	             	<@s.fielderror><@s.param>coupon.content</@s.param></@s.fielderror>
	             	*(规则描述限制输入50个字)
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>开始时间</td>
	           <td class="table_right">
	             	<input type="text"  name="coupon.start_time" value="<#if coupon!=null>${coupon.start_time?if_exists}</#if>" class="Wdate upWdate" style="width:162px;" id="updown1" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'updown2\',{M:0,d:0})||$dp.$DV(\'2020-4-3\',{M:0,d:0})}'})"/>
	             	<@s.fielderror><@s.param>coupon.start_time</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>结束时间</td>
	           <td class="table_right">
	             	<input type="text"  name="coupon.end_time" value="<#if coupon!=null>${coupon.end_time?if_exists}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown2" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd ',minDate:'#F{$dp.$D(\'updown1\',{M:0,d:0});}',maxDate:'2020-4-3'})"/>
	             	<@s.fielderror><@s.param>coupon.end_time</@s.param></@s.fielderror>
	           </td>
	        </tr>	
	    
			 <tr>
	           <td class="table_name"><font color='red'>*</font>会员级别</td>
	           <td class="table_right">
	             	<#list commparaList as commpara>
	             	  <input type="checkbox" name="coupon.member_level" value="${commpara.level_code}" <#if coupon != null && coupon.member_level?if_exists?index_of('${commpara.level_code?trim}') gt -1>checked</#if>/>${commpara.level_name}
	             	</#list>
	             	<@s.fielderror><@s.param>coupon.member_level</@s.param></@s.fielderror>
	           </td>
	        </tr>	

        </table>
        
        </br> 
 	    <h2>优惠条件</h2>
		<table  width="100%" cellspacing="0" cellpadding="0">       
			 <tr>
	           <td class="table_right">
	             	  <input type="radio" name="coupon.need_state" value="1" onclick="selectRadio('coupon.need_state','need')" <#if coupon != null && coupon.need_state?if_exists=='1'>checked</#if>>指定的商品总价满X,对商品优惠</br>
	             	  <div class="coupon" id="need1">
	             	  商品金额满<input  type="text" style="width:50px;heigth:24px;" id="need_money1"  onkeyup="checkNum(this)" <#if coupon != null && coupon.need_state?if_exists=='1'>value="${coupon.need_money?if_exists}"</#if>/>
	             	  <input type="button" value="添加商品" onclick="addRalateGoods('searchgoods','0',this);"/>
 	  					        <div id="selralategoods" class="selralategoods">
				             		<table width="100%">
					             		<tr class="rg_title">
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
	             	  <input type="radio" name="coupon.need_state" value="2" onclick="selectRadio('coupon.need_state','need')" <#if coupon != null && coupon.need_state?if_exists=='2'>checked</#if>>指定的分类商品总价满X,对商品优惠</br>
	             	  <div class="coupon" id="need2">
	             	  <div id="catDiv" style="margin-left:0px;"></div>
	             	   商品金额满<input  type="text" style="width:50px;heigth:24px;" id="need_money2" onkeyup="checkNum(this)" <#if coupon != null && coupon.need_state?if_exists=='2'>value="${coupon.need_money}"</#if>/>
	             	  </div>
	             	  <input type="radio" name="coupon.need_state" value="3" onclick="selectRadio('coupon.need_state','need')" <#if coupon != null && coupon.need_state?if_exists=='3'>checked</#if>>对所有商品总价满X,对商品优惠</br>             	  
	             	  <div class="coupon" id="need3">商品金额满<input  type="text" style="width:50px;heigth:24px;" id="need_money3"   onkeyup="checkNum(this)" <#if coupon != null && coupon != null && coupon.need_state?if_exists=='3'>value="${coupon.need_money?if_exists}"</#if>/></div>
	             	   -->
	             	<@s.fielderror><@s.param>coupon.need_state</@s.param></@s.fielderror>
	           </td>
	        </tr>
	    </table> 
	    
	    </br>  
	    <h2>优惠方案</h2>
		<table  width="100%" cellspacing="0" cellpadding="0">	        
			 <tr>
	           <td class="table_right">
	             	   <!--
	             	  <input type="radio" name="coupon.coupon_state" value="1" onclick="selectRadio('coupon.coupon_state','coupon')" <#if coupon != null && coupon.coupon_state?if_exists=='1'>checked</#if>>商品以固定折扣出售</br>
	             	  <div class="coupon" id="coupon1">商品总价格乘以<input  type="text" style="heigth:24px;" id="coupon_money1" onkeyup="checkNum(this)" <#if coupon != null && coupon.coupon_state?if_exists=='1'>value="${coupon.coupon_money}"</#if>/>%折扣出售</div>
	             	  <input type="radio" name="coupon.coupon_state" value="2" onclick="selectRadio('coupon.coupon_state','coupon')" <#if coupon != null && coupon.coupon_state?if_exists=='2'>checked</#if>>商品固定价格购买</br>
	             	  <div class="coupon" id="coupon2">商品总价格以<input  type="text" style="heigth:24px;" id="coupon_money2" onkeyup="checkNum(this)" <#if coupon != null && coupon.coupon_state?if_exists=='2'>value="${coupon.coupon_money}"</#if>/>出售</div>
	             	  <input type="radio" name="coupon.coupon_state" value="3" onclick="selectRadio('coupon.coupon_state','coupon')" <#if coupon != null && coupon.coupon_state?if_exists=='3'>checked</#if>>商品减去固定折扣出售</br>
	             	  <div class="coupon" id="coupon3">商品总价格减去<input  type="text" style="heigth:24px;" id="coupon_money3" onkeyup="checkNum(this)" <#if coupon != null && coupon.coupon_state?if_exists=='3'>value="${coupon.coupon_money}"</#if>/>%折扣出售</div>
	             	  -->
	             	  <input type="radio" name="coupon.coupon_state" value="4" onclick="selectRadio('coupon.coupon_state','coupon')" <#if coupon != null && coupon.coupon_state?if_exists=='4'>checked</#if>>商品减固定价格购买</br>
	             	  <div class="coupon" id="coupon4">商品总价格优惠<input  type="text" style="heigth:24px;" id="coupon_money4" onkeyup="checkNum(this)" <#if coupon != null && coupon.coupon_state?if_exists=='4'>value="${coupon.coupon_money}"</#if>/>出售</div>
	             	  <@s.fielderror><@s.param>coupon.coupon_state</@s.param></@s.fielderror>
	           </td>
	        </tr>        
       </table> 
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
           <@s.hidden name="token_value" value="${get_token_value?if_exists}"/>
           <@s.token/> 
           <@s.hidden name="coupon.coupon_money" id="coupon_money"/>
           <@s.hidden name="coupon.term" id="relate_goods"/>
           <@s.hidden name="coupon.need_money" id="need_money"/>
           <@s.hidden  id="type"/>
           <@s.hidden name="coupon.coupon_type" value="0"/>
	       ${listSearchHiddenField?if_exists}
           <input type="button" onclick="checkForm();" value="提交"/>
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Coupon_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>  
</body>
</html>

