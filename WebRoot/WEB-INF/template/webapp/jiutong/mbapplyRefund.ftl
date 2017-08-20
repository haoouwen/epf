<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${cfg_webname?if_exists}申请退款/退换货</title>
<meta name="author" content="久通宏达科贸（北京）有限公司">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">
<link href="/malltemplate/jiutong/css/mbPublic.css" rel="stylesheet" type="text/css"></link>
<link href="/malltemplate/jiutong/css/mbMember.css" rel="stylesheet" type="text/css"></link>
<link href="/wro/webapp_common_publiccss.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="top">
   <a href="javascript:gbackhistory();" class="topReturn"><img src="/malltemplate/jiutong/images/topReturn.png"></a>申请退款/退换货<span class="topCls"></span>
</div>
<#include "/WEB-INF/template/webapp/jiutong/mbCommonTop.html">
<!--订单商品-->
<div class="hasOrder">
<!--订单商品-->
<div class="hasOrder">
  <div class="orderh2">已选服务的商品<@s.fielderror><@s.param>re_goods_id_str</@s.param></@s.fielderror></div>
  <!--已选服务的商品-->
   <#list detailList as dlist>
    <#assign strade_id =dlist.trade_id>
     <#if (refund_goods_id_str)?if_exists?index_of(strade_id?string) gt (-1)>
  <header class="hoDiv">
    <table>
      <tr>
        <td>
	        <div class="imgDiv">
	         <#if dlist.img_path!=''>
				<#if ((dlist.img_path)?index_of(",") > (-1))>      			
					<#assign startpos = "${dlist.img_path?if_exists}"?index_of(',')>
		            <#if ((startpos-1)>-1)>
		                <#assign img =(dlist.img_path)[(0)..(startpos-1)]>
		             </#if>
		         <#else> 
		         		<#assign img =dlist.img_path>
		         </#if> 
	         	<a href="#" >
	  				<img src="${(img)?if_exists}" />
	  			</a>
			 <#else>
			 	<a href="#" >
					<img src="${(cfg_nopic)?if_exists}" >
				</a>
			 </#if>	
	        </div>
        </td>
        <td>
           <p><a href="#" >${(dlist.temporary_goodsname)?if_exists}</a></p>
           <p>数量：<#if dlist.order_num?if_exists>${dlist.order_num}<#else>-</#if></p>
           <p>商品原价：<b>￥${(dlist.old_subtotal)?if_exists}</b></p>
            <p>申请数量：${(dlist.apply_num)?if_exists}</p>
            <p>状态： 
               <#if dlist.orderdetail_state=='1'>
               			 <span class="pcOrange" >退款中</span>
                <#elseif dlist.orderdetail_state=='2'>	
                 		 <span class="pcOrange" >部分售后</span>
                <#elseif dlist.orderdetail_state=='3'>
               			 <span class="pcOrange" > 退款成功</span>
                <#elseif dlist.orderdetail_state=='4'>
                  		<span class="pcOrange" >退货中</span>
                <#elseif dlist.orderdetail_state=='5'>
                		  <span class="pcOrange" >部分售后</span>
                <#elseif dlist.orderdetail_state=='6'>
                 	    <span class="pcOrange" >退货成功</span>
                <#elseif dlist.orderdetail_state=='7'>
                 		 <span class="pcOrange" >换货中</span>
                <#elseif dlist.orderdetail_state=='8'>
                  		<span class="pcOrange" >部分售后</span>
                <#elseif dlist.orderdetail_state=='9'>
               		    <span class="pcOrange" >  换货成功</span>
               <#elseif dlist.orderdetail_state=='0'>
               		   --
                </#if>
	         </p>
        </td>
      </tr>
    </table>
  </header>
  </#if>
   </#list>
