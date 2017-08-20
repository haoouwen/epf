<html>
  <head>
    <title>发票列表</title>
  </head>
  <body>
<@s.form action="/admin_Orderinvoice_list.action" method="post" id="indexForm">

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
                 <th width="5%"><input type="checkbox" name="checkbox" id="checkall" onclick="selectAll('orderinvoice.invoice_id')"/></th>
                 
                 	<th width="10%">invoice_id</th>
			    
                 	<th width="10%">look_up</th>
			    
                 	<th width="10%">p_content</th>
			    
                 	<th width="10%">invoice_type</th>
			    
                 	<th width="10%">company_name</th>
			    
                 	<th width="10%">identifier</th>
			    
                 	<th width="10%">re_address</th>
			    
                 	<th width="10%">re_phone</th>
			    
                 	<th width="10%">bank_name</th>
			    
                 	<th width="10%">bank_id</th>
			    
                 	<th width="10%">z_content</th>
			    
                 	<th width="10%">ticket_name</th>
			    
                 	<th width="10%">ticket_cell</th>
			    
                 	<th width="10%">area_attr</th>
			    
                 	<th width="10%">address</th>
			    
                 	<th width="10%">cust_id</th>
			    
                 	<th width="10%">user_id</th>
			    
                 <th  width="10%">操作</th>
               </tr>
			    <#list orderinvoiceList as orderinvoice>
				 <tr >
			         <td><input type="checkbox" name="orderinvoice.invoice_id" value="${orderinvoice.invoice_id?if_exists}"/></td>
			         
			         	<td align="center">${orderinvoice.invoice_id?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.look_up?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.p_content?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.invoice_type?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.company_name?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.identifier?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.re_address?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.re_phone?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.bank_name?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.bank_id?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.z_content?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.ticket_name?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.ticket_cell?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.area_attr?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.address?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.cust_id?if_exists}</td>
				    
			         	<td align="center">${orderinvoice.user_id?if_exists}</td>
				      
				    
				    <td align="center"><a onclick="linkToInfo('/admin_Orderinvoice_view.action','orderinvoice.invoice_id=${orderinvoice.invoice_id?if_exists}');">
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
             <input type="button" class="rbut" onclick="linkToInfo('/admin_Orderinvoice_add.action','');" value="添加发票">
             <input type="button" class="rbut" onclick="updateBatchState('0','orderinvoice.invoice_id','/admin_Orderinvoice_updateStateB2C.action','推荐');" value="推荐">             
			 <input type="button" class="rbut" onclick="updateBatchState('1','orderinvoice.invoice_id','/admin_Orderinvoice_updateStateB2C.action','取消推荐');" value="取消推荐">  
             <input type="button" class="rbut"onclick="delInfo('orderinvoice.invoice_id','/admin_Orderinvoice_delete.action')" value="删除">
           </div>
        </div>
		<!--表单框隐藏域存放-->
		 <!-- <@s.hidden  name="xxx_s"/>-->
	   <!--表单框隐藏域存放--> 
</@s.form>
<div  style="display:none;"  id="searchDiv"  class="searchDiv">
<@s.form action="/admin_Orderinvoice_list.action"   method="post"  id="form_search_id">
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

