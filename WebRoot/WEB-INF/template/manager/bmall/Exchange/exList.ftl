<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>换货</title>
<script type="text/javascript" src="/include/bmember/js/exchange.js"></script>
</head>

<body>
<@s.form action="/bmall_Exchange_buylist.action" method="post" id="indexForm">
  <!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>换货</span></h2>
        <div class="rTopDiv">
          <span>换货编号:<input type="text" class="queryText" name="return_no_s">
          <input type="submit" value="查询" class="cssbtn"/>
          </span>
        </div>
        <!----> 
        <div class="usedTDiv">
        
            <table width="100%" cellpadding="0" cellspacing="0" class="usedTab">
                <tr>
                    <th width="14%">换货编号</th>
                    <th width="33%">订单商品</th>
                    <th width="14%">提交时间</th>
                    <th width="8%">数量</th>
                    <th width="16%">申请状态</th>
                    <th width="13%">操作</th>
                </tr>
               
            <#list exchangeList as exchange>
                  <!--定于撤销申请需要的字段-->
	                <#assign num=0>
	       		    <#assign list_img_str=""><!-- 换货图片-->
	       		    <#assign ret_number_str=""><!-- 换货编号-->
	       		    <#assign ret_liyou_str=""><!-- 换货理由-->
	       		    <#assign ret_desc_str=""><!-- 换货描述-->
                     <!--获取撤销申请需要的值-->
	       		    <#assign ret_number_str=exchange.return_no><!-- 换货编号-->
	       		    <#list commparaList as com>
			            <#if com.para_value?if_exists==exchange.refund_type?if_exists>
			              <#assign ret_liyou_str="${com.para_key?if_exists}"><!-- 换货理由 -->
			            </#if>
		            </#list>
                    <#assign ret_desc_str=exchange.buy_reason> <!--换货描述-->
                <tr>
                  <td><a href="#" class="bluea">${exchange.return_no?if_exists}</a></td>
                  <td class="lUsedTd">
                  <#list detailList as detail>
                 	 <#if ((detail.order_id)?if_exists)==((exchange.order_id)?if_exists)>
                 	 <!-- 主要用于撤销申请提示框的数据 -->
                 	 <#assign num=num+1>   
			         <#if num?if_exists=="1">
			         	<#assign list_img_str="${detail.list_img?if_exists}"><!-- 换货图片-->
			   		 <#else>
			   			 <#assign list_img_str="${list_img_str}#${detail.list_img?if_exists}"><!-- 换货图片-->
			   		 </#if>
                 	 <#assign img_path="${( detail.list_img)?if_exists}">
                     <div class="reUsedImg">
	                     <a href="/mall-goodsdetail-${detail.goods_id?if_exists}.html" title="${detail.temporary_goodsname?if_exists}" target="_blank">
	                     	<img src="<#if img_path!=''>${img_path?if_exists}<#else>${(cfg_nopic)?if_exists}</#if>"/></a>
	                     </a>
                     </div>
                    </#if>
	      		 </#list> 
                  </td>
                  <td>${exchange.info_date?if_exists}</td>
                  <td><b class="mred">${exchange.ex_goods_num?if_exists}</b></td>
                  <td>
                  	<#if (exchange.refund_state)?if_exists=='0'><b class="blueSpan">等待处理</b>
			    	<#elseif (exchange.refund_state)?if_exists=='1'><b class="greenSpan">换货成功</b>
			    	<#elseif (exchange.refund_state)?if_exists=='2'><b >换货失败</b>
			    	<#elseif (exchange.refund_state)?if_exists=='5'><b class="blueSpan">撤销申请</b>
			    	<#elseif (exchange.refund_state)?if_exists=='3'><b class="blueSpan">等待会员发货</b>
			    	<#elseif (exchange.refund_state)?if_exists=='4'><b class="blueSpan">等待商家确认收货</b>
			    	<#elseif (exchange.refund_state)?if_exists=='6'><b class="blueSpan">等待商家发货</b>
			    	<#elseif (exchange.refund_state)?if_exists=='7'><b class="blueSpan">等待会员确认收货</b>
			    	<#elseif (exchange.refund_state)?if_exists=='8'><b class="blueSpan">会员拒绝收货</b>
			    	<#elseif (exchange.refund_state)?if_exists=='9'><b >换货失败</b>
		    		<#elseif (exchange.refund_state)?if_exists=='a'><b class="greenSpan">换货成功</b>
		    		<#elseif (exchange.refund_state)?if_exists=='b'><b>换货失败</b>
		    	</#if>
                  </td>
                  <td>
                     <p>
                      <a href="###" onclick="linkToInfo('/bmall_Exchange_buygoodsview.action','exchange.trade_id=${exchange.trade_id}&selli=${selli?if_exists}&parentMenuId=${parentMenuId?if_exists}')");" class="bluea">查看详情</a>
                     </p>
                     <#if (exchange.refund_state)?if_exists=='0'>
                     <p> <a onclick="cancelOrder('${exchange.trade_id?if_exists}','${ret_number_str?if_exists}','${list_img_str?if_exists}','${ret_liyou_str?if_exists}','${ret_desc_str?if_exists}')" >撤销申请</a></p>
                	 </#if>
                  </td>
                </tr> 
                </#list>
            </table>                 
        </div>
        <!---->
       <div class="listbottom">${pageBar?if_exists}</div>
        
   </div>
   
  </div>
  <div class="clear"></div>
</div>
<@s.hidden name="cancel_trade_id" id="cancel_desr_id"/>   
<@s.hidden name="order_id" id="cancel_trade_id"/>
 <@s.hidden name="selli"/>
<@s.hidden name="parentMenuId"/>
</@s.form>
<!--取消订单弹出层-->
<div class="popupDiv" id="cancelOrderId">
</div>
</body>

</html>
