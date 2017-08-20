<html>
  <head>
    <title>换货列表</title>
  </head>
  <body>
<@s.form action="/admin_Exchange_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：1 > 2 > 3</div>
        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >标题:</td><td><@s.textfield name="title_s" cssClass="search_input"/></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
                 <td><input type="button" onclick="searchShowDIV('searchDiv','300px','130px');" class="rbut" value="高级查询"></td>
               </tr>
             </table>
          
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                 <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('exchange.trade_id')"/></th>
                 
                 	<th width="10%">trade_id</th>
			    
                 	<th width="10%">buy_cust_id</th>
			    
                 	<th width="10%">buy_user_id</th>
			    
                 	<th width="10%">order_id</th>
			    
                 	<th width="10%">buy_reason</th>
			    
                 	<th width="10%">buy_date</th>
			    
                 	<th width="10%">seller_state</th>
			    
                 	<th width="10%">seller_date</th>
			    
                 	<th width="10%">seller_reason</th>
			    
                 	<th width="10%">img_path</th>
			    
                 	<th width="10%">info_date</th>
			    
                 	<th width="10%">consignee</th>
			    
                 	<th width="10%">mconsignee</th>
			    
                 	<th width="10%">area_attr</th>
			    
                 	<th width="10%">marea_attr</th>
			    
                 	<th width="10%">sell_address</th>
			    
                 	<th width="10%">buy_address</th>
			    
                 	<th width="10%">mobile</th>
			    
                 	<th width="10%">mmobile</th>
			    
                 	<th width="10%">send_mode</th>
			    
                 	<th width="10%">msend_mode</th>
			    
                 	<th width="10%">send_time</th>
			    
                 	<th width="10%">msend_time</th>
			    
                 	<th width="10%">sure_time</th>
			    
                 	<th width="10%">msure_time</th>
			    
                 	<th width="10%">sell_remark</th>
			    
                 	<th width="10%">buy_remark</th>
			    
                 	<th width="10%">send_num</th>
			    
                 	<th width="10%">msend_num</th>
			    
                 	<th width="10%">deny_num</th>
			    
                 	<th width="10%">return_no</th>
			    
                 	<th width="10%">detail_id_str</th>
			    
                 	<th width="10%">refund_state</th>
			    
                 	<th width="10%">refund_type</th>
			    
                 <th  width="10%">操作</th>
               </tr>
			    <#list exchangeList as exchange>
				 <tr >
			         <td><input type="checkbox" name="exchange.trade_id" value="${exchange.trade_id?if_exists}"/></td>
			         
			         	<td align="center">${exchange.trade_id?if_exists}</td>
				    
			         	<td align="center">${exchange.buy_cust_id?if_exists}</td>
				    
			         	<td align="center">${exchange.buy_user_id?if_exists}</td>
				    
			         	<td align="center">${exchange.order_id?if_exists}</td>
				    
			         	<td align="center">${exchange.buy_reason?if_exists}</td>
				    
			         	<td align="center">${exchange.buy_date?if_exists}</td>
				    
			         	<td align="center">${exchange.seller_state?if_exists}</td>
				    
			         	<td align="center">${exchange.seller_date?if_exists}</td>
				    
			         	<td align="center">${exchange.seller_reason?if_exists}</td>
				    
			         	<td align="center">${exchange.img_path?if_exists}</td>
				    
			         	<td align="center">${exchange.info_date?if_exists}</td>
				    
			         	<td align="center">${exchange.consignee?if_exists}</td>
				    
			         	<td align="center">${exchange.mconsignee?if_exists}</td>
				    
			         	<td align="center">${exchange.area_attr?if_exists}</td>
				    
			         	<td align="center">${exchange.marea_attr?if_exists}</td>
				    
			         	<td align="center">${exchange.sell_address?if_exists}</td>
				    
			         	<td align="center">${exchange.buy_address?if_exists}</td>
				    
			         	<td align="center">${exchange.mobile?if_exists}</td>
				    
			         	<td align="center">${exchange.mmobile?if_exists}</td>
				    
			         	<td align="center">${exchange.send_mode?if_exists}</td>
				    
			         	<td align="center">${exchange.msend_mode?if_exists}</td>
				    
			         	<td align="center">${exchange.send_time?if_exists}</td>
				    
			         	<td align="center">${exchange.msend_time?if_exists}</td>
				    
			         	<td align="center">${exchange.sure_time?if_exists}</td>
				    
			         	<td align="center">${exchange.msure_time?if_exists}</td>
				    
			         	<td align="center">${exchange.sell_remark?if_exists}</td>
				    
			         	<td align="center">${exchange.buy_remark?if_exists}</td>
				    
			         	<td align="center">${exchange.send_num?if_exists}</td>
				    
			         	<td align="center">${exchange.msend_num?if_exists}</td>
				    
			         	<td align="center">${exchange.deny_num?if_exists}</td>
				    
			         	<td align="center">${exchange.return_no?if_exists}</td>
				    
			         	<td align="center">${exchange.detail_id_str?if_exists}</td>
				    
			         	<td align="center">${exchange.refund_state?if_exists}</td>
				    
			         	<td align="center">${exchange.refund_type?if_exists}</td>
				      
				    
				    <td align="center"><a onclick="linkToInfo('/admin_Exchange_view.action','exchange.trade_id=${exchange.trade_id?if_exists}');">
					  <img src="/include/common/images/bj.gif" /></a></td>
				    
			       </tr>
			    </#list>
             </table>
           </div>
           <!--翻页-->
           <div class="pages">
             ${pageBar?if_exists}
           </div>
           <div class="clear"/>
           <!--翻页-->
           <div class="bsbut">
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Exchange_add.action','');" value="添加换货">
             <input type="button" class="rbut" onclick="updateBatchState('0','exchange.trade_id','/admin_Exchange_updateStateB2C.action','推荐');" value="推荐">             
			 <input type="button" class="rbut" onclick="updateBatchState('1','exchange.trade_id','/admin_Exchange_updateStateB2C.action','取消推荐');" value="取消推荐">  
             <input type="button" class="rbut"onclick="delInfo('exchange.trade_id','/admin_Exchange_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
	   <!--表单框隐藏域存放--> 
</@s.form>
<div  style="display:none;"  id="searchDiv"  class="searchDiv">
<@s.form action="/admin_Exchange_list.action"   method="post"  id="form_search_id">
<!--dd-->
	<table>
	<tr>
		<td class="searchDiv_td">标题:</td>
		<td><@s.textfield name="title_s"/></td>
	</tr>
	
    <tr>
		<td align="center" colspan="2" style="border:0px;">
		<input type="button" name="search" value="搜索" onclick="showSearchDiv('area_attr','cat_attr','search_area_attr','search_cat_attr','form_search_id');"/>&nbsp;
		   <input type="button" name="close" value="关闭" onclick="closeSearchShowDIV('searchDiv')"/>
		</td>
	</tr>
	</table>
</@s.form>
</div>

  </body>
</html>

