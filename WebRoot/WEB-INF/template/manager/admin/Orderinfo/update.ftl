<html>
  <head>
    <title>查看订单</title>
    <script type="text/javascript" src="/include/common/js/jquery.alert.js"></script>
	
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>  
  </head>
  <body>
<@s.form action="/admin_Orderinfo_update.action" method="post" validate="true" id="modiy_form">
<!--当前位置开始-->
<div class="postion">
当前位置:网站管理 > 订单管理 > 订单管理 > 查看订单
</div>
<!--当前位置结束-->
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
		 <!--详细页table的数据开始-->
		  <tr>
             <td  colspan="4" style="padding-left:50px;font-size:16px;background:#FFFBF0;height:40px;font-weight:bold;" class="td_style">
                订单状态：<#if orderinfo.order_state?if_exists=='0'>等待买家付款</#if>
    	          <#if orderinfo.order_state?if_exists=='1'>等待卖家发货</#if>
	              <#if orderinfo.order_state?if_exists=='2'>等待买家确认收货</#if>
	              <#if orderinfo.order_state?if_exists=='3' && commentState!='1'>交易成功</#if>
	              <#if orderinfo.order_state?if_exists=='4'>关闭交易</#if>
             </td>
           </tr>
           
           <tr>
             <td colspan="4" class="td_style">收货地址:${(orderinfo.cust_name)?if_exists},${(orderinfo.cell_phone)?if_exists},${(orderinfo.area_attr)?if_exists},${(orderinfo.post_code)?if_exists}</td>
           </tr>
           
           <tr style="display:none;">
             <td colspan="4" class="td_style">买家留言:${(orderinfo.remark)?if_exists}</td>
           </tr>
           
           <tr>
             <td colspan="4" class="td_style">订单信息:&nbsp;&nbsp;订单号:${(orderinfo.order_id)?if_exists}&nbsp;&nbsp;${(orderinfo.in_date)?if_exists}</td>
           </tr>
           
           <tr>
             <td colspan="4" class="tdbottom"><span class="td_style">订单流程:</span>
                <ul style="padding-left:70px;">           
		           <#list orderhistoryList as orderhistory>
		               <li>
		           		 ${(orderhistory.in_date)?if_exists}&nbsp;${(orderhistory.action_name)?if_exists}&nbsp;
                       </li><br/>
		           </#list>
		           <li><#if orderinfo.order_state?if_exists=='3'>交易成功</#if></li>
		        </ul>    
             </td>
           </tr>
           
           <tr>
             <td colspan="4" class="td_style">买家信息:<a href="/showroom/${(memberuser.user_name)?if_exists}" class="f_busin" target="_blank"> ${(member.cust_name)?if_exists}</a>
             &nbsp;&nbsp;&nbsp;&nbsp;卖家信息:<a href="/showroom/${(memberuser.user_name)?if_exists}" class="f_busin" target="_blank"> ${(seller.cust_name)?if_exists}</a>
             </td>
             
           </tr>
           
           <tr style="background-color:#E8F2FF;">
             <td width="50%">商品名称</td>
             <td width="10%">单价</td>
             <td width="10%">数量</td>
             <td width="25%">总价</td>
           </tr>
           
          <tr >
			<td class="tdbottom">
			   <a href="${stack.findValue("@com.rbt.function.ChannelFuc@getArticleUrl('supply','${(supply.supply_id)?if_exists}','${(supply.in_date)?if_exists}')")}" target="_blank">${(supply.title)?if_exists}</a>
			</td>
			<td class="tdbottom">
				${(supply.price)?if_exists}
			</td>
			<td class="tdbottom">
				${(orderinfo.goods_num)?if_exists}
			</td>
			<td class="tdbottom">
				${(orderinfo.goods_fee)?if_exists}&nbsp;元
			</td>
       	  </tr>
       	  
       	  <tr>	
			<td colspan="4" class="tdbottom">
			  <table cellspacing="0" cellpadding="0" border="0" align="right" style="padding-right:80px;">
			    <tr>
			     <td class="tdbottom">
				   运费：${(orderinfo.carriage_fee)?if_exists}&nbsp;元&nbsp;&nbsp;总支付：<font size="4" color="#FF5500"><b>${(orderinfo.total_fee)?if_exists}</b></font>&nbsp;元
				 </td>
				</tr>
			  </table>
			</td>
       	  </tr>
		 
		 <!--详细页table的数据结束-->
		</table>
	</div>
	<div class="clear"/>
   <!--操作按钮开始-->
   <div class="bsbut_detail">
	   <@s.token/>
	   ${listSearchHiddenField?if_exists}
	   <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Orderinfo_list.action','');"/>
	   <!--所属地区插件隐藏字段开始区域-->
	   <@s.hidden id="hidden_area_value" name="hidden_area_value" value="${hidden_area_value?if_exists}"/>
	   <@s.hidden id="hidden_up_area_id" name="hidden_up_area_id" value="${hidden_up_area_id?if_exists}"/>
	   <!--所属地区插件隐藏字段结束区域-->
   </div>
   <!--操作按钮结束-->
</div>
<div class="clear"></div>
</@s.form>
</body>
</html>