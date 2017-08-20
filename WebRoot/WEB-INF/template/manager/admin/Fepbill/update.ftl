<html>
  <head>
    <title>查看代购汇账单</title>
  </head>
<body >
<@s.form action="/admin_Fepbill_update.action"  method="post" validate="true"  id="detailForm">
<div class="postion">
当前位置：商城管理 >  保税发货 > >查看待购汇账单详情
</div>
<div class="rtdcont">
	<div class="rdtable">
		<table  width="100%" cellspacing="0" cellpadding="0">
			
			  <tr>
	             <td class="table_name">账单时间</td>
	             <td class="table_right">
	              ${fepbill.in_date[0..18]?if_exists}
	             </td>
	           </tr>
			   <tr>
	             <td class="table_name">账单编号</td>
	             <td class="table_right">
						${fepbill.fepbill_id?if_exists}
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">购汇总金额</td>
	             <td class="table_right">
	             ${fepbill.purchasingtotalamount?if_exists}
	             </td>
	           </tr>
	        
			   <tr>
	             <td class="table_name">跨境通订单ID</td>
	             <td class="table_right">
	              ${fepbill.orderids?if_exists}
	             </td>
	           </tr>
	           
       </table>
	</div>
	<div class="clear"/>
   <div class="bsbut_detail">
	       <input type="button" name="returnList" value="返回列表" onclick="linkToInfo('/admin_Fepbill_list.action','');"/>
   </div>
</div>
<div class="clear"></div>
</@s.form>   
</body>
</html>