</div>
<@s.form action="/webapp/refundapp!orderBuyRefundment.action" method="post"  id="tradeForm"  >
<@s.hidden name="imgString" id="member_icon_refund"/>
<@s.hidden name="imgStrings" id="member_icon_ex"/>
<div class="apply">
 	<div class="applyh2">
 	   <#if if_refund==1>
 	    <a href="###" onclick="isReturn('0')"  id="tkbutton_id"><i class="selli">退款</i></a>
       </#if>
       <#if if_regoods==1>
       <a href="###" onclick="isReturn('1')"  id="thbutton_id"><i>退货</i></a>
       </#if>
       <#if if_exchangegoods==1>
       <a href="###" id="hhbutton_id" onclick="isReturn('2')"><i>换货</i></a>
       </#if>
      <br class="clear"/>
    </div>
    
    
    
    <#if if_exchangegoods ==1>
    <!--退款退货-->
     <div class="applyCont" id="th">
      <p><span><font id="liyou_id">退款原因</font>:</span>
      <@s.select  name="buy_refund_type"  list="commparaList" listValue="para_key"
       listKey="para_value" headerKey="--请选择理由--" headerValue="--请选择理由--" id="buy_refund_type" value="${refundapp?if_exists.buy_type?if_exists}"/>
       <@s.fielderror><@s.param>buy_refund_type</@s.param></@s.fielderror>
      <br class="clear"/>
      </p>
      <p><span><font id="jine_id">退款金额</font>:</span>
      
      
        <#assign t_amount=0>
       <#list detailList as dlist>
          <#assign strade_id =dlist.trade_id>
          <#assign ont_price =dlist.trade_id>
          <#if (refund_goods_id_str)?if_exists?index_of(strade_id?string) gt (-1)>
              <#assign t_amount = "${(t_amount?number + dlist.subtotal?number)?number}">
          </#if>
       </#list>
      <input type="text"  name="need_refund"  id="need_refund" class="applyText"  maxlength="11"  value="${(t_amount)?if_exists?number}" onkeyup="checkRMB(this);checkAll(this,'${(t_amount)?if_exists?number}');"/>&nbsp;元
       退款上限:<b id="sx" >${(t_amount)?if_exists}</b>元 ( 扣除优惠后的可退金额 )
       <@s.fielderror><@s.param>need_refund</@s.param></@s.fielderror>
       <br class="clear"/>
      </p>
      <p><span>问题描述:</span>
       <@s.textarea  name="buy_refund_reason" cssClass="applyArea"
        onkeyup="checkLength(this,200)" id="buy_refund_reason" /> 200字
      <@s.fielderror><@s.param>buy_refund_reason</@s.param></@s.fielderror>
      <br class="clear"/>
      </p>
      <!-- 待接入图片上传控件 -->
      <p id="upload_refund"><span>上传图片:</span>
		<div id="dropbox"></div> 
        <input id="uploadifyfile" name="uploadifyfile" class="upload_refund" type="file" multiple>
      </p>
       <p>最多上传3张，每张不超过2M，支持JPG，BMP，PNG，GIF</p>
       <div id="img_src"></div>
       <div class="submitDiv"><@s.submit value="提 交"  /></div>
    </div> 
    
    <!--换货-->
     <div class="applyCont" id="hh" style="display:none">
     
      <p><span>换货原因:</span>
      
       <@s.select  name="refund_type"  list="hcommparaList" listValue="para_key"
       listKey="para_value" headerKey="--请选择理由--" headerValue="--请选择理由--"  value="${exchange?if_exists.refund_type?if_exists}"/>
       <@s.fielderror><@s.param>refund_type</@s.param></@s.fielderror>
      <br class="clear"/></p>
      
      <p><span>问题描述:</span>
     <@s.textarea  name="refund_reason" cssClass="applyArea" onkeyup="checkLength(this,200)" id="buy_refund_reason" /> 200字
     <@s.fielderror><@s.param>refund_reason</@s.param></@s.fielderror>
      <br class="clear"/></p>
      <!-- 待接入图片上传控件 -->
      <p id="upload_ex"><span>上传图片:</span>
        <div id="dropbox"></div> 
        <input id="uploadifyfile" name="uploadifyfile" class="upload_ex" type="file" multiple>
      </p>
      <p><div id="img_src_ex"></div></p>
      <br class="clear"/>
      <p>最多上传3张，每张不超过2M，支持JPG，BMP，PNG，GIF</p>
      
      <br class="clear"/>
      <p><span>发货姓名:</span>
       <@s.textfield  cssClass="applyText" name="exchange.mconsignee"/>
       <@s.fielderror><@s.param>exchange.mconsignee</@s.param></@s.fielderror>
      <br class="clear"/></p>
      
      <p><span>所在地区:</span>
	       <div id="areaDiv" style="margin-left:0px;"></div>
	       <@s.fielderror><@s.param>areaDiv</@s.param></@s.fielderror>
      <br class="clear"/></p>
      
      <p><span>具体地址:</span>
	      <@s.textfield  cssClass="applyText" name="exchange.buy_address"/>
	      <@s.fielderror><@s.param>exchange.buy_address</@s.param></@s.fielderror>
      <br class="clear"/></p>
      
      <p><span>手机号码:</span>
	      <@s.textfield   id="phone" cssClass="applyText" name="exchange.mmobile"  onkeyup="checkDigital(this)" maxlength="11" />
	      <@s.fielderror><@s.param>exchange.mmobile</@s.param></@s.fielderror>
      <br class="clear"/></p>
      <div class="submitDiv">
      <input  type="button"  onclick="submitRefund();" value="提 交" />
      </div>
    </div>  
  
    <#else>
      <!--无法售后-->
     <div >
      <div class="submitDiv">
      <span class="pcOrange" >*抱歉,您的订单商品已经超过售后有效期,暂时无法申请任何售后服务!</span>
      </div>
    </div>  
    </#if>
    
</div>
<@s.hidden name="order_id" id="g_order_id"  value="${(goodsorder.order_id)?if_exists}"/>
<@s.hidden name="goodsorder.order_state" id="order_state" />
<@s.hidden name="refundapp_id"id="refundapp_id" value="${refundapp?if_exists.trade_id?if_exists}"/>
<@s.hidden name="exchange_id"id="exchange_id" value="${exchange?if_exists.trade_id?if_exists}"/>
<@s.hidden name="is_return_str" id="is_return" value="0" />
<@s.hidden name="refund_goods_id_str" />
<@s.hidden name="apply_num_str" />
<@s.hidden name="all_refund" id="all_refund" value="${(t_amount)?if_exists}" />
<@s.hidden id="hidden_area_value" name="hidden_area_value"/>
<@s.hidden id="back_goods_num" name="back_goods_num"/>
 </@s.form>
</div>
<!--尾部-->
<#include "/a/webapp/mbFooter.html">
<#include "/a/webapp/mbCommon.html">
</body>
<script src="/wro/webapp_common.js" type="text/javascript"></script>
<script src="/malltemplate/jiutong/js/refundapp.js" type="text/jscript"></script>
<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
<script type="text/javascript" src="/include/components/uploadify/jquery.html5uploader.min.js"></script> 
<script src="/malltemplate/jiutong/js/uploadRefundImg.js" type="text/javascript"></script>
<script type="text/javascript" >
  //初始化加载
	$(document).ready(function(){
	//所属地区的回选
	 loadArea("${areaIdStr?if_exists}","");
	 isReturn(${is_return_str});
	});
 </script>
</html>
