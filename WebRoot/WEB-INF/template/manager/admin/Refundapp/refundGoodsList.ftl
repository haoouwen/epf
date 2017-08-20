<html>
  <head>
    <title>退款列表</title>
    <link href="/include/admin/css/refund.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>   
  </head>
<body>
<@s.form action="/admin_Refundapp_refundGoodsList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:商城管理 > 售后服务 > 退款列表</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
	      
	        <td>退款编号:</td> 
			<td><@s.textfield name="return_no_s" cssStyle="width:200px;" /></td>
			<td class="tdpad">会员名称:</td> 
			<td><@s.textfield name="buy_cust_name_s" /></td>
	        <td><input type="submit" class="rbut" value="查询"></td>
	        <td><input type="button" onclick="searchShowDIV('searchDiv','300px','220px');" class="rbut" value="高级查询"></td>
	      </tr>
     </table>
   </div>
<!--条件结束-->
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
      <tr >
		     <th width="6%"  >退款编号</th>
		     
		     <th width="10%"  >订单号</th>
		     
	     	 <th width="6%"  >会员</th>
	    
	     	 <th width="10%" >申请时间</th>
	     	 
	     	 <th width="8%" >退款金额（元）</th>
	    
	     	 <th width="6%"  >处理状态</th>
	    
	     	 <th width="6%"  >退款状态</th>
	    
	         <th width="5%"   >操作</th>
	  </tr>
	  
	  <#list refundappList as refundapp>
		  <tr>
		 	
		 	    <td align="center">
		 	      <a onclick="linkToInfo('/admin_Refundapp_refundView.action','refundapp.trade_id=${refundapp.trade_id?if_exists}');">${refundapp.return_no?if_exists}</a>
		 	    </td>
		    	<td align="center">
		 	      ${refundapp.order_id?if_exists}
		    	</td>
		        
		        <td>
		           ${refundapp.buy_cust_name?if_exists}
		        </td>
		    
		    	<td align="center">${refundapp.info_date?if_exists}</td>
		    	
		    	<td>
		           ${refundapp.refund_amount?if_exists}
		        </td>
		    
		    	<td align="center">
		    	<#if (refundapp.seller_state)?if_exists>
			    	<#if (refundapp.seller_state)?if_exists=='0'>
			    	<font color="green">同意退款</font>
			    	<#elseif (refundapp.seller_state)?if_exists=='1'>
			    	<font color="red">拒绝退款</font>
			    	</#if>
		    	<#else>-</#if>
		    	</td>
		    	<td align="center">
			    	<#if (refundapp.refund_state)?if_exists=='0'><font color="red">退款中</font>
			    	<#elseif (refundapp.refund_state)?if_exists=='1'><font color="green">退款成功</font>
			    	<#elseif (refundapp.refund_state)?if_exists=='2'><font color="red">退款失败</font>
			    	<#elseif (refundapp.refund_state)?if_exists=='5'><font color="red">退款关闭</font>
			    	</#if>
		    	</td>
		    
		      <td align="center">
		     		 <a onclick="linkToInfo('/admin_Refundapp_refundView.action','refundapp.trade_id=${refundapp.trade_id?if_exists}');" title="查看">
		     		  <img src="/include/common/images/view.gif" />
		     		 </a>
		     		 <#if refundapp.refund_state=='0'>
		     		 <a onclick="linkToInfo('/admin_Refundapp_refundGoodsView.action','refundapp.trade_id=${refundapp.trade_id?if_exists}');" title="退款处理"><img src="/include/common/images/bj.gif" /></a>
		     		 <#else>
		     		 <a href="javascript:void(0);"> <img src="/include/common/images/nbj.gif" /></a>
		     		 </#if> 
		      </td>
		  </tr>
	  </#list>
	</table>
  </div>
<!--后台table结束-->
<!--翻页-->
   <div class="pages">
     ${pageBar?if_exists}
   </div>
<!--翻页结束-->

</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="seller_cust_name_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Refundapp_refundGoodsList.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
	           <tr>
					<td class="searchDiv_td">订单号:</td> 
					<td><@s.textfield name="order_id_s"  cssStyle="width:200px;"/></td>
			   </tr>
			   <tr>
					<td class="searchDiv_td">退款编号:</td> 
					<td><@s.textfield name="return_no_s" /></td>
			   </tr>
			   <tr>
					<td class="searchDiv_td">会员名称:</td> 
					<td><@s.textfield name="buy_cust_name_s" /></td>
			   </tr>
			   <tr>
					<td class="searchDiv_td">申请时间:</td> 
					<td>
					     <@s.textfield id="txtstartDate" name="starttime_s"  type="text" cssStyle="width:100px;" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'txtendDate\\',{d:1})}',readOnly:true})"  /> 
			&nbsp;至&nbsp;
			 <@s.textfield id="txtendDate" name="endtime_s" cssStyle="width:100px;"  type="text" cssClass="Wdate" 
			               readonly="true" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'txtstartDate\\',{d:1})}',readOnly:true})"  />
					</td>
			   </tr>
			
			   <tr>
					<td align="center" colspan="2" style="border:0px;">
						<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
					<input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
					</td>
		       </tr>
	   </table>
		<!--搜索框隐藏域存放-->
		</@s.form>
	</div>		   
<!--搜索区域结束-->
  </body>
</html>
