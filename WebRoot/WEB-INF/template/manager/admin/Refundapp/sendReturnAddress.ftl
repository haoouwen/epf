<html>
  <head>
    <title>退货地址选择</title>
    <script type="text/javascript" src="/include/admin/js/refundapp.js"></script>   
  </head>
<body>
<@s.form action="/admin_Buyeraddr_sysList.action" method="post"  id="indexForm">
<!--当前位置-->
	<div class="postion">当前位置:商城管理 > 售后服务 > 退货地址</div>
<!--当前位置结束-->
<div class="rtdcont">
	  
<!--后台table-->
   <div class="rtable">
     <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
          <tr >
		 <th width="5%"></th>

     	 <th width="95%"  >选择退货地址：</th>
     	 
    	 </tr>
    	  <#list buyeraddrList as buyeraddr>
			<tr>
			<td><input type="radio" name="buyeraddr.addr_id" value="${buyeraddr.addr_id?if_exists}"/></td>    
			<td align="left">
			   ${buyeraddr.consignee?if_exists}&nbsp;${buyeraddr.area_attr?if_exists}&nbsp;${buyeraddr.address?if_exists}&nbsp;${buyeraddr.cell_phone?if_exists}&nbsp;${buyeraddr.postcode?if_exists}
			</td>
		    </tr>
          </#list>
	</table>
  </div>
<!--后台table结束-->
<!--按钮操作存放-->
   <div class="bsbut">
     <input type="button" class="rbut" onclick="sendAddressToMember('${(refundapp.trade_id)?if_exists}','buyeraddr.addr_id','/admin_Refundapp_sendAddressToMember.action')" value="发送收货地址">
     <input type="button" class="rbut" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Refundapp_returnGoodsList.action','');"/>
   </div>
<!--按钮操作存放结束-->
</div>
<!--表单框隐藏域存放-->
 <@s.hidden name="refundapp.trade_id"/>
 <!--表单框隐藏域存放-->  
</@s.form>

  </body>
</html>
