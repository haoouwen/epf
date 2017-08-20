<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>退款/退货 </title>
<script type="text/javascript" src="/include/bmember/js/refundapp.js"></script>
</head>

<body>
<@s.form action="/bmall_Refundapp_buylist.action" method="post" id="indexForm">
  <!--右边-->
  <div class="wR810">
   <!--最新订单-->
   <div class="rwTitle">
   
   		<h2><span>退款/退货</span></h2>
        <div class="rTopDiv">
          <span>服务类型：<@s.select  name="is_return_s"  list=r"#{'999':'--请选择--','0':'退款','1':'退货'}" /></span>
          <span>退款编号:<input type="text" class="queryText" name="return_no_s">
          <input type="submit" value="查询" class="cssbtn"/>
          </span>
        </div>
        <!----> 
        <div class="usedTDiv">
        
            <table width="100%" cellpadding="0" cellspacing="0" class="usedTab">
                <tr>
                    <th width="10%">服务类型</th>	
                    <th width="14%">退款编号</th>
                    <th width="23%">订单商品</th>
                    <th width="14%">提交时间</th>
                    <th width="8%">数量</th>
                    <th width="10%">返还金额</th>
                    <th width="12%">申请状态</th>
                    <th width="12%">操作</th>
                </tr>
               
            <#list refundappList as refundapp>
                  <!--定于撤销申请需要的字段-->
	                <#assign num=0>
	       		    <#assign list_img_str=""><!-- 退款图片-->
	       		    <#assign ret_price_str=""><!-- 退款金额-->
	       		    <#assign ret_type_str=""><!-- 退款类型-->
	       		    <#assign ret_number_str=""><!-- 退款编号-->
	       		    <#assign ret_liyou_str=""><!-- 退款理由-->
	       		    <#assign ret_desc_str=""><!-- 退款描述-->
                     <!--获取撤销申请需要的值-->
                    <#if (refundapp.is_return)?if_exists==0> <#assign ret_type_str="退款"><#else> <#assign ret_type_str="退货"></#if></b><!-- 退款类型-->
                    <#assign ret_price_str=refundapp.refund_amount><!-- 退款金额-->
	       		    <#assign ret_number_str=refundapp.return_no><!-- 退款编号-->
	       		    <#list commparaList as com>
			            <#if com.para_value=refundapp.buy_type>
			              <#assign ret_liyou_str=com.para_key><!-- 退款理由 -->
			            </#if>
		            </#list>
                    <#assign ret_desc_str=refundapp.buy_reason> <!--退款描述-->
                <tr>
                  <td><b>
                  <#if (refundapp.is_return)?if_exists==0>退款<#else>退货</#if></b>
                  </td>
                  <td><a href="#" class="bluea">${refundapp.return_no?if_exists}</a></td>
                  <td class="lUsedTd">
                  <#list detailList as detail>
                 	 <#if ((detail.order_id)?if_exists)==((refundapp.order_id)?if_exists)>
                 	 <!-- 主要用于撤销申请提示框的数据 -->
                 	 <#assign num=num+1>   
			         <#if num?if_exists=="1">
			         	<#assign list_img_str="${detail.temporary_goodsimg?if_exists}"><!-- 退款图片-->
			   		 <#else>
			   			 <#assign list_img_str="${list_img_str}#${detail.temporary_goodsimg?if_exists}"><!-- 退款图片-->
			   		 </#if>
                 	 <#assign img_path="${( detail.temporary_goodsimg)?if_exists}">
                     <div class="reUsedImg">
	                     <a href="/mall-goodsdetail-${detail.goods_id?if_exists}.html" title="${detail.temporary_goodsname?if_exists}" target="_blank">
	                     	<img src="<#if img_path!=''>${img_path?if_exists}<#else>${(cfg_nopic)?if_exists}</#if>"/>
	                     </a>
                     </div>
                    </#if>
	      		 </#list> 
                  </td>
                  <td>${refundapp.info_date?if_exists}</td>
                  <td><b class="mred">${refundapp.back_goods_num?if_exists}</b></td>
                  <td><b class="mred">￥${refundapp.refund_amount?if_exists}</b></td>
                   
                  <td>
                  	<#if (refundapp.refund_state)?if_exists=='0'><b class="blueSpan">等待处理</b></font>
			    	<#elseif (refundapp.refund_state)?if_exists=='1'><b class="greenSpan">退款成功</b></font>
			    	<#elseif (refundapp.refund_state)?if_exists=='2'><b >退款失败</b></font>
			    	<#elseif (refundapp.refund_state)?if_exists=='5'><b class="greenSpan">撤销申请</b></font>
			    	<#elseif (refundapp.refund_state)?if_exists=='3'><b class="blueSpan">等待买家发货</b></font>
			    	<#elseif (refundapp.refund_state)?if_exists=='4'><b class="blueSpan">等待卖家确认收货</b></font>
		    	</#if>
                  </td>
                  <td>
                     <p>
                     <#if refundapp.is_return=='0'>
                     <a href="###" onclick="linkToInfo('/bmall_Refundapp_buyfundview.action','refundapp.trade_id=${refundapp.trade_id}')");" class="bluea">查看详情</a>
                     <#elseif refundapp.is_return=='1'>
                      <a href="###" onclick="linkToInfo('/bmall_Refundapp_buygoodsview.action','refundapp.trade_id=${refundapp.trade_id}')");" class="bluea">查看详情</a>
                     </#if>
                     </p>
                     <#if (refundapp.refund_state)?if_exists=='0'>
                     <p> <a onclick="cancelOrder('${refundapp.trade_id?if_exists}','${ret_price_str?if_exists}','${ret_number_str?if_exists}','${list_img_str?if_exists}','${ret_type_str?if_exists}','${ret_liyou_str?if_exists}','${ret_desc_str?if_exists}')" >撤销申请</a></p>
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
<@s.hidden name="cancel_trade_desc" id="cancel_desr_id"/>   
<@s.hidden name="order_id" id="cancel_trade_id"/>
</@s.form>
<!--取消订单弹出层-->
<div class="popupDiv" id="cancelOrderId">
</div>
</body>

</html>
