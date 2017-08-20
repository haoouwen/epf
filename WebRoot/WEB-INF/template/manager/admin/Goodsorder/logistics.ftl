<html>
<head>
<title>查看物流</title>
<script type="text/javascript" src="/include/admin/js/goodsorder.js"></script>
<link href="/include/admin/css/logistics.css" rel="stylesheet" type="text/css"/>
   <script type="text/javascript">
	$(document).ready(function(){
	   //加载物流动态信息
	   getLOgisticsInfo();
	});
  </script>
</head>
  <body>
  <@s.form action="/admin_Goodsorder_adminDelivery.action"  method="post" validate="true"  id="tradeForm">
<div class="postion">当前位置:商城管理 > 查看物流</div>
<div class="rtdcont">
	<div class="rttable">
	   <!--列表页table的数据-->	
	   <div class="main_cont">
 		<div class="main_content">
 			 <div class="orderNum">
		           <span>订单号：${(goodsorder.order_id)?if_exists}</span><span>创建时间：${(goodsorder.order_time)?if_exists}</span>
		        </div>
		        
		        <div class="logistics">
		           <span>物流公司：<b class="bred" style="color:#0579C6;">${kuai_company}</b></span>
		           <span>运单号：<b style="color:#1B730C;">${kuai_number}</b></span>
		        </div>
		        
		        <div class="logDynamic">
		           <h2><span>物流动态</span></h2>
		           <div class="logDycon" id="show_log">
		           </div>
		        </div>
		        <div class="prompt">
		          <h2>以上信息数据由 <a href="http://www.kuaidi100.com" target="_blank" style="color:#0579C6;">快递100</a>  提供，您可以查看每条信息及其来源</h2>
		        </div>
		        <!--订单-->
		        <div class="logDynamic">
		          <h2><span>宝贝信息</span></h2>
		          <table width="100%" cellpadding="0" cellspacing="0">
		          
		           <#list orderdetailList as orderlist>
		            <tr>
		              <td width="45%">
		              <#if orderlist.img_path!=''>
				  			<#if ((orderlist.img_path)?index_of(",") > (-1))>      			
				  				<#assign startpos = "${orderlist.img_path?if_exists}"?index_of(',')>
				                <#if ((startpos-1)>-1)>
				                    <#assign img =(orderlist.img_path)[(0)..(startpos-1)]>
				                 </#if>
				             <#else> 
				             		<#assign img =orderlist.img_path>
				             </#if> 
			      			<img src="${(img)?if_exists}" width='50' height='50'>
			      		 <#else>
			      			<img src="${(cfg_nopic)?if_exists}" width='50' height='50'>
			      		 </#if>	
			             <a target='_blank' href="/mall-goodsdetail-${orderlist.goods_id?if_exists}.html">
			      			${(orderlist.goods_name)?if_exists}
			      		</a>
		              </td>
		              <td width="20%">
		              <#if (orderlist.goods_attr)?if_exists>
			      		 <#assign g_attr=orderlist.goods_attr>
			      		  <#if ((g_attr)?index_of("<br>") > (-1))>
			      		       <#assign g_attr =g_attr.replace('<br>','')>
			      		  </#if>
			      		 </#if>
			      		 ${(g_attr)?if_exists}
		              </td>
		              <td width="15%">${(orderlist.goods_price)?if_exists}</td>
		              <td width="10%">${(orderlist.order_num)?if_exists}</td>
		            <tr>
		         </#list>
		         
		          </table>
		        </div>
		        <!--地址-->
		       <div class="batch">
		         <p><b>收货信息</b>：${(order_area)?if_exists}，${(goodsorder.buy_address)?if_exists}，${(goodsorder.zip_code)?if_exists}，${(goodsorder.consignee)?if_exists}，${(goodsorder.mobile)?if_exists}</p>
		         <p><b>发货信息</b>：${(shopconfig.area_attr)?if_exists} - ${(shopconfig.address)?if_exists} , ${(shopconfig.phone)?if_exists} , ${(shopconfig.contant_man)?if_exists} , ${(shopconfig.mobile)?if_exists}</p>
		         <p><b>售后信息</b>：${(buyeraddr.area_attr)?if_exists} - ${(buyeraddr.address)?if_exists} , ${(buyeraddr.phone)?if_exists} , ${(buyeraddr.consignee)?if_exists} , ${(buyeraddr.cell_phone)?if_exists}</p>
		       </div>  
  		 </div>
  		 <br/> <br/> <br/>
   </div>
     <@s.hidden name="logistics_query" id="logistics_query_id"/>
     <@s.hidden name="kuai_company" id="kuai_company_id"/> 
	</div>
	<div class="clear"/>
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>


