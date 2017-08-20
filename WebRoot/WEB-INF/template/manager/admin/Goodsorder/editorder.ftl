<html>
  <head>
    <title>商品订单详情</title>
    <link href="/include/admin/css/goodsorder.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/include/common/js/get-cat-area.js"></script>
	<script type="text/javascript" src="/include/common/js/copytext.js"></script>
	<script type="text/javascript" src="/include/admin/js/goodsorder.js"></script>
	<script type="text/javascript">
	  $(function(){
	    	loadArea("${areaIdStr?if_exists}","");
	  })
	  //控制涨价或者折扣，修改订单总额
	  function addprice(){
	     var upprice=$("#id_addprice").val();
	     var forprice=$("#id_total_amount").val();
	     var totalprice=0;
	     var checkprice=$('input:radio[name="updown_price"]:checked').val();
	     if(checkprice=="0"){
	       if(parseInt(forprice)>parseInt(upprice)){
	        	totalprice= parseInt(forprice)-parseInt(upprice);
	       }else  if(parseInt(forprice)<parseInt(upprice)){
	       		alert("请输入正确的价格!");
	       		totalprice=parseInt(forprice);
	       		return false;
	       }
	     }else if(checkprice=="1"){
	       totalprice= parseInt(upprice)+parseInt(forprice);
	     }
	     $("#id_total_amount").val(totalprice);
	  }
	</script>
  </head>
  <body>
  <@s.form action="/admin_Goodsorder_adminUpdate.action" method="post" validate="true" id="tradeForm">
