<html>
  <head>
    <title>换货列表</title>
    <script language="javascript" type="text/javascript" src="/include/components/calendar/WdatePicker.js"></script>    
  </head>
<body>
<@s.form action="/admin_Exchange_exGoodsList.action" method="post"  id="indexForm">

<!--当前位置-->
	<div class="postion">当前位置:商城管理 > 售后服务 > 换货列表</div>
<!--当前位置结束-->
<div class="rtdcont">
<!--条件查询-->
   <div class="rseacher">
     <table cellpadding="0" cellspacing="0">
	      <tr>
	      
	        <td>换货编号:</td> 
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
		     <th width="8%"  >换货编号</th>
		     
		     <th width="12%"  >订单号</th>
	        
	     	 <th width="8%"  >会员</th>
	    
	     	 <th width="12%" >申请时间</th>
	    
	     	 <th width="8%"  >处理状态</th>
	    
	     	 <th width="8%"  >换货状态</th>
	    
	         <th width="5%"   >操作</th>
	  </tr>
	  
	  <#list exchangeList as exchange>
		  <tr>
		 	
		 	    <td align="center">
		 	      <a onclick="linkToInfo('/admin_Exchange_view.action','exchange.trade_id=${exchange.trade_id?if_exists}');">${exchange.return_no?if_exists}</a>
		 	    </td>
		    	<td align="center">
		 	      ${exchange.order_id?if_exists}
		    	</td>
		        
		        <td>
		           ${exchange.buy_cust_name?if_exists}
		        </td>
		    
		    	<td align="center">${exchange.info_date?if_exists}</td>
		    
		    	<td align="center">
		    	<#if (exchange.seller_state)?if_exists>
			    	<#if (exchange.seller_state)?if_exists=='0'>
			    	<font color="green">同意换货</font>
			    	<#elseif (exchange.seller_state)?if_exists=='1'>
			    	<font color="red">拒绝换货</font>
			    	</#if>
		    	<#else>-</#if>
		    	</td>
		    	<td align="center">
			    	<#if (exchange.refund_state)?if_exists=='0'><font color="red">申请换货中</font>
			    	<#elseif (exchange.refund_state)?if_exists=='1'><font color="green">换货成功</font>
			    	<#elseif (exchange.refund_state)?if_exists=='2'><font color="red">换货失败</font>
			    	<#elseif (exchange.refund_state)?if_exists=='5'><font color="red">换货关闭</font>
			    	<#elseif (exchange.refund_state)?if_exists=='3'><font color="blue">等待会员发货</font>
				    <#elseif (exchange.refund_state)?if_exists=='4'><font color="blue">等待商家确认收货</font>
				    <#elseif (exchange.refund_state)?if_exists=='6'><font color="blue">等待商家发货</font>
			    	<#elseif (exchange.refund_state)?if_exists=='7'><font color="blue">等待会员确认收货</font>
			    	<#elseif (exchange.refund_state)?if_exists=='8'><font color="red">会员拒绝收货</font>
			    	<#elseif (exchange.refund_state)?if_exists=='9'><font color="red">换货失败</font>
		    		<#elseif (exchange.refund_state)?if_exists=='a'><font color="green">换货成功</font>
		    		<#elseif (exchange.refund_state)?if_exists=='b'><font color="red">换货失败</font>
			    	</#if>
		    	</td>
		    
		      <td align="center">
		     		 <a onclick="linkToInfo('/admin_Exchange_exGoodsView.action','exchange.trade_id=${exchange.trade_id?if_exists}');"><img src="/include/common/images/view.gif" title="查看"/></a>
		  		     <#if (exchange.refund_state)?if_exists=='0' || (exchange.refund_state)?if_exists=='4'|| (exchange.refund_state)?if_exists=='6'|| (exchange.refund_state)?if_exists=='8'>
		  		      <a onclick="linkToInfo('/admin_Exchange_exView.action','exchange.trade_id=${exchange.trade_id?if_exists}');" title="换货处理"><img src="/include/common/images/bj.gif" /></a>
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
   <div class="clear"/>
<!--翻页结束-->
<!--按钮操作存放-->
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
  <@s.hidden name="seller_cust_name_s"/>
 <!--表单框隐藏域存放-->  
</@s.form>


<!--搜索区域开始-->
	<div  style="display:none;"  id="searchDiv"  class="searchDiv">
	<@s.form action="/admin_Exchange_exGoodsList.action" method="post"  id="form_search_id">
		<table width="100%" border="0" cellspacing="0">
	           
	           <tr>
					<td class="searchDiv_td">订单号:</td> 
					<td><@s.textfield name="order_id_s" cssStyle="width:200px;"/></td>
			   </tr>
	           
			   <tr>
					<td class="searchDiv_td">换货编号:</td> 
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
