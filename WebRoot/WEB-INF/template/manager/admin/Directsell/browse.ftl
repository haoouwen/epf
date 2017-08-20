<html>
  <head>
    <title>查看预售商品</title>
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
		 loadCat("${catIdStr?if_exists}","","","direct");
      });
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
		<table  width="100%" cellspacing="0" cellpadding="0" style="TABLE-LAYOUT: fixed;">
		 <!--详细页table的数据-->
		 
		          <tr>
		             <td class="table_name" style="width:15%;">预售标题<font color='red'>*</font></td>
		             <td colspan="3">
		             	${(directsell.sale_title)?if_exists}
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
		                ${preCatAttr?if_exists} &nbsp;&nbsp;说明：预售分类暂不用于前台搜索等，仅用于后台列表页查询  		
		             </td>
		           </tr>
					
					<tr>
		             <td class="table_name">定金<font color='red'>*</font></td>
		             <td colspan="3">
		             	￥${(directsell.earnest)?if_exists}
		            说明：定金只能小于或者等于预售价格（如果定金等于预售价格，完成定金支付后直接等待发货）
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
																		￥${(lab.price)?if_exists}
																	<#assign uptr=uptr+2/>
																</#list>
							</#if>
									 <input type="hidden" name="lownums" id="lownums" value="1"/>
				    				 说明：预售价格不能大于商品市场价 
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
			             <td>
			             	  <input type="text" disabled="disabled"  name="directsell.tail_time" value="<#if directsell!=null>${directsell.tail_time?if_exists}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown3" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown2\',{M:0,d:1});}',maxDate:'2020-4-3'})"/>
			             	<@s.fielderror><@s.param>directsell.tail_time</@s.param></@s.fielderror>
			             </td>
			              <td class="table_name">预计发货时间<font color='red'>*</font></td>
			             <td>
			             	  <input type="text" disabled="disabled"  name="directsell.deliver_time" value="<#if directsell!=null>${directsell.deliver_time?if_exists}</#if>"  class="Wdate downWdate" style="width:162px;" id="updown4" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'updown3\',{M:0,d:3});}',maxDate:'2020-4-3'})"/>
			             	<@s.fielderror><@s.param>directsell.deliver_time</@s.param></@s.fielderror>
			             </td>
			      </tr>

		           <tr>
		             <td class="table_name">实际库存<font color='red'>*</font></td>
		             <td colspan="3">
		             	 ${(directsell.stock)?if_exists}&nbsp;&nbsp;
		             	 (预设的库存数量为：${(directsell.stock_maxbuy)?if_exists})
		             	 &nbsp;&nbsp;说明:实际库存是指买家支付完尾款之后减掉
		             </td>
		           </tr>
		           
		             <tr>
		             <td class="table_name">预定数量<font color='red'>*</font></td>
		             <td colspan="3">
		             	<!--<@s.textfield name="directsell.max_buy" cssClass="txtinput" onkeyup="checkDigital(this);" onkeyup="checkDigital(this);" maxlength="6"cssStyle="width:60px;"/>
		             	 预设的预定数量为：${directsellMaxbuy?if_exists}&nbsp;&nbsp;说明:预定数量指可供买家支付定金的商品数量
		             	<@s.fielderror><@s.param>directsell.max_buy</@s.param></@s.fielderror>-->
		                      <span style='font-size:12pt;color:red;'><#if (directsell.deposit_num)=="" || (directsell.deposit_num)==null>0<#else>${directsell.deposit_num?if_exists}</#if>/${directsell.max_buy?if_exists}</span>&nbsp;&nbsp;(已付定金数/预定最大数量)
		             </td>
		           </tr>
		            <tr>
		             <td class="table_name">排序<font color='red'>*</font></td>
		             <td colspan="3">
		                 ${(directsell.sort)?if_exists}
		             </td>
		           </tr>
		           <tr>
			             <td class="table_name">详细描述<font color='red'>*</font></td>
			             <td colspan="3"  style="word-wrap:break-word;">
			             	  ${(directsell.saledesc)?if_exists}
			             </td>
			      </tr>
		           

	           <tr>
	             <td class="table_name">审核状态<font color='red'>*</font></td>
	             <td colspan="3">
	                <#if directsell.info_state=='1'>
	                     正常
	                <#else>
	                     禁用<br/>
	                     ${(reason)?if_exists}
	                </#if>
	           
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
       <@s.token/>  
       ${listSearchHiddenField?if_exists}
       <#if directsell.is_del=="0">
          <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Directsell_list.action','');"/>
       <#else>
          <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Directsell_prerecyclelist.action','');"/>    
       </#if>    
   </div>
</div>
<div class="clear"></div>

</@s.form>
</body>
</html>