<div class="postion">当前位置:商城管理 > 订单管理 > 订单列表 > 订单详情</div>
<div class="rtdcont">
	<div class="rttable">
	   <!--列表页table的数据-->	
	    <div class="main_cont">
 		<div class="main_content">
 			<div>
 			<b>商品信息</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
		       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
		           <tr >
		             <th class="gth" width="15%;" style='line-height:25px;'>货号</th>
		             <th class="gth" width="40%;">
		            	商品名称
		             </th>
		             <th class="gth" width="15%;">
		            	价格
		             </th>
		             <th class="gth" width="10%;">
		            	购买数量
		             </th>
		             <th class="gth" width="10%;">
		            	小计
		             </th>
		           </tr>
		           <#list orderdetailList as orderlist>
		           <tr>
		             <td class="gtd">${(orderlist.goods_id)?if_exists}</td>
		             <td align="center">
		             <#if (orderlist.goods_attr)?if_exists>
				      		 <#assign g_attr=orderlist.goods_attr>
				      		  <#if ((g_attr)?index_of("<br>") > (-1))>
				      		       <#assign g_attr =g_attr.replace('<br>','')>
				      		  </#if>
				      		 </#if>
		            	${(orderlist.goods_name)?if_exists}(${(g_attr)?if_exists})
		             </td>
		              <td class="gtd">
		            		${(orderlist.goods_price)?if_exists}
		             </td>
		              <td class="gtd">
		            		${(orderlist.order_num)?if_exists}
		             </td>
		              <td class="gtd">
		            		${(orderlist.goods_price*orderlist.order_num)?if_exists}
		             </td>
		           </tr>
		           </#list>
				           
	            </table>
			 		</div>
			 	</div>
			 <br/>
			 <div>
			 <b>订单信息</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
			       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
					           <tr>
						             <td class="ordertd">订单号</td>
						             <td width="30%">
						            		${(goodsorder.order_id)?if_exists}
						             </td>
						             <td class="ordertd">订单状态</td>
						             <td>
						                <#list commparaList as comlist>
						                   <#if comlist.para_value==goodsorder.order_state> 
						                   ${(comlist.para_key)?if_exists}
						                   </#if>
						                </#list>
						            	
						             </td>
						            
					           </tr>
					           <tr>
						             <td class="ordertd">商品总金额</td>
						             <td>
						            	${(goodsorder.goods_amount)?if_exists}
						             </td>
						             <td class="ordertd">下单日期</td>
						             <td width="30%">
						            	${(goodsorder.order_time)?if_exists}
						             </td>
						             
						             
					           </tr>
					           <tr>
						             <td class="ordertd">配送费用</td>
						             <td>
						            		${(goodsorder.ship_free)?if_exists}
						             </td>
						             <td class="ordertd">支付方式</td>
						             <td>
						               <@s.select  name="goodsorder.pay_id"  list="paymentList" listValue="pay_name" listKey="pay_id" headerKey="" headerValue="--请选择--" />   
						             </td>
						          
					           </tr>
					           <tr>
						             <td class="ordertd">保价费用</td>
						             <td>
						                ${(goodsorder.insured)?if_exists}
						             </td>
						                   <td class="ordertd">配送方式</td>
						             <td>
						                <@s.select  name="goodsorder.send_mode"  list="sendmodeList" listValue="smode_name" listKey="smode_id" headerKey="" headerValue="--请选择--" />   
						             </td>
						             
					           </tr>
					           <tr>
						             <td class="ordertd">支付手续费</td>
						             <td>
						            	 ${(goodsorder.comm_free)?if_exists}
						             </td>
						             <td class="ordertd">订单折扣或涨价</td>
						             <td>
						            <@s.radio name="updown_price"  list=r"#{'0':'折扣','1':'涨价'}" value="0"/>
						                 <@s.textfield id="id_addprice"  onkeyup="checkNum(this)"
						                   maxlength="10"   cssStyle="width:100px;"   />
						                   <input type="button"  style="cursor:pointer;" value="确定"  onclick="addprice();"/>
						             </td>
					           </tr>
					           <tr>
					           		 <td class="ordertd">订单总金额</td>
						             <td >
						             <@s.textfield id="id_total_amount"  readonly="true"  name="goodsorder.tatal_amount"  maxlength="10"  />
						             </td>
						           <td class="ordertd"></td>
						             <td> </td>
						            
					           </tr>
		            </table>
				 </div>
			 </div>
			  <br/>
			 <div id="oper_seo_div" class="oper_seo_div">      
							       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
									           <tr>
										             <td width="20%;">会员备注</td>
										             <td>
										            	${(goodsorder.mem_mark)?if_exists}
										             </td>
									           </tr>
									            <tr>
										             <td width="20%;">订单备注</td>
										             <td>
										            		${(goodsorder.order_mark)?if_exists}
										             </td>
									           </tr>
						            </table>
							<div class="clear"></div>
			 </div>
			  <br/>
			 <div>
			 <b>购买人信息</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
			       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
					           <tr>
						             <td class="ordertd">会员用户名</td>
						             <td width="30%;">
						            	${(memberuser.user_name)?if_exists}
						             </td>
						             <td class="ordertd">姓名</td>
						             <td width="30%;">
						            	${(memberuser.real_name)?if_exists}
						             </td>
					           </tr>
					           <tr>
					                 <td class="ordertd">联系电话</td>
						             <td>
						            	${(memberuser.phone)?if_exists}
						             </td>
						             <td class="ordertd">手机</td>
						             <td>
						            	${(memberuser.cellphone)?if_exists}
						             </td>
					           </tr>
					           <tr>
					           <td class="ordertd">MSN</td>
						             <td>
						            	${(memberuser.msn)?if_exists}
						             </td>
						             <td class="ordertd">QQ</td>
						             <td>
						            	${(memberuser.qq)?if_exists}
					            		<#--<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${(memberuser.qq)?if_exists}&site=qq&menu=yes" target="_blank">
								        <img border="0" src="http://wpa.qq.com/pa?p=1:${(memberuser.qq)?if_exists}:16" alt="在线客服">
								        </a>-->
						             </td>
					           </tr>
					           
		            </table>
					</div>
			 </div>
			   <br/>
			 <div>
			 <b>收货人信息</b>
			 <div id="shouhuo" class="oper_seo_div">      
							       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
									           <tr>
										             <td id="name" class="ordertd">收货人姓名<font id="a" color="red">*</font></td>
										             <td width="30%;">
										              <@s.textfield  name="goodsorder.consignee"  maxlength="15"  />
										              <@s.fielderror><@s.param>goodsorder.consignee</@s.param></@s.fielderror>
										             </td>
										             <td id="mobile" class="ordertd">联系手机<font id="a" color="red">*</font></td>
										             <td width="30%;">
										             <@s.textfield  name="goodsorder.mobile"  maxlength="11"  onkeyup="checkNum(this)"  />
										             <@s.fielderror><@s.param>goodsorder.mobile</@s.param></@s.fielderror>
										             </td>
									           </tr>
									           <tr>
									           <td id="phone" class="ordertd">联系电话</td>
										             <td>
										             <@s.textfield  name="goodsorder.telephone"  maxlength="15" onkeyup="checkNum(this)"  />
										             <@s.fielderror><@s.param>goodsorder.telephone</@s.param></@s.fielderror>
										             </td>
										             <td id="post" class="ordertd">邮政编码<font color="red">*</font></td>
										             <td>
										             <@s.textfield  name="goodsorder.zip_code"  maxlength="6"  onkeyup="checkNum(this)" />
										             <@s.fielderror><@s.param>goodsorder.zip_code</@s.param></@s.fielderror>
										             </td>
									           </tr>
									           <tr>
									           <td id="addres" class="ordertd">收货地区<font color="red">*</font></td>
										             <td >
										            	<table>
										             		<tr>
										             			<td class="tdbottom">
										             				<div id="areaDiv" style="margin-left:-5px;"></div>
										             			</td>
										             			<td class="tdbottom">
										             				<@s.fielderror><@s.param>area_attr</@s.param></@s.fielderror>
											              		</td>
											              	</tr>
											            </table>
										             </td>
										              <td></td>
										             <td></td>
									           </tr>
									            <tr>
									           		<td id="addres" class="ordertd">详细收货地址<font color="red">*</font></td>
										             <td >
										             <@s.textfield  name="goodsorder.buy_address"  maxlength="100" cssStyle="width:400px;"  />
										             <@s.fielderror><@s.param>goodsorder.buy_address</@s.param></@s.fielderror>
										             </td>
										             <td></td>
										             <td></td>
									           </tr>
						            </table>
					</div>
			 </div>
			 
			 	 <div>
			 <b>物流信息</b>
			 <div id="shouhuo" class="oper_seo_div">      
							       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
									           <tr>
										             <td id="name" class="ordertd">物流公司</td>
										             <td width="30%;">
										            <#list sendmodeList as slist>
									                     <#if goodsorder.send_mode==slist.smode_id>
									                          ${(slist.smode_name)?if_exists}
									                     </#if>
									                </#list>
										             </td>
										             <td id="mobile" class="ordertd">物流单号</td>
										             <td width="30%;">
										               ${(goodsorder.send_num)?if_exists}
										             </td>
									           </tr>

						            </table>
					</div>
			 </div>
			 
			 
			  <br/>
			<div>
 			<b>订单日志</b>
			 <div id="oper_seo_div" class="oper_seo_div">      
		       	 <table cellSpacing=1 cellPadding=1 width="100%;" class="tabdiv">   
		           <tr>
		             <th class="gth" width="15%;"  style='line-height:25px;'>序号</th>
		              <th class="gth" width="10%;">
		                 任务
		             </th>
		             <th class="gth" width="15%;">
		                 时间
		             </th>
		             <th class="gth" width="10%;">
		            	操作人
		             </th>
		             <th class="gth" width="30%;">
		                备注
		             </th>
		           </tr>
		           <#list ordertransList as otranslist>
		           <tr>
		             <td class="gtd">${otranslist_index+1}</td>
		             <td class="gtd">
		                <#list commparaList as comlist>
		                   <#if comlist.para_value==otranslist.order_state> 
		                   ${(comlist.para_key)?if_exists}
		                   </#if>
		                  </#list>
		             </td>
		              <td class="gtd">
		            		${(otranslist.trans_time)?if_exists}
		             </td>
		             <td align="center">
		            	${(otranslist.user_name)?if_exists}
		             </td>
		              <td class="gtd">
		            		${(otranslist.reason)?if_exists}
		             </td>
		           </tr>
		           </#list>
				           
	            </table>
			 		</div>
			 	</div>
			 <br/>
			 <div>
			 <div class="fixbuttom" style="margin-top:10px;">
			   <@s.token/>
			    <@s.hidden name="sysdesc" id="sysdesc" />
			    <@s.hidden name="sysvalue" id="sysvalue" />
			    <@s.hidden name="goodsorder.order_id"/>  
			    
			      <#if goodsorder.order_state=='2'>
				    	  <a class="order_img_1" onclick="sellerDelivery('${goodsorder.order_id?if_exists}','3');" >
				    发货</a>
				    </#if>
				    <#if goodsorder.order_state=='1'>
				    	  <a class="order_img_2" onclick="sellerCancelOrder('${goodsorder.order_id?if_exists}','0');" >
				    取消订单</a>
				    </#if>
				    <#if goodsorder.order_state=='4'>
				    	 <a class="order_img_2" onclick="sellerConfirmRefund('${goodsorder.order_id?if_exists}','5');" >
				    同意退款</a>
				    	 <a class="order_img_2" onclick="sellerRefusedRefund('${goodsorder.order_id?if_exists}','6');" >
				    拒绝退款</a>
				    </#if>
				     <a class="order_img_2" onclick="linkToInfo('/bmall_Goodsorder_sellerorderlist.action','order_state=${(goodsorder.order_state)?if_exists}');" >
				    返回</a>
			    
			    
			   <@s.submit value="保存"/>
			   <input type="button" name="returnList" 
			   style="cursor:pointer;" value="返回列表"  onclick="linkToInfo('/admin_Goodsorder_list.action?order_state=${(order_state)?if_exists}','');"/>
			   
			</div>
  		 </div>
   </div>
	</div>
	<div class="clear"/>
</div>
<div class="clear"></div>
</@s.form>
  
<div class="
</body>
</html>

