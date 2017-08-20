<html>
<head>
<title>取消订单</title>
<script type="text/javascript" src="/include/admin/js/goodsorder.js"></script>
<link href="/include/admin/css/logistics.css" rel="stylesheet" type="text/css"/>
</head>
  <body>
  <@s.form action="/admin_Goodsorder_adminDelivery.action" method="post" validate="true" id="tradeForm">
<div class="postion">当前位置:订单管理 > 订单列表 > 取消订单</div>
<div class="rtdcont">
	<div class="rttable">
	   <!--列表页table的数据-->	 
	   <div class="main_content">
 			 <div class="orderNum">
		           <span>订单号：${(goodsorder.order_id)?if_exists}</span><span>创建时间：${(goodsorder.order_time)?if_exists}</span>
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
		        <div class="batch">
		         <p><b>取消订单理由</b>：<@s.select  name="seller_order_cancel"  list="commparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="--请选择关闭理由--" /></p>
		       </div>  
		       
		       
		       
  		 </div>
	</div>
	<div class="clear"/>
</div>
<div class="clear"></div>
</@s.form>
  
</body>
</html>


