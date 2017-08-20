<html>
<head>
<title>订单回收站</title>
<script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script> 
<script language="javascript" type="text/javascript" src="/include/bmember/js/directSell.js"></script> 
<script src="/include/bmember/js/goodsorder.js" type="text/jscript"></script>
</head>
<body>
<@s.form action="/bmall_Goodsorder_reList.action" method="post" id="indexForm">
  <!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>订单回收站</span></h2>
        
        <div class="rTopDiv">
           <span>订单号：<@s.textfield name="order_id_s" cssStyle="width:300px" /></span>
           <span><input type="submit" value="查询" class="cssbtn"/></span>
           
       </div>
        
        <div class="orderDiv">
        
        	<!---->             
             <table width="100%" cellpadding="0" cellspacing="0" class="h2Tab">
               <tr><td width="37%">商品信息</td><td width="12%">收货人</td><td width="12%">实付款(元)</td><td width="12%">交易时间</td><td width="12%">交易状态</td><td width="15%">交易操作</td></tr>
             </table>
            
              <#list goodsorderList as goodsorder>
          <table width="100%" cellpadding="0" cellspacing="0" class="orderTab">
           <tr>
               <th colspan="6">
               	   <span>订单编号：
               	   <a onclick="linkToInfo('/bmall_Goodsorder_buyOrderView.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}&parentMenuId=4637243721&selli=2442547481');" >${(goodsorder.order_id)?if_exists}</a>
               	   </span>
               	     <#if (goodsorder.order_type)?if_exists=='1'>
					 	<font color="#005ea7">普通订单</font>
					<#elseif (goodsorder.order_type)?if_exists=='2'>
						<font color="#005ea7">积分订单</font>
					</#if>
               </th>
           </tr>
           
           <tr>
           		<td width="37%" class="imgTd">
           		<div>
       		    <#assign num=0>
       		    <#assign goods_id_str="">
       		    <#assign goods_name_str="">
       		    <#assign list_img_str="">
       		    <#assign sale_price_str="">
           		 <#if (goodsorder.order_type)?if_exists=='6'>
           		 <#else>
	           		<!-- 普通订单,积分订单、秒杀订单、套餐订单、团购订单、试用订单--> 
					<#list detailList as detail>
						  <#if ((detail.order_id)?if_exists)==((goodsorder.order_id)?if_exists)>
						  	 <#assign num=num+1>   
						  	 <#assign img_path="${( detail.temporary_goodsimg)?if_exists}">   
					          <#if detail.remark!=null>
					                <#assign img_path="${( detail.goods_attr)?if_exists}">
							  </#if>
					         <#if num?if_exists=="1">
					         <#assign goods_id_str="${detail.goods_id?if_exists}">
					         <#assign goods_name_str="${detail.temporary_goodsname?if_exists}">
					         <#assign list_img_str="${detail.temporary_goodsimg?if_exists}">
					         <#assign sale_price_str="${detail.goods_price?if_exists}">
					   		 <#else>
					   		 <#assign goods_id_str="${goods_id_str}#${detail.goods_id?if_exists}">
					   		 <#assign goods_name_str="${goods_name_str}#${detail.temporary_goodsname?if_exists}">
					   		 <#assign list_img_str="${list_img_str}#${detail.temporary_goodsimg?if_exists}">
					   		 <#assign sale_price_str="${sale_price_str}#${detail.goods_price?if_exists}">
					   		 </#if>
					   		 <#assign goodsdetailurl="">
					   		   <!-- 普通订单-->
	                          <#assign goodsdetailurl="/mall-goodsdetail-${detail.goods_id?if_exists}.html">
				      	      <a href="${goodsdetailurl?if_exists}" target="_blank">
				         	<img src="<#if img_path!=''>${img_path?if_exists}<#else>${(cfg_nopic)?if_exists}</#if>" class="f_left" align="absmiddle"></a>
				      		 
		      		 </#if>
		      		 </#list>	
           		 </#if>
	      		 
           		</div></td>
           		<td width="12%">${goodsorder.consignee?if_exists}</td>
           		<td width="12%">	
           		<p> 
           		<#if (goodsorder.order_type)?if_exists=='6'>
           		   <#else>
           			￥<b>${goodsorder.tatal_amount?if_exists}</b> <br/>
           			(<#if goodsorder.ship_free!='0'>含运费${goodsorder.ship_free?if_exists}<#else>免运费</#if>)
           			</#if>
           			</p>
           			
           		</td>
                <td width="12%">
                	<p>${goodsorder.order_time?if_exists[0..10]}</p><p>${goodsorder.order_time?if_exists[10..18]}</p>
                	<p>
           			 <#assign zfpay="">
	                 <#list paymentList as plist>
	                     <#if goodsorder.pay_id==plist.pay_id>
				            	<#assign zfpay=plist.pay_name>
	                     </#if>
	                </#list>
	                <#if (zfpay)?if_exists>
	            	${(zfpay)?if_exists}
	            	<#else>
	            	</#if>
           			</p>
                </td>
                <td width="12%">
                	<p>
                     <#if goodsorder.order_para!=''>
				   		<b>${(goodsorder.order_para)?if_exists}</b>
				    </#if>
                	</p>
                	<#if goodsorder.order_state?if_exists=='3'>
				     <div class="logistics">
                          <i> <a  onclick="linkToInfo('/bmall_Goodsorder_buy_order_Logistics.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}');" >
																    查看物流</a></i>
                       </div>
				     </#if >
				    <a onclick="linkToInfo('/bmall_Goodsorder_buyOrderView.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&order_state=${(goodsorder.order_state)?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}');" >
			      查看</a> 
                </td>
                <td width="15%">
				<a onclick="commonB2cOneInfo('/bmall_Goodsorder_reOrder.action','goodsorder.order_id=${goodsorder.order_id?if_exists}&parentMenuId=${parentMenuId?if_exists}&selli=${selli?if_exists}','确定还原订单？')" >
				还原</a>
                </td>
            </tr>
          </table>
          </#list>
        
        </div>
      
        <!--翻页控件-->
        <div class="listbottom">${pageBar?if_exists}</div>
          
   </div>
   
  </div>

<div class="clear"></div>
<@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>
<@s.hidden name="goods_order_id" id="order_id"/>
</@s.form>

</body>
</html>