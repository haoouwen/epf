<html>
  <head>
    <title>取消订单理由列表</title>
  </head>
  <body>
<@s.form action="/admin_Cancelorder_list.action" method="post" id="indexForm">

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
                 <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('cancelorder.trade_id')"/></th>
                 
                 	<th width="10%">trade_id</th>
			    
                 	<th width="10%">buy_cust_id</th>
			    
                 	<th width="10%">buy_user_id</th>
			    
                 	<th width="10%">order_id</th>
			    
                 	<th width="10%">buy_reason</th>
			    
                 	<th width="10%">buy_date</th>
			    
                 	<th width="10%">sell_cust_id</th>
			    
                 	<th width="10%">order_state</th>
			    
                 <th  width="10%">操作</th>
               </tr>
			    <#list cancelorderList as cancelorder>
				 <tr >
			         <td><input type="checkbox" name="cancelorder.trade_id" value="${cancelorder.trade_id?if_exists}"/></td>
			         
			         	<td align="center">${cancelorder.trade_id?if_exists}</td>
				    
			         	<td align="center">${cancelorder.buy_cust_id?if_exists}</td>
				    
			         	<td align="center">${cancelorder.buy_user_id?if_exists}</td>
				    
			         	<td align="center">${cancelorder.order_id?if_exists}</td>
				    
			         	<td align="center">${cancelorder.buy_reason?if_exists}</td>
				    
			         	<td align="center">${cancelorder.buy_date?if_exists}</td>
				    
			         	<td align="center">${cancelorder.sell_cust_id?if_exists}</td>
				    
			         	<td align="center">${cancelorder.order_state?if_exists}</td>
				      
				    
				    <td align="center"><a onclick="linkToInfo('/admin_Cancelorder_view.action','cancelorder.trade_id=${cancelorder.trade_id?if_exists}');">
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
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Cancelorder_add.action','');" value="添加取消订单理由">
             <input type="button" class="rbut" onclick="updateBatchState('0','cancelorder.trade_id','/admin_Cancelorder_updateStateB2C.action','推荐');" value="推荐">             
			 <input type="button" class="rbut" onclick="updateBatchState('1','cancelorder.trade_id','/admin_Cancelorder_updateStateB2C.action','取消推荐');" value="取消推荐">  
             <input type="button" class="rbut"onclick="delInfo('cancelorder.trade_id','/admin_Cancelorder_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
	   <!--表单框隐藏域存放--> 
</@s.form>
<div  style="display:none;"  id="searchDiv"  class="searchDiv">
<@s.form action="/admin_Cancelorder_list.action"   method="post"  id="form_search_id">
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

