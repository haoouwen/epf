<html>
  <head>
    <title>跨境通接收值</title>
  </head>
  <body>
<@s.form action="/admin_Kjtrecall_list.action" method="post" id="indexForm">

        <div class="postion">当前位置：商城管理 > 保税发货 > 跨境通接收值</div>

        <div class="rtdcont">
           <!--条件查询-->
           <div class="rseacher">
             <table cellpadding="0" cellspacing="0">
               <tr>
                  <td >跨境通订单:</td><td><@s.textfield name="sosysno" cssClass="search_input"/></td>
                  <td class="tdpad">跨境通订单状态:</td>
                  <td><@s.select  name="sostatus"  list="kjtcommparaList" listValue="para_key" listKey="para_value" headerKey="" headerValue="--请选择--"  /></td>
                 <td><input type="submit" class="rbut" value="查询"></td>
               </tr>
             </table>
           </div>
           <!--后台table-->
           <div class="rtable">
             <table width="100%" cellspacing="0" cellpadding="0" class="indexTab">
               <tr>
                    <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('kjtrecall.sosysno')"/></th>
                    <th width="10%">商城订单ID</th>
			    
                 	<th width="8%">商城订单价格</th>
			    
                 	<th width="5%">商城税费</th>
			    
                 	<th width="5%">商城运费</th>
                 	
                 	<th width="8%">跨境通订单ID</th>
			    
                 	<th width="8%">跨境通订单价格</th>
			    
                 	<th width="5%">跨境通税费</th>
			    
                 	<th width="5%">跨境通运费</th>
                 	
                 	<th width="8%">跨境通状态</th>
                 	
                 	<th width="7%">购汇帐单号</th>
                 	
                 	<th width="7%">提交时间</th>
               </tr>
			    <#list kjtrecallList as kjtrecall>
				 <tr >
				        <td><input type="checkbox" name="kjtrecall.sosysno" value="${kjtrecall.sosysno?if_exists}"/></td>
				        
			         	<td align="center">${kjtrecall.order_id?if_exists}</td>
				    
			         	<td align="center">${kjtrecall.tatal_amount?if_exists}</td>
				    
			         	<td align="center">${kjtrecall.taxes?if_exists}</td>
				    
			         	<td align="center">${kjtrecall.ship_free?if_exists}</td>
				    
				    	<td align="center">${kjtrecall.sosysno?if_exists}</td>

			         	<td align="center">${kjtrecall.productamount?if_exists}</td>
				    
			         	<td align="center">${kjtrecall.taxamount?if_exists}</td>
				    
			         	<td align="center">${kjtrecall.shippingamount?if_exists}</td>
			         	<td align="center">
			         	                <#list kjtcommparaList as clist>
						                     <#if kjtrecall.sostatus==clist.para_value>
									            	${clist.para_key}
						                     </#if>
						                </#list>
			         	</td>
			         	<td align="center">${kjtrecall.purchasing?if_exists}</td>
			         	<td align="center">${kjtrecall.kjtdate?if_exists}</td>
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
             <input type="button" class="rbut"onclick="exprotExcel('kjtrecall.sosysno','/admin_Kjtrecall_exportInfo.action');"  value="导出购汇帐单">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
		 <@s.hidden  name="excid" id="excid"/>
	   <!--表单框隐藏域存放--> 
</@s.form>
<div  style="display:none;"  id="searchDiv"  class="searchDiv">
<@s.form action="/admin_Kjtrecall_list.action"   method="post"  id="form_search_id">
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

